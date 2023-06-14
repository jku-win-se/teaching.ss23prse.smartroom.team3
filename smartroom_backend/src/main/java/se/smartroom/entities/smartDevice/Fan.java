package se.smartroom.entities.smartDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Fan extends SmartDevice {
    @Id
    @GeneratedValue
    private int id;

    public Fan() {
    }

    public Fan(boolean on) {
        super(on);
    }

    public Fan(int id, boolean on) {
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
        return "Fan{" +
                "id=" + id +
                ", on=" + this.isOn() +
                '}';
    }
}
