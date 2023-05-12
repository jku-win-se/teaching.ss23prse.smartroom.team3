package se.smartroom.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.smartroom.entities.Room;
import se.smartroom.repositories.RoomRepository;
import se.smartroom.services.RoomService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RoomServicesTest {
    @Mock
    private RoomRepository repository;

    @InjectMocks
    private RoomService roomService;

    @Test
    public void testRemoveRoom() {
        // Arrange
        int roomId = 1;
        Room roomToRemove = new Room("Room 1", 1);

        // Set up mock behavior
        Mockito.when(repository.findById(roomId)).thenReturn(Optional.of(roomToRemove));

        // Act
        Room removedRoom = roomService.removeRoom(roomId);

        // Assert
        assertNotNull(removedRoom);
        assertEquals(roomToRemove, removedRoom);

        // Verify interactions
        Mockito.verify(repository).findById(roomId);
        Mockito.verify(repository).delete(roomToRemove);
    }

    @Test
    public void testUpdateRoom() {
        // Arrange
        int roomId = 1;
        Room updatedRoom = new Room("Updated Room", 2);
        updatedRoom.setId(roomId);

        Room existingRoom = new Room("Existing Room", 3);
        existingRoom.setId(roomId);

        // Set up mock behavior
        Mockito.when(repository.findById(roomId)).thenReturn(Optional.of(existingRoom));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Room result = roomService.updateRoom(updatedRoom);

        // Assert
        assertNotNull(result);
        assertEquals(updatedRoom, result);

        // Verify interactions
        Mockito.verify(repository).findById(roomId);
        Mockito.verify(repository).save(existingRoom);
    }

    @Test
    public void testGetRooms() {
        // Arrange
        List<Room> mockedRooms = new ArrayList<>();
        mockedRooms.add(new Room("Room 1", 1));
        mockedRooms.add(new Room("Room 2", 2));

        // Set up mock behavior
        Mockito.when(repository.findAll()).thenReturn(mockedRooms);

        // Act
        List<Room> result = roomService.getRooms();

        // Assert
        assertNotNull(result);
        assertEquals(mockedRooms, result);

        // Verify interactions
        Mockito.verify(repository).findAll();
    }

    @Test
    public void testGetRoomById() {
        // Arrange
        int roomId = 1;
        Room expectedRoom = new Room("Room 1", 1);
        expectedRoom.setId(roomId);

        // Set up mock behavior
        Mockito.when(repository.findById(roomId)).thenReturn(Optional.of(expectedRoom));

        // Act
        Room result = roomService.getRoomById(roomId);

        // Assert
        assertEquals(expectedRoom, result);

        // Verify interactions
        Mockito.verify(repository).findById(roomId);
    }

    @Test
    public void testSaveRoom() {
        // Arrange
        Room roomToSave = new Room("New Room", 1);
        Room expectedSavedRoom = new Room("New Room", 2);
        expectedSavedRoom.setId(1);

        // Set up mock behavior
        Mockito.when(repository.save(roomToSave)).thenReturn(expectedSavedRoom);

        // Act
        Room result = roomService.saveRoom(roomToSave);

        // Assert
        assertEquals(expectedSavedRoom, result);

        // Verify interactions
        Mockito.verify(repository).save(roomToSave);
    }

}
