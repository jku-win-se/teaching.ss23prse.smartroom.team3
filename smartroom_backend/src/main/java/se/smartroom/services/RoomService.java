package se.smartroom.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import se.smartroom.entities.Room;
import se.smartroom.entities.data.Co2SensorData;
import se.smartroom.entities.data.TemperatureData;
import se.smartroom.entities.environment.EnvironmentData;
import se.smartroom.entities.people.PeopleData;
import se.smartroom.entities.physicalDevice.Fenster;
import se.smartroom.repositories.EnvironmentDataRepository;
import se.smartroom.repositories.RoomRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private EnvironmentDataRepository environmentDataRepository;

    @Value("${body.heat}")
    public double bodyHeat;

    public RoomService(RoomRepository mockRoomRepository) {
        repository = mockRoomRepository;
    }

    public RoomService(){

    }

    public RoomService(RoomRepository mockRoomRepository, EnvironmentDataRepository MockEnvironmentDataRepository) {
        repository = mockRoomRepository;
        environmentDataRepository = MockEnvironmentDataRepository;
    }

    /**
     * Stores and saves a room
     *
     * @param room to store
     * @return stored room
     */
    public Room saveRoom(Room room) {
        return repository.save(room);
    }

    public Room removeRoom(int id) {
        Room room = repository.findById(id).orElse(null);

        if (room != null) {
            repository.delete(room);
        }

        return room;
    }

    public Room updateRoom(Room room) {
        int id = room.getId();

        Room existingRoom = repository.findById(id).orElse(room);

        existingRoom.setName(room.getName());
        existingRoom.setSize(room.getSize());
        existingRoom.setDoors(room.getDoors());
        existingRoom.setRoomWindows(room.getRoomWindows());
        existingRoom.setLights(room.getLights());
        existingRoom.setFans(room.getFans());

        return repository.save(existingRoom);
    }

    public Room addValues(int id) {
        Room room = repository.findById(id).orElse(new Room());
        System.out.println(room);

        Random random = new Random();
        int minValue = 10;
        int maxValue = 25;

        Timestamp randomTimestamp = new Timestamp(System.currentTimeMillis());

        room.setCo2SensorData(Collections.singletonList(new Co2SensorData(Math.random())));
        room.setTemperatureData(Collections.singletonList(new TemperatureData(random.nextDouble(maxValue - minValue + 1.0) + minValue)));
        room.setPeopleData(Collections.singletonList(new PeopleData(Date.valueOf(LocalDate.now()), random.nextInt(30 + 1))));
        return updateRoom(room);
    }

    /**
     * Runs every 5 seconds.
     * Going over all rooms and updating the values based on the number
     * of people in the room, the outside temperature and the number of open
     * windows, running fans.
     */
    @Transactional
    @Scheduled(initialDelay = 7000, fixedRate = 5000)
    public void scheduledIntervalCalculation() {
        List<Room> rooms = repository.findAll();
        EnvironmentData environmentData = environmentDataRepository.findAll().get(0);
        if (!rooms.isEmpty()) {
            List<Room> updatedRooms = rooms.stream().map(room -> {
                int numOpenWindows = 0;
                if (!room.getRoomWindows().isEmpty()) {
                    numOpenWindows = room.getRoomWindows().stream().filter(Fenster::isOpen).toList().size();
                }
                int numPeople = 1;
                if (room.getPeopleData().size() > 0) {
                    numPeople = room.getPeopleData().get(room.getPeopleData().size() - 1).getCount();
                }
                double temperatureAdjustment = 0.0;

                double co2Adjustment = 0.0;
                if (numPeople > 0) {
                    co2Adjustment += 1.0;
                } else {
                    co2Adjustment -= 1.0;
                }
                double latestCo2Value = 0.0;
                if (room.getCo2SensorData().size() > 0) {
                    latestCo2Value = room.getCo2SensorData().get(room.getCo2SensorData().size() - 1).getcO2value();
                }
                double newCo2Value = latestCo2Value + co2Adjustment;

                double newTemperatureAdjustment = calculateTemperatureChange(numOpenWindows, numPeople, this.bodyHeat, environmentData.getOutsideTemperature(), room.getSize());

                double newTemperature = environmentData.getOutsideTemperature();
                if (newTemperatureAdjustment > 0) {
                    newTemperature += newTemperatureAdjustment;
                } else {
                    newTemperature -= newTemperatureAdjustment;
                }

                Date timestamp = new Date(System.currentTimeMillis());
                TemperatureData newTemperatureData = new TemperatureData();
                newTemperatureData.setTemperatureValue(newTemperature);
                newTemperatureData.setTimestamp(timestamp);
                Co2SensorData co2SensorData = new Co2SensorData();
                co2SensorData.setcO2value(newCo2Value);
                co2SensorData.setTimestamp(timestamp);
                List<TemperatureData> roomsTempData = room.getTemperatureData();
                roomsTempData.add(newTemperatureData);
                room.setTemperatureData(roomsTempData);
                List<Co2SensorData> roomsC02Data = room.getCo2SensorData();
                roomsC02Data.add(co2SensorData);
                room.setCo2SensorData(roomsC02Data);
                return room;
            }).collect(Collectors.toList());
            repository.saveAll(updatedRooms);
        }
    }


    /**
     *
     * @param numOpenWindows
     * @param numPeople
     * @param avgBodyHeat in WATT
     * @param outsideTemperature in Celsius
     * @param roomSize in m2
     * @return
     */
    public static double calculateTemperatureChange(int numOpenWindows, int numPeople, double avgBodyHeat, double outsideTemperature, double roomSize) {
        double temperatureChange = (numOpenWindows > 0 ? numOpenWindows : 0.1 * numPeople * avgBodyHeat * outsideTemperature) / (100 * roomSize);
        return Math.min(Math.max(temperatureChange, -1), 1);
    }

    /**
     * Returns a list of all rooms
     *
     * @return List of Rooms
     */
    public List<Room> getRooms() {
        return repository.findAll();
    }

    public Room getRoomById(int id) {
        return repository.findById(id).orElse(null);
    }

}
