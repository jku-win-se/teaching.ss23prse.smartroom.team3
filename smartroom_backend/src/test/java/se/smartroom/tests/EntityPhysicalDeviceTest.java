package se.smartroom.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import se.smartroom.entities.physicalDevice.Door;
import se.smartroom.entities.physicalDevice.Window;
import se.smartroom.entities.physicalDevice.PhysicalDevice;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EntityPhysicalDeviceTest {

    //Testing super class
    @Test
    public void testPhysicalDeviceConstructorAndGettersSetters() {
        // Create an instance of PhysicalDevice using the constructor
        PhysicalDevice physicalDevice = new PhysicalDevice(true);

        // Assert that the isOpen method returns the expected value
        assertTrue(physicalDevice.isOpen());

        // Set the open state using the setter method
        physicalDevice.setOpen(false);

        // Assert that the isOpen method now returns the updated value
        assertFalse(physicalDevice.isOpen());
    }

    @Test
    public void testPhysicalDeviceEqualsAndHashCode() {
        // Create two instances of PhysicalDevice with the same open state
        PhysicalDevice device1 = new PhysicalDevice(true);
        PhysicalDevice device2 = new PhysicalDevice(true);

        // Assert that the instances are equal
        assertEquals(device1, device2);

        // Assert that the hash codes are equal
        assertEquals(device1.hashCode(), device2.hashCode());

        // Create an instance with a different open state
        PhysicalDevice device3 = new PhysicalDevice(false);

        // Assert that the instances are not equal
        assertNotEquals(device1, device3);

        // Assert that the hash codes are different
        assertNotEquals(device1.hashCode(), device3.hashCode());
    }

    @Test
    public void testPhysicalDeviceToString() {
        // Create an instance of PhysicalDevice
        PhysicalDevice physicalDevice = new PhysicalDevice(true);

        // Assert that the toString method returns the expected string representation
        assertEquals("PhysicalDevice{open=true}", physicalDevice.toString());
    }

    //Testing Door.class
    @Test
    public void testDoorConstructorAndGettersSetters() {
        // Create an instance of Door using the constructor
        Door door = new Door(true);

        // Assert that the isOpen method returns the expected value
        assertTrue(door.isOpen());

        // Set the open state using the setter method
        door.setOpen(false);

        // Assert that the isOpen method now returns the updated value
        assertFalse(door.isOpen());
    }

    @Test
    public void testDoorToString() {
        // Create an instance of Door
        Door door = new Door(1, true);

        // Assert that the toString method returns the expected string representation
        assertEquals("Door{id=1, open=true}", door.toString());
    }

    @Test
    public void testDoorGetIdAndSetId() {
        // Create an instance of MyClass
        Door door = new Door(1, true);

        // Set a value for the ID using the setId() method
        int expectedId = 123;
        door.setId(expectedId);

        // Retrieve the ID using the getId() method
        int actualId = door.getId();

        // Assert that the retrieved ID matches the expected ID
        assertEquals(expectedId, actualId);
    }

    //Testing Window.class
    @Test
    public void testWindowConstructor() {
        // Create an instance of Door using the constructor
        Window window = new Window(true);

        // Assert that the isOpen method returns the expected value
        assertTrue(window.isOpen());

        // Set the open state using the setter method
        window.setOpen(false);

        // Assert that the isOpen method now returns the updated value
        assertFalse(window.isOpen());
    }

    @Test
    public void testWindowToString() {
        // Create an instance of Door
        Window window = new Window(1, true);

        // Assert that the toString method returns the expected string representation
        assertEquals("Window{id=1, open=true}", window.toString());
    }

    @Test
    public void testWindowGetIdAndSetId() {
        // Create an instance of MyClass
        Window window = new Window(1, true);

        // Set a value for the ID using the setId() method
        int expectedId = 123;
        window.setId(expectedId);

        // Retrieve the ID using the getId() method
        int actualId = window.getId();

        // Assert that the retrieved ID matches the expected ID
        assertEquals(expectedId, actualId);
    }
}
