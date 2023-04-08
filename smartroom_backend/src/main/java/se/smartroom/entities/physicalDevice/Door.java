package se.smartroom.entities.physicalDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Door extends PhysicalDevice {

    @Id
    @GeneratedValue
    private int id;

    public Door() {
    }

    public Door(boolean open) {
        super(open);
    }

    public Door(int id, boolean open) {
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
        Door door = (Door) o;
        return id == door.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Door{" +
                "id=" + id +
                "open=" + super.isOpen() +
                '}';
    }
}
