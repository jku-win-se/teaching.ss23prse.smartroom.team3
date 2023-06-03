package se.smartroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import se.smartroom.entities.environment.EnvironmentData;
import se.smartroom.entities.environment.SEASONSTATUS;
import se.smartroom.repositories.EnvironmentDataRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EnvironmentDataService {

    @Autowired
    private EnvironmentDataRepository repository;

    public EnvironmentData saveEnvironment(EnvironmentData environmentData) {
        return repository.save(environmentData);
    }

    public EnvironmentData removeEnvironment(int id) {
        EnvironmentData environmentData = repository.findById(id).orElse(null);

        if (environmentData != null) {
            repository.delete(environmentData);
        }

        return environmentData;
    }

    public List<EnvironmentData> getEnvironments() {
        return repository.findAll();
    }

    public EnvironmentData getEnvironmentById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    public void scheduledIntervalCalculation() {
        EnvironmentData environment;

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println(time.format(formatter));

        List<EnvironmentData> environmentDataList = repository.findAll();
        if (environmentDataList.isEmpty()) {

            EnvironmentData environmentData = new EnvironmentData(
                    22.0,
                    time.format(formatter),
                    SEASONSTATUS.SUMMER
            );
            environment = repository.save(environmentData);
        } else {
            environment = environmentDataList.get(0);
        }

        LocalTime newTimeOfTheDay = LocalTime.parse(environment.getTimeOfTheDay());
        newTimeOfTheDay = newTimeOfTheDay.plusMinutes(30);
        environment.setTimeOfTheDay(newTimeOfTheDay.toString());

        int hour = newTimeOfTheDay.getHour();
        if (hour >= 0 && hour < 6) {
            environment.setOutsideTemperature(Math.max(environment.getOutsideTemperature() - 1.0, 12.0));
        } else if (hour >= 6 && hour < 18) {
            environment.setOutsideTemperature(Math.min(environment.getOutsideTemperature() + 1.0, 32.0));
        } else if (hour >= 18 && hour <= 23) {
            environment.setOutsideTemperature(Math.max(environment.getOutsideTemperature() - 1.0, 12.0));
        }

        repository.save(environment);

        System.out.println("Environment Data updated");
        System.out.println(environment.toString());
    }

}
