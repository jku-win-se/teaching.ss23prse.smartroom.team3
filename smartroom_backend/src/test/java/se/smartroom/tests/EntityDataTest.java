package se.smartroom.tests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import se.smartroom.entities.data.Co2SensorData;
import se.smartroom.entities.data.DataInterface;
import se.smartroom.entities.data.TemperatureData;
import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EntityDataTest {

    //Interface Tests
    @Test
    public void testConstructorAndGetters1() {
        // Create a Date object to use in the test
        Date timestamp = new Date(System.currentTimeMillis());

        // Create an instance of DataInterface using the constructor
        DataInterface dataInterface = new DataInterface(timestamp);

        // Assert that the timestamp is set correctly
        assertEquals(timestamp, dataInterface.getTimestamp());
    }

    @Test
    public void testSetters1() {
        // Create an instance of DataInterface using the default constructor
        DataInterface dataInterface = new DataInterface();

        // Create a Date object to use in the test
        Date timestamp = new Date(System.currentTimeMillis());

        // Set the timestamp using the setter method
        dataInterface.setTimestamp(timestamp);

        // Assert that the timestamp is set correctly
        assertEquals(timestamp, dataInterface.getTimestamp());
    }

    @Test
    public void testToString1() {
        // Create an instance of DataInterface using the default constructor
        DataInterface dataInterface = new DataInterface();

        // Assert that the toString method doesn't return null
        assertNotNull(dataInterface.toString());
    }

    @Test
    public void testEqualsAndHashCode1() {
        // Create two instances of DataInterface with the same timestamp
        Date timestamp = new Date(System.currentTimeMillis());
        DataInterface dataInterface1 = new DataInterface(timestamp);
        DataInterface dataInterface2 = new DataInterface(timestamp);

        // Assert that the objects are considered equal and have the same hash code
        assertEquals(dataInterface1, dataInterface2);
        assertEquals(dataInterface1.hashCode(), dataInterface2.hashCode());
    }

    //Test C02SensorData
    @Test
    public void testConstructorAndGetters2() {
        // Create a Date object to use in the test
        Date timestamp = new Date(System.currentTimeMillis());
        int id = 1;
        Double Co2Value = 45.0;

        //Constructor with one parameter
        Co2SensorData co2SensorData = new Co2SensorData(Co2Value);

        assertNotNull(co2SensorData.getTimestamp());

        // Assert that the specific fields are set correctly
        assertEquals(Co2Value, co2SensorData.getcO2value());

        //Constructor with two parameters
        Co2SensorData co2SensorData1 = new Co2SensorData(timestamp, Co2Value);
        assertNotNull(co2SensorData1.getTimestamp());
        // Assert that the inherited fields are set correctly
        assertEquals(timestamp, co2SensorData1.getTimestamp());

        // Assert that the specific field is set correctly
        assertEquals(Co2Value, co2SensorData1.getcO2value());

        //Constructor with three parameters
        Co2SensorData co2SensorData2 = new Co2SensorData(id, timestamp, Co2Value);
        // Assert that the superclass constructor is called
        assertNotNull(co2SensorData2.getTimestamp());

        // Assert that the specific fields are set correctly
        assertEquals(id, co2SensorData2.getId());
        assertEquals(Co2Value, co2SensorData2.getcO2value());
    }

    @Test
    public void testSetters2() {
        // Create an instance of Co2SensorData using the default constructor
        Co2SensorData co2SensorData = new Co2SensorData();

        // Create a Date object to use in the test
        Date timestamp = new Date(System.currentTimeMillis());
        Double cO2value = 42.0;

        // Set the inherited field using the setter method from DataInterface
        co2SensorData.setTimestamp(timestamp);

        // Set the specific field using the setter method from Co2SensorData
        co2SensorData.setcO2value(cO2value);

        // Assert that the inherited field is set correctly
        assertEquals(timestamp, co2SensorData.getTimestamp());

        // Assert that the specific field is set correctly
        assertEquals(cO2value, co2SensorData.getcO2value());
    }

    @Test
    public void testToString2() {
        // Create an instance of Co2SensorData using the default constructor
        Co2SensorData co2SensorData = new Co2SensorData();

        // Assert that the toString method doesn't return null
        assertNotNull(co2SensorData.toString());
    }

    @Test
    public void testEqualsAndHashCode2() {
        // Create two instances of Co2SensorData with the same values
        Date timestamp = new Date(System.currentTimeMillis());
        Double cO2value = 42.0;
        Co2SensorData co2SensorData1 = new Co2SensorData(timestamp, cO2value);
        Co2SensorData co2SensorData2 = new Co2SensorData(timestamp, cO2value);

        // Assert that the objects are considered equal and have the same hash code
        assertEquals(co2SensorData1, co2SensorData2);
        assertEquals(co2SensorData1.hashCode(), co2SensorData2.hashCode());
    }

    //Test TemperaturData
    @Test
    public void testConstructorAndGetters3() {
        // Create a Date object to use in the test
        Date timestamp = new Date(System.currentTimeMillis());
        int id = 1;
        Double temperaturValue = 25.0;

        //Constructor with one parameter
        TemperatureData temperaturData = new TemperatureData(temperaturValue);

        assertNotNull(temperaturData.getTimestamp());

        // Assert that the specific fields are set correctly
        assertEquals(temperaturValue, temperaturData.getTemperatureValue());

        //Constructor with two parameters
        TemperatureData temperaturData1 = new TemperatureData(timestamp, temperaturValue);
        assertNotNull(temperaturData1.getTimestamp());
        // Assert that the inherited fields are set correctly
        assertEquals(timestamp, temperaturData1.getTimestamp());

        // Assert that the specific field is set correctly
        assertEquals(temperaturValue, temperaturData1.getTemperatureValue());

        //Constructor with three parameters
        TemperatureData temperaturData2 = new TemperatureData(id, timestamp, temperaturValue);
        // Assert that the superclass constructor is called
        assertNotNull(temperaturData2.getTimestamp());

        // Assert that the specific fields are set correctly
        assertEquals(id, temperaturData2.getId());
        assertEquals(temperaturValue, temperaturData2.getTemperatureValue());
    }

    @Test
    public void testSetters3() {
        // Create an instance of TemperaturData using the default constructor
        TemperatureData temperaturData = new TemperatureData();

        // Create a Date object to use in the test
        Date timestamp = new Date(System.currentTimeMillis());
        Double temperaturValue = 25.0;

        // Set the inherited field using the setter method from DataInterface
        temperaturData.setTimestamp(timestamp);

        // Set the specific field using the setter method from TemperaturData
        temperaturData.setTemperatureValue(temperaturValue);

        // Assert that the inherited field is set correctly
        assertEquals(timestamp, temperaturData.getTimestamp());

        // Assert that the specific field is set correctly
        assertEquals(temperaturValue, temperaturData.getTemperatureValue());
    }

    @Test
    public void testToString3() {
        // Create an instance of TemperaturData using the default constructor
        TemperatureData temperaturData = new TemperatureData();

        // Assert that the toString method doesn't return null
        assertNotNull(temperaturData.toString());
    }

    @Test
    public void testEqualsAndHashCode3() {
        // Create two instances of TemperaturData with the same values
        Date timestamp = new Date(System.currentTimeMillis());
        Double temperaturValue = 25.0;
        TemperatureData temperaturData1 = new TemperatureData(timestamp, temperaturValue);
        TemperatureData temperaturData2 = new TemperatureData(timestamp, temperaturValue);

        // Assert that the objects are considered equal and have the same hash code
        assertEquals(temperaturData1, temperaturData2);
        assertEquals(temperaturData1.hashCode(), temperaturData2.hashCode());
    }

}

