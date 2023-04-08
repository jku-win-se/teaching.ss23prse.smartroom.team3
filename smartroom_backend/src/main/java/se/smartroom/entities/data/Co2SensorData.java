package se.smartroom.entities.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Co2SensorData extends DataInterface {

    @Id
    @GeneratedValue
    private int id;

    private Double temperaturValue;

    public Co2SensorData() {
    }

    public Co2SensorData(Double temperaturValue) {
        super();
        this.temperaturValue = temperaturValue;
    }

    public Co2SensorData(Date timestamp, Double temperaturValue) {
        super(timestamp);
        this.temperaturValue = temperaturValue;
    }

    public Co2SensorData(int id, Date timestamp, Double temperaturValue) {
        super(timestamp);
        this.id = id;
        this.temperaturValue = temperaturValue;
    }

    public Double getTemperaturValue() {
        return temperaturValue;
    }

    public void setTemperaturValue(Double temperaturValue) {
        this.temperaturValue = temperaturValue;
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
        Co2SensorData that = (Co2SensorData) o;
        return id == that.id && Objects.equals(temperaturValue, that.temperaturValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, temperaturValue);
    }

    @Override
    public String toString() {
        return "Co2SensorData{" +
                "id=" + id +
                ", temperaturValue=" + temperaturValue +
                '}';
    }
}