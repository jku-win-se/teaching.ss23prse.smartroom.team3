package se.smartroom.entities.physicalDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Fenster extends PhysicalDevice {

    @Id
    @GeneratedValue
    private int id;

    public Fenster() {
    }

    public Fenster(boolean open) {
        super(open);
    }

    public Fenster(int id, boolean open) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Fenster fenster = (Fenster) o;
        return id == fenster.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Window{" +
                "id=" + id +
                "open=" + super.isOpen() +
                '}';
    }
}
