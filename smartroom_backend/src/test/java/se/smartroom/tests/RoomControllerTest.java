package se.smartroom.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import se.smartroom.entities.Room;
import se.smartroom.entities.data.TemperaturData;
import se.smartroom.entities.data.Co2SensorData;
import se.smartroom.entities.physicalDevice.Door;
import se.smartroom.entities.physicalDevice.Fenster;
import se.smartroom.entities.smartDevice.Fan;
import se.smartroom.entities.smartDevice.Light;
import se.smartroom.services.RoomService;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGETRoomsEndpointWithNoRooms() throws Exception {
        List<Room> rooms = Collections.emptyList();
        given(roomService.getRooms()).willReturn(rooms);

        ResultActions response = mockMvc.perform(get("/rooms"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(rooms.size())));
    }

    @Test
    public void testGETRoomsEndpointWithTwoRoom() throws Exception {
        List<Room> rooms = Arrays.asList(createRoom("Living Room", 25, 0), createRoom("Bed Room", 12, 1));
        given(roomService.getRooms()).willReturn(rooms);

        ResultActions response = mockMvc.perform(get("/rooms"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(rooms.size())));
    }

    @Test
    public void testGETRoomsByIdEndpoint() throws Exception {
        int id = 5;
        Room room = createRoom("Living Room", 25, id);
        given(roomService.getRoomById(5)).willReturn(room);

        ResultActions response = mockMvc.perform(get("/room/5"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(room.getId())))
                .andExpect(jsonPath("$.name", is(room.getName())));
    }

    @Test
    public void testPUTRoomsEndpoint() throws Exception {
        int id = 5;
        Room savedRoom = createRoom("Living Room", 25, id);
        Room updatedRoom = createRoom("Bed Room", 25, id);
        given(roomService.getRoomById(id)).willReturn(savedRoom);
        given(roomService.updateRoom(any(Room.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRoom)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(updatedRoom.getName())));
    }

    @Test
    public void testPOSTRoomsEndpointWithNoRooms() throws Exception {
        Room room = createRoom("Living Room", 25, 0);

        given(roomService.saveRoom(any(Room.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(room)));

        response.andDo(print())
                .andExpect(jsonPath("$.name", is(room.getName())))
                .andExpect(jsonPath("$.size", is(room.getSize())))
                .andExpect(jsonPath("$.doors.size()", is(room.getDoors().size())))
                .andExpect(jsonPath("$.roomWindows.size()", is(room.getRoomWindows().size())))
                .andExpect(jsonPath("$.lights.size()", is(room.getLights().size())))
                .andExpect(jsonPath("$.fans.size()", is(room.getFans().size())))
                .andExpect(jsonPath("$.temperaturData.size()", is(room.getTemperaturData().size())))
                .andExpect(jsonPath("$.co2SensorData.size()", is(room.getCo2SensorData().size())));
    }

    @Test
    public void testDELETERoomsByIdEndpoint() throws Exception {
        int id = 5;
        Room room = createRoom("Living Room", 25, id);
        given(roomService.getRoomById(id)).willReturn(room);

        ResultActions response = mockMvc.perform(delete("/room/{id}", id));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    private Room createRoom(String name, int size, int id) {
        Room room = new Room();
        room.setId(id);
        room.setName(name);
        room.setSize(size);
        room.setDoors(Arrays.asList(new Door(false), new Door(true)));
        room.setRoomWindows(Arrays.asList(new Fenster(0, true)));
        room.setLights(Arrays.asList(new Light(0, true)));
        room.setFans(Arrays.asList(new Fan(0, true)));
        room.setTemperaturData(Arrays.asList(new TemperaturData(0, new Date(System.currentTimeMillis()), 22.5)));
        room.setCo2SensorData(Arrays.asList(new Co2SensorData(0, new Date(System.currentTimeMillis()), 22.5)));

        return room;
    }

}
