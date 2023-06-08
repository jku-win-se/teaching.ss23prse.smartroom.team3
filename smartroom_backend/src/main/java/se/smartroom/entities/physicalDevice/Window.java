package se.smartroom.entities.physicalDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Window extends PhysicalDevice {
    @Id
    @GeneratedValue
    private int id;

    public Window() {
    }

    public Window(boolean open) {
        super(open);
    }

    public Window(int id, boolean open) {
        super(open);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Window{" +
                "id=" + id +
                ", open=" + isOpen() +
                '}';
    }
}
