package se.smartroom.entities.physicalDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Window {
    @Id
    @GeneratedValue
    private int id;

    private boolean open;

    public Window() {
    }

    public Window(boolean open) {
        this.open = open;
    }

    public Window(int id, boolean open) {
        this.open = open;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "Window{" +
                "id=" + id +
                ", open=" + open +
                '}';
    }
}
