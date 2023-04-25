package se.smartroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.smartroom.entities.Room;
import se.smartroom.repositories.RoomRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
