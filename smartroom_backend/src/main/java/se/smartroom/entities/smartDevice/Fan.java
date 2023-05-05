package se.smartroom.entities.smartDevice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Fan {

    @Id
    @GeneratedValue
    private int id;

    private boolean isOn;

    public Fan() {
    }

    public Fan(boolean isOn) {
        this.isOn = isOn;
    }

    public Fan(int id, boolean isOn) {
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

    @Override
    public String toString() {
        return "Fan{" +
                "id=" + id +
                ", isOn=" + isOn +
                '}';
    }
}
