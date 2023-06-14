package se.smartroom.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import se.smartroom.entities.smartDevice.Fan;
import se.smartroom.entities.smartDevice.Light;
import se.smartroom.entities.smartDevice.SmartDevice;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class EntitySmartDeviceTest {

    // Testing SmartDevice class

    @Test
    public void testSmartDeviceConstructor() {
        // Arrange
        boolean expectedOn = true;

        // Act
        SmartDevice smartDevice = new SmartDevice(expectedOn);

        // Assert
        assertEquals(expectedOn, smartDevice.isOn());
    }

    @Test
    public void testSmartDeviceEqualsAndHashCode() {
        // Create two instances of SmartDevice with the same on state
        SmartDevice device1 = new SmartDevice(true);
        SmartDevice device2 = new SmartDevice(true);

        // Assert that the instances are equal
        assertEquals(device1, device2);

        // Assert that the hash codes are equal
        assertEquals(device1.hashCode(), device2.hashCode());

        // Create an instance with a different on state
        SmartDevice device3 = new SmartDevice(false);

        // Assert that the instances are not equal
        assertNotEquals(device1, device3);

        // Assert that the hash codes are different
        assertNotEquals(device1.hashCode(), device3.hashCode());
    }

    @Test
    public void testSmartDeviceToString() {
        // Create an instance of SmartDevice
        SmartDevice smartDevice = new SmartDevice(true);

        // Assert that the toString method returns the expected string representation
        assertEquals("SmartDevice{on=true}", smartDevice.toString());
    }

    // Testing Light class

    @Test
    public void testLightConstructor() {
        // Arrange
        int expectedId = 1;
        boolean expectedOn = true;

        // Act
        Light light = new Light(expectedId, expectedOn);

        // Assert
        assertEquals(expectedId, light.getId());
        assertEquals(expectedOn, light.isOn());
    }

    @Test
    public void testLightToString() {
        // Create an instance of Light
        Light light = new Light(1, true);

        // Assert that the toString method returns the expected string representation
        assertEquals("Light{id=1, on=true}", light.toString());
    }

    @Test
    public void testLightGetIdAndSetId() {
        // Create an instance of Light
        Light light = new Light(1, true);

        // Set a value for the ID using the setId() method
        int expectedId = 123;
        light.setId(expectedId);

        // Retrieve the ID using the getId() method
        int actualId = light.getId();

        // Assert that the retrieved ID matches the expected ID
        assertEquals(expectedId, actualId);
    }

    // Testing Fan class

    @Test
    public void testFanConstructor() {
        // Arrange
        int expectedId = 1;
        boolean expectedOn = false;

        // Act
        Fan fan = new Fan(expectedId, expectedOn);

        // Assert
        assertEquals(expectedId, fan.getId());
        assertEquals(expectedOn, fan.isOn());
    }

    @Test
    public void testFanToString() {
        // Create an instance of Fan
        Fan fan = new Fan(1, false);

        // Assert that the toString method returns the expected string representation
        assertEquals("Fan{id=1, on=false}", fan.toString());
    }

    @Test
    public void testFanGetIdAndSetId() {
        // Create an instance of Fan
        Fan fan = new Fan(1, false);

        // Set a value for the ID using the setId() method
        int expectedId = 123;
        fan.setId(expectedId);

        // Retrieve the ID using the getId() method
        int actualId = fan.getId();

        // Assert that the retrieved ID matches the expected ID
        assertEquals(expectedId, actualId);
    }

}
