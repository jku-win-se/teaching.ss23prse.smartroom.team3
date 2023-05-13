package se.smartroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.smartroom.entities.Room;
import se.smartroom.entities.data.Co2SensorData;
import se.smartroom.entities.data.TemperaturData;
import se.smartroom.entities.people.PeopleData;
import se.smartroom.repositories.RoomRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

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
        room.getTemperaturData().add(new TemperaturData(random.nextDouble(maxValue - minValue + 1) + minValue));
        room.getPeopleData().add(new PeopleData(Date.valueOf(LocalDate.now()), random.nextInt(30 + 1)));

        return updateRoom(room);
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
