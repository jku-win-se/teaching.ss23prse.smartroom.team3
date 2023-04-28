package se.smartroom.entities.physicalDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Door {

    @Id
    @GeneratedValue
    private int id;

    private boolean open;

    public Door() {
    }

    public Door(boolean open) {
        this.open = open;
    }

    public Door(int id, boolean open) {
        this.id = id;
        this.open = open;
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
}
