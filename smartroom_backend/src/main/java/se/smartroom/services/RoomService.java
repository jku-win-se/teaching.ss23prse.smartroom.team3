package se.smartroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.smartroom.entities.Room;
import se.smartroom.repositories.RoomRepository;

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
        return repository
                .findById(id)
                .map(value -> {
                    value.setName(room.getName());
                    value.setSize(room.getSize());
                    value.setDoors(room.getDoors());
                    value.setRoomWindows(room.getRoomWindows());
                    value.setLights(room.getLights());
                    value.setFans(room.getFans());

                    return repository.save(value);
                })
                .orElseGet(() -> {
                    return repository.save(room);
                });
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
