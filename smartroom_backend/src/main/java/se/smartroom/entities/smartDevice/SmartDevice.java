package se.smartroom.entities.smartDevice;

import java.util.Objects;

public class SmartDevice {
    private boolean on;

    public SmartDevice() {
    }

    public SmartDevice(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartDevice that = (SmartDevice) o;
        return on == that.on;
    }

    @Override
    public int hashCode() {
        return Objects.hash(on);
    }

    @Override
    public String toString() {
        return "SmartDevice{" +
                "on=" + on +
                '}';
    }
}
