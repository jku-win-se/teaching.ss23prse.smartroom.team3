package se.smartroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.smartroom.entities.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    default Room getByID(int i) {
        // Retrieve the room from the repository based on the ID
        Optional<Room> optionalRoom = this.findById(i);

        // Return the room if it exists, otherwise return null or throw an exception
        return optionalRoom.orElse(null);
    }
}
