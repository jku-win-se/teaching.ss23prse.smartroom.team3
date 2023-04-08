package se.smartroom.entities.smartDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Fan extends SmartDevice {

    @Id
    @GeneratedValue
    private int id;

    public Fan() {
    }

    public Fan(boolean open) {
        super(open);
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
        Fan fan = (Fan) o;
        return id == fan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Fan{" +
                "id=" + id +
                "on=" + super.isOn() +
                '}';
    }
}
