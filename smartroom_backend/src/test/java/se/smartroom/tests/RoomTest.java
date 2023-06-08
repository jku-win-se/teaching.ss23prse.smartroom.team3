package se.smartroom.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import se.smartroom.entities.Room;
import se.smartroom.entities.data.Co2SensorData;
import se.smartroom.entities.data.TemperatureData;
import se.smartroom.entities.people.PeopleData;
import se.smartroom.entities.physicalDevice.Door;
import se.smartroom.entities.physicalDevice.Window;
import se.smartroom.entities.smartDevice.Fan;
import se.smartroom.entities.smartDevice.Light;
import se.smartroom.repositories.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RoomTest {
    @Test
    public void testCo2SensorDataMethods() {
        // Create an instance of DataContainer
        Room dataContainer = new Room(RoomRepository.class);

        // Create sample lists of Co2SensorData objects
        List<Co2SensorData> expectedCo2SensorData = new ArrayList<>();
        expectedCo2SensorData.add(new Co2SensorData(1.23));
        expectedCo2SensorData.add(new Co2SensorData(4.56));

        // Set the sample lists using setCo2SensorData()
        dataContainer.setCo2SensorData(expectedCo2SensorData);

        // Retrieve the lists using getCo2SensorData()
        List<Co2SensorData> actualCo2SensorData = dataContainer.getCo2SensorData();

        // Assert that the retrieved lists match the expected lists
        assertEquals(expectedCo2SensorData, actualCo2SensorData);
    }

    @Test
    public void testTemperaturDataMethods() {
        // Create an instance of DataContainer
        Room dataContainer = new Room(RoomRepository.class);

        // Create sample lists of TemperaturData objects
        List<TemperatureData> expectedTemperaturData = new ArrayList<>();
        expectedTemperaturData.add(new TemperatureData(12.34));
        expectedTemperaturData.add(new TemperatureData(56.78));

        // Set the sample lists using setTemperaturData()
        dataContainer.setTemperaturData(expectedTemperaturData);

        // Retrieve the lists using getTemperaturData()
        List<TemperatureData> actualTemperaturData = dataContainer.getTemperaturData();

        // Assert that the retrieved lists match the expected lists
        assertEquals(expectedTemperaturData, actualTemperaturData);
    }

    @Test
    public void testToString() {
        // Create an instance of Room
        Room room = new Room(RoomRepository.class);
        room.setId(1);
        room.setName("Living Room");
        room.setSize(20);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(true));
        List<Window> windows = new ArrayList<>();
        windows.add(new Window(true));
        List<Light> lights = new ArrayList<>();
        lights.add(new Light(true));
        List<Fan> fans = new ArrayList<>();
        fans.add(new Fan(true));
        windows.add(new Window(true));
        List<Co2SensorData> co2SensorData = new ArrayList<>();
        co2SensorData.add(new Co2SensorData(1.23));
        co2SensorData.add(new Co2SensorData(4.56));

        List<TemperatureData> temperaturData = new ArrayList<>();
        temperaturData.add(new TemperatureData(12.34));
        temperaturData.add(new TemperatureData(56.78));

        List<PeopleData> peopleData = new ArrayList<>();

        room.setDoors(doors);
        room.setRoomWindows(windows);
        room.setFans(fans);
        room.setLights(lights);
        room.setCo2SensorData(co2SensorData);
        room.setTemperaturData(temperaturData);
        room.setPeopleData(peopleData);

        // Invoke the toString() method
        String result = room.toString();

        // Define the expected string representation
        String expectedString = "Room{" +
                "id=1, " +
                "name='Living Room', " +
                "size=20, " +
                "doors="+ doors.toString()+", " +
                "roomWindows=" + windows.toString()+", " +
                "lights=" + lights.toString()+", " +
                "fans=" + fans.toString()+", " +
                "co2SensorData=" + co2SensorData.toString() + ", " +
                "temperaturData=" + temperaturData.toString() + ", " +
                "peopleData=" + "[]" +
                "}";

        // Assert that the returned string matches the expected string
        assertEquals(expectedString, result);

        //hash methode testing
        int expectedHashCode = Objects.hash(room.getId(), room.getName(), room.getSize(), room.getDoors(),
                room.getRoomWindows(), room.getLights(), room.getFans(), room.getCo2SensorData(), room.getTemperaturData(), room.getPeopleData());

        // Invoke the hashCode() method
        int result2 = room.hashCode();

        assertEquals(expectedHashCode,result2);
    }
}
