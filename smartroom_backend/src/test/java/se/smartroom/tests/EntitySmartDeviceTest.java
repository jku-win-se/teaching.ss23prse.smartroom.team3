package se.smartroom.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import se.smartroom.entities.data.DataInterface;
import se.smartroom.entities.physicalDevice.Door;
import se.smartroom.entities.physicalDevice.PhysicalDevice;
import se.smartroom.entities.smartDevice.Fan;
import se.smartroom.entities.smartDevice.Light;
import se.smartroom.entities.smartDevice.SmartDevice;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class EntitySmartDeviceTest {
    @Test
    public void testIsOnAndSetOn() {
        // Create an instance of SmartDevice
        SmartDevice smartDevice = new SmartDevice();

        // Set a value for the 'on' property using the setOn() method
        boolean expectedOnValue = true;
        smartDevice.setOn(expectedOnValue);

        // Retrieve the value of the 'on' property using the isOn() method
        boolean actualOnValue = smartDevice.isOn();

        // Assert that the retrieved value matches the expected value
        assertEquals(expectedOnValue, actualOnValue);
    }

    @Test
    public void testToString() {
        // Create an instance of DataInterface using the default constructor
        SmartDevice smartDevice = new SmartDevice();

        // Assert that the toString method doesn't return null
        assertNotNull(smartDevice.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two instances of PhysicalDevice with the same open state
        SmartDevice device1 = new SmartDevice(true);
        SmartDevice device2 = new SmartDevice(true);

        // Assert that the instances are equal
        assertEquals(device1, device2);

        // Assert that the hash codes are equal
        assertEquals(device1.hashCode(), device2.hashCode());

        // Create an instance with a different open state
        SmartDevice device3 = new SmartDevice(false);

        // Assert that the instances are not equal
        assertNotEquals(device1, device3);

        // Assert that the hash codes are different
        assertNotEquals(device1.hashCode(), device3.hashCode());
    }

    //Test Fan.class
    @Test
    public void testConstructorAndGettersSetters2() {
        // Create an instance of Door using the constructor
        Fan fan = new Fan(true);

        // Assert that the isOpen method returns the expected value
        assertTrue(fan.isIsOn());

        // Set the open state using the setter method
        fan.setIsOn(false);

        // Assert that the isOpen method now returns the updated value
        assertFalse(fan.isIsOn());

        //Constructor with two parameters
        Fan fan2 = new Fan(2,true);
        assertEquals(fan2.getId(),2);
        assertEquals(fan2.isIsOn(),true);
    }

    @Test
    public void testGetIdAndSetId1() {
        // Create an instance of MyClass
        Fan fan = new Fan(true);

        // Set a value for the ID using the setId() method
        int expectedId = 123;
        fan.setId(expectedId);

        // Retrieve the ID using the getId() method
        int actualId = fan.getId();

        // Assert that the retrieved ID matches the expected ID
        assertEquals(expectedId, actualId);
    }

    @Test
    public void FanTestToString() {
        // Create an instance of DataInterface using the default constructor
        Fan device = new Fan();

        // Assert that the toString method doesn't return null
        assertNotNull(device.toString());
    }

    //Test Light.class
    @Test
    public void testConstructorAndGettersSetters3() {
        // Create an instance of Door using the constructor
        Light light = new Light(true);

        // Assert that the isOpen method returns the expected value
        assertTrue(light.isIsOn());

        // Set the open state using the setter method
        light.setIsOn(false);

        // Assert that the isOpen method now returns the updated value
        assertFalse(light.isIsOn());

        //Constructor with two parameters
        Light light2 = new Light(2,true);
        assertEquals(light2.getId(),2);
        assertEquals(light2.isIsOn(),true);
    }

    @Test
    public void testGetIdAndSetId2() {
        // Create an instance of MyClass
        Light light = new Light(true);

        // Set a value for the ID using the setId() method
        int expectedId = 123;
        light.setId(expectedId);

        // Retrieve the ID using the getId() method
        int actualId = light.getId();

        // Assert that the retrieved ID matches the expected ID
        assertEquals(expectedId, actualId);
    }

    @Test
    public void LightTestToString() {
        // Create an instance of DataInterface using the default constructor
        Light light = new Light();

        // Assert that the toString method doesn't return null
        assertNotNull(light.toString());
    }

}
