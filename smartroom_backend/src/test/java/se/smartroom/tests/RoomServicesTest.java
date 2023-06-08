package se.smartroom.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import se.smartroom.entities.Room;
import se.smartroom.repositories.RoomRepository;
import se.smartroom.services.RoomService;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServicesTest {
    @Mock
    private RoomRepository repository;

    @InjectMocks
    private RoomService roomService;

    @Test
    public void testUpdateRoom() {
        // Arrange
        RoomRepository mockRoomRepository = mock(RoomRepository.class);
        RoomService roomService = new RoomService(mockRoomRepository);

        Room existingRoom = new Room("Existing Room", 1);
        Room updatedRoom = new Room("Updated Room", 1);

        // Set up the behavior of the mock repository
        when(mockRoomRepository.findById(existingRoom.getId())).thenReturn(Optional.of(existingRoom));
        when(mockRoomRepository.save(existingRoom)).thenReturn(existingRoom);

        // Act
        Room result = roomService.updateRoom(updatedRoom);

        // Assert
        assertEquals(existingRoom, result);
        assertEquals(updatedRoom.getName(), existingRoom.getName());
        assertEquals(updatedRoom.getSize(), existingRoom.getSize());
        assertEquals(updatedRoom.getDoors(), existingRoom.getDoors());
        assertEquals(updatedRoom.getRoomWindows(), existingRoom.getRoomWindows());
        assertEquals(updatedRoom.getLights(), existingRoom.getLights());
        assertEquals(updatedRoom.getFans(), existingRoom.getFans());
    }

    @Test
    public void testGetRooms() {
        // Arrange
        RoomRepository mockRoomRepository = mock(RoomRepository.class);
        RoomService roomService = new RoomService(mockRoomRepository);

        List<Room> expectedRooms = new ArrayList<>();
        expectedRooms.add(new Room("Room 1", 1));
        expectedRooms.add(new Room("Room 2", 2));

        // Set up the behavior of the mock repository
        when(mockRoomRepository.findAll()).thenReturn(expectedRooms);

        // Act
        List<Room> result = roomService.getRooms();

        // Assert
        assertEquals(expectedRooms, result);
    }

    @Test
    public void testGetRoomById() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        Room roomToRemove = new Room("Room 1", 1);
        RoomRepository mockRoomRepository = mock(RoomRepository.class);
        when(mockRoomRepository.findById(1)).thenReturn(Optional.of(roomToRemove));
        RoomService roomServiceMock = new RoomService(mockRoomRepository);
        Room roomResult = roomServiceMock.getRoomById(1);

        // Assert
        assertEquals(roomToRemove, roomResult);

        // Check if repository field is initialized
        Field repositoryField = RoomService.class.getDeclaredField("repository");
        repositoryField.setAccessible(true);
        RoomRepository repositoryInstance = (RoomRepository) repositoryField.get(roomServiceMock);
        assertNotNull(repositoryInstance);
    }

    @Test
    public void testSaveRoom() {
        // Arrange
        // Arrange
        RoomRepository mockRoomRepository = mock(RoomRepository.class);
        RoomService roomService = new RoomService(mockRoomRepository);

        Room roomToSave = new Room("Room 1", 1);
        Room savedRoom = new Room("Saved Room", 1);

        // Set up the behavior of the mock repository
        when(mockRoomRepository.save(roomToSave)).thenReturn(savedRoom);

        // Act
        Room result = roomService.saveRoom(roomToSave);

        // Assert
        assertEquals(savedRoom, result);
    }

    @Test
    public void testRemoveRoom_ExistingRoom_ReturnsRoom() {
        // Arrange
        int roomId = 1;
        Room room = new Room("Room 1", 100);

        when(repository.findById(roomId)).thenReturn(Optional.of(room));

        // Act
        Room removedRoom = roomService.removeRoom(roomId);

        // Assert
        assertEquals(room, removedRoom);
    }

    @Test
    public void testRemoveRoom_NonExistingRoom_ReturnsNull() {
        // Arrange
        int roomId = 1;

        when(repository.findById(roomId)).thenReturn(Optional.empty());

        // Act
        Room removedRoom = roomService.removeRoom(roomId);

        // Assert
        assertNull(removedRoom);
    }




}
