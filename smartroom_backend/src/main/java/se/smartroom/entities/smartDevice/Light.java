package se.smartroom.entities.smartDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Light {

    @Id
    @GeneratedValue
    private int id;

    private boolean isOn;

    public Light() {
    }

    public Light(boolean isOn) {
        this.isOn = isOn;
    }

    public Light(int id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsOn() {
        return isOn;
    }

    public void setIsOn(boolean on) {
        this.isOn = on;
    }
}
