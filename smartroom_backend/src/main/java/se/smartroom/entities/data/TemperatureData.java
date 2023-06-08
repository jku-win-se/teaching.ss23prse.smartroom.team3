package se.smartroom.entities.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Date;
import java.util.Objects;

@Entity
public class TemperatureData extends DataInterface {

    @Id
    @GeneratedValue
    private int id;

    private Double temperaturValue;

    public TemperatureData() {
    }

    public TemperatureData(Double temperaturValue) {
        super();
        this.temperaturValue = temperaturValue;
    }

    public TemperatureData(Date timestamp, Double temperaturValue) {
        super(timestamp);
        this.temperaturValue = temperaturValue;
    }

    public TemperatureData(int id, Date timestamp, Double temperaturValue) {
        super(timestamp);
        this.id = id;
        this.temperaturValue = temperaturValue;
    }

    public Double getTemperatureValue() {
        return temperaturValue;
    }

    public void setTemperatureValue(Double temperaturValue) {
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
        TemperatureData that = (TemperatureData) o;
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
