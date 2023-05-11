package se.smartroom.tests;


import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import se.smartroom.controller.RoomController;
import se.smartroom.entities.Room;
import se.smartroom.services.RoomService;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTest {


    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @Mock
    private HttpServletResponse response;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }
    @Test
    public void testGetRooms() {
        // Create a list of Room objects for mocking the response
        List<Room> mockedRooms = new ArrayList<>();
        mockedRooms.add(new Room("Room 1",45));
        mockedRooms.add(new Room("Room 2",23));

        // Configure the behavior of the mock
        when(roomService.getRooms()).thenReturn(mockedRooms);

        // Invoke the controller method
        List<Room> result = roomController.getRooms();

        // Verify the interactions and assertions
        verify(roomService).getRooms();
        assertEquals(mockedRooms, result);
    }

    @Test
    public void testGetRoom() {
        // Arrange
        int roomId = 1;
        Room mockedRoom = new Room("Room 1", 33);
        when(roomService.getRoomById(roomId)).thenReturn(mockedRoom);

        // Act
        Room result = roomController.getRoom(roomId);

        // Assert
        assertEquals(mockedRoom, result);
        verify(roomService).getRoomById(roomId);
    }

    @Test
    public void testUpdateRoom() {
        // Arrange
        Room roomToUpdate = new Room("Room 1", 23);
        Room updatedRoom = new Room("Updated Room 1", 23);
        when(roomService.updateRoom(roomToUpdate)).thenReturn(updatedRoom);

        // Act
        Room result = roomController.updateRoom(roomToUpdate);

        // Assert
        assertEquals(updatedRoom, result);
        verify(roomService).updateRoom(roomToUpdate);
    }

    @Test
    public void testCreateRoom() {
        // Arrange
        Room roomToCreate = new Room("Room 1", 11);
        Room createdRoom = new Room("Created Room 1", 11);
        Mockito.when(roomService.saveRoom(roomToCreate)).thenReturn(createdRoom);

        // Act
        Room result = roomController.createRoom(roomToCreate);

        // Assert
        assertEquals(createdRoom, result);
        Mockito.verify(roomService).saveRoom(roomToCreate);
    }

    @Test
    public void testDeleteRoom() {
        // Arrange
        int roomId = 1;
        Room deletedRoom = new Room("Deleted Room", 1);
        Mockito.when(roomService.removeRoom(roomId)).thenReturn(deletedRoom);

        // Act
        Room result = roomController.deleteRoom(roomId);

        // Assert
        assertEquals(deletedRoom, result);
        Mockito.verify(roomService).removeRoom(roomId);
    }

    @Test
    public void testExportToCSV() throws Exception {
        // Arrange
        List<Room> mockedRooms = new ArrayList<>();
        mockedRooms.add(new Room("Room 1", 1));
        mockedRooms.add(new Room("Room 2", 2));
        Mockito.when(roomService.getRooms()).thenReturn(mockedRooms);


        ArgumentCaptor<String[]> csvHeaderCaptor = ArgumentCaptor.forClass(String[].class);
        ArgumentCaptor<String[]> nameMappingCaptor = ArgumentCaptor.forClass(String[].class);

        // Act and Assert
        mockMvc.perform(get("/rooms/export"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", containsString("attachment; filename=rooms_")));

        // Verify interactions and assertions
        Mockito.verify(roomService).getRooms();

    }
}
