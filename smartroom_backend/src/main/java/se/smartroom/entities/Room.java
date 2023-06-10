package se.smartroom.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import se.smartroom.entities.data.TemperatureData;
import se.smartroom.entities.data.Co2SensorData;
import se.smartroom.entities.people.PeopleData;
import se.smartroom.entities.physicalDevice.Door;
import se.smartroom.entities.physicalDevice.Fenster;
import se.smartroom.entities.smartDevice.Fan;
import se.smartroom.entities.smartDevice.Light;
import se.smartroom.repositories.RoomRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int size;

    // Physical devices of the room
    @OneToMany(cascade = CascadeType.ALL)
    private List<Door> doors;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Fenster> roomWindows;

    // Smart devices of the room
    @OneToMany(cascade = CascadeType.ALL)
    private List<Light> lights;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Fan> fans;

    // Sensor Data of the room
    @OneToMany(cascade = CascadeType.ALL)
    private List<Co2SensorData> co2SensorData;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TemperatureData> temperatureData;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PeopleData> peopleData;

    public Room(Class<RoomRepository> roomRepositoryClass) {
    }

    public Room(String name, int size) {
        this(name, size, Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public Room(String name, int size, List<Door> doors, List<Fenster> roomWindows, List<Light> lights,
                List<Fan> fans, List<Co2SensorData> co2SensorData, List<TemperatureData> temperaturData,
                List<PeopleData> peopleData
    ) {
        this.name = name;
        this.size = size;
        this.doors = doors;
        this.roomWindows = roomWindows;
        this.lights = lights;
        this.fans = fans;
        this.co2SensorData = co2SensorData;
        this.temperatureData = temperaturData;
        this.peopleData = peopleData;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    @Transactional
    public List<Fenster> getRoomWindows() {
        return roomWindows;
    }

    public void setRoomWindows(List<Fenster> fensters) {
        this.roomWindows = fensters;
    }

    public List<Light> getLights() {
        return lights;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
    }

    public List<Fan> getFans() {
        return fans;
    }

    public void setFans(List<Fan> fans) {
        this.fans = fans;
    }

    public List<Co2SensorData> getCo2SensorData() {
        return co2SensorData;
    }

    public void setCo2SensorData(List<Co2SensorData> co2SensorData) {
        this.co2SensorData = co2SensorData;
    }

    public List<TemperatureData> getTemperatureData() {
        return temperatureData;
    }

    public void setTemperatureData(List<TemperatureData> temperatureData) {
        this.temperatureData = temperatureData;
    }

    public List<PeopleData> getPeopleData() {
        return peopleData;
    }

    public void setPeopleData(List<PeopleData> peopleData) {
        this.peopleData = peopleData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && size == room.size && Objects.equals(name, room.name) && Objects.equals(doors, room.doors) && Objects.equals(roomWindows, room.roomWindows) && Objects.equals(lights, room.lights) && Objects.equals(fans, room.fans) && Objects.equals(co2SensorData, room.co2SensorData) && Objects.equals(temperatureData, room.temperatureData) && Objects.equals(peopleData, room.peopleData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, size, doors, roomWindows, lights, fans, co2SensorData, temperatureData, peopleData);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", doors=" + doors +
                ", roomWindows=" + roomWindows +
                ", lights=" + lights +
                ", fans=" + fans +
                ", co2SensorData=" + co2SensorData +
                ", temperaturData=" + temperatureData +
                ", peopleData=" + peopleData +
                '}';
    }

}
