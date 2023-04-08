package se.smartroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.smartroom.entities.Room;
import se.smartroom.services.RoomService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/room/{id}")
    public Room getRoom(@PathVariable int id) {
        return roomService.getRoomById(id);
    }

    @PutMapping("/room")
    public Room updateRoom(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @PostMapping("/room")
    public Room createRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @DeleteMapping("/room/{id}")
    public Room deleteRoom(@PathVariable int id) {
        return roomService.removeRoom(id);
    }

}
