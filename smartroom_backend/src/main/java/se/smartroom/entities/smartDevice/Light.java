package se.smartroom.entities.smartDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Light extends SmartDevice {

    @Id
    @GeneratedValue
    private int id;

    public Light() {
    }

    public Light(boolean open) {
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
        Light light = (Light) o;
        return id == light.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Light{" +
                "id=" + id +
                "on=" + super.isOn() +
                '}';
    }
}
