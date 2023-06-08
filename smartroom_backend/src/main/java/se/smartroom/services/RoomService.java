package se.smartroom.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import se.smartroom.entities.Room;
import se.smartroom.entities.data.Co2SensorData;
import se.smartroom.entities.data.TemperatureData;
import se.smartroom.entities.environment.EnvironmentData;
import se.smartroom.entities.people.PeopleData;
import se.smartroom.entities.physicalDevice.Window;
import se.smartroom.repositories.EnvironmentDataRepository;
import se.smartroom.repositories.RoomRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private EnvironmentDataRepository environmentDataRepository;

    public RoomService(RoomRepository mockRoomRepository) {
        repository=mockRoomRepository;
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
        Room room = repository.findById(id).orElse(null);
        System.out.println("TEST");
        System.out.println(room);

        Random random = new Random();
        int minValue = 10;
        int maxValue = 25;

        Timestamp randomTimestamp = new Timestamp(System.currentTimeMillis());

        room.getCo2SensorData().add(new Co2SensorData(Math.random()));
        room.getTemperaturData().add(new TemperatureData(random.nextDouble(maxValue - minValue + 1.0) + minValue));
        room.getPeopleData().add(new PeopleData(Date.valueOf(LocalDate.now()), random.nextInt(30 + 1)));

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
                    numOpenWindows = room.getRoomWindows().stream().filter(Window::isOpen).toList().size();
                }
                int numPeople = 0;
                if (room.getPeopleData().size() > 0) {
                    numPeople = room.getPeopleData().get(room.getPeopleData().size() -1).getCount();
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

                if (numOpenWindows > 0) {
                    temperatureAdjustment -= 1.0;
                }
                if (numPeople > 0 && environmentData.getOutsideTemperature() > room.getTemperaturData().get(0).getTemperatureValue()) {
                    temperatureAdjustment += 1.0;
                }

                double newTemperature = environmentData.getOutsideTemperature() + temperatureAdjustment;

                Date timestamp = new Date(System.currentTimeMillis());
                TemperatureData newTemperaturData = new TemperatureData();
                newTemperaturData.setTemperatureValue(newTemperature);
                newTemperaturData.setTimestamp(timestamp);

                Co2SensorData co2SensorData = new Co2SensorData();
                co2SensorData.setcO2value(newCo2Value);
                co2SensorData.setTimestamp(timestamp);

                List<TemperatureData> roomsTempData = room.getTemperaturData();
                roomsTempData.add(newTemperaturData);
                room.setTemperaturData(roomsTempData);

                List<Co2SensorData> roomsC02Data = room.getCo2SensorData();
                roomsC02Data.add(co2SensorData);
                room.setCo2SensorData(roomsC02Data);

                return room;
            }).collect(Collectors.toList());

            repository.saveAll(updatedRooms);
        }
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
