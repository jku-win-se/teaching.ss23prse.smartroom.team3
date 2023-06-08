package se.smartroom.entities.smartDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Light extends SmartDevice {
    @Id
    @GeneratedValue
    private int id;

    public Light() {
    }

    public Light(boolean on) {
        super(on);
    }

    public Light(int id, boolean on) {
        super(on);
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
        return "Light{" +
                "id=" + id +
                ", on=" + isOn() +
                '}';
    }
}
