package de.hbrs.team89.se1_starter_repo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {
    ArrayList<Car> cars = new ArrayList<>();
    String[] vehicleTypes = {"SUV", "PKW", "MOTORBIKE", "E_VEHICLE"};
    String[] clientCategory = {"FAMILY", "WOMEN", "ANY", "HANDICAPPED"};
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] hours = {"00:00am", "01:00am", "02:00am", "03:00am",
            "04:00am", "05:00am", "06:00am", "07:00am",
            "08:00am", "09:00am", "10:00am", "11:00am",
            "00:00pm", "01:00pm", "02:00pm", "03:00pm",
            "04:00pm", "05:00pm", "06:00pm", "07:00pm",
            "08:00pm", "09:00pm", "10:00pm", "11:00pm"};

    void addCar(Car car) {
        cars.add(car);
    }

    ArrayList<Car> getCarList() {
        return cars;
    }

    double getSum() {
        double sum = 0;
        for (Car car : cars) {
            sum += car.getPrice();
        }
        return Math.floor(sum * 100) / 100;
    }

    double getAvg() {
        return cars.size() == 0 ? 0 : Math.floor((getSum() / cars.size()) * 100) / 100;
    }

    double getTime() {
        double time = 0;
        for (Car car : cars) {
            time += car.getDuration();
        }
        return time;
    }

    int getAvgTime() {
        return cars.size() == 0 ? 0 : (int) (getTime() / cars.size());
    }

    String[] getVehicleTypes() {
        return vehicleTypes;
    }

    String[] getClientCategory() {
        return clientCategory;
    }

    int[] countClientCategoryTypes() {
        int[] counterClientCategory = {0, 0, 0, 0};
        if (cars.size() > 0) {
            for (CarIF car : cars) {
                for (int i = 0; i < clientCategory.length; i++) {
                    if (car.getClientCategory().equals(clientCategory[i])) {
                        counterClientCategory[i]++;
                    }
                }
            }
        }
        return counterClientCategory;
    }

    int[] countVehicleType() {
        int[] counterVehicleTypes = {0, 0, 0, 0};
        if (cars.size() > 0) {
            for (CarIF car : cars) {
                for (int i = 0; i < vehicleTypes.length; i++) {
                    if (car.getVehicleType().equals(vehicleTypes[i])) {
                        counterVehicleTypes[i]++;
                    }
                }
            }
        }
        return counterVehicleTypes;
    }


    String generateClientCategoryChart() {
        JsonArrayBuilder clientCategoryBuilder = Json.createArrayBuilder();
        for (String clientCat : clientCategory) {
            clientCategoryBuilder.add(clientCat);
        }

        int[] counter = countClientCategoryTypes();
        int total = Arrays.stream(counter).sum();
        JsonArrayBuilder percentageClientCategoryBuilder = Json.createArrayBuilder();
        for (int clientCategoryCount : counter) {
            percentageClientCategoryBuilder.add((float) clientCategoryCount / total * 100);
        }

        JsonObject clientCategoryRoot = Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("values", percentageClientCategoryBuilder)
                                .add("labels", clientCategoryBuilder)
                                .add("type", "pie")
                        )
                ).build();
        return clientCategoryRoot.toString();
    }

    String generateVehicleTypeChart() {
        JsonArrayBuilder vehicleTypeBuilder = Json.createArrayBuilder();
        for (String vehicleType : vehicleTypes) {
            vehicleTypeBuilder.add(vehicleType);
        }

        int[] counter = countVehicleType();
        int total = Arrays.stream(counter).sum();
        JsonArrayBuilder percentageVehicleTypeBuilder = Json.createArrayBuilder();
        for (int vehicleTypeCount : counter) {
            percentageVehicleTypeBuilder.add((float) vehicleTypeCount / total * 100);
        }

        JsonObject vehicleTypeRoot = Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("values", percentageVehicleTypeBuilder)
                                .add("labels", vehicleTypeBuilder)
                                .add("type", "pie")
                        )
                ).build();
        return vehicleTypeRoot.toString();
    }

    public String generateDurationChart() {
        JsonObject root = Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("x", carsAsNrArray())
                                        .add("y", carsAsDurationArray())
                                        .add("type", "bar")
                                        .add("name", "Duration")
                                )
                    /*    .add(Json.createObjectBuilder()
                                .add("x", carsAsNrArray())
                                .add("y", carsAsBeginArray())
                                .add("type", "bar")
                                .add("name", "Begin")
                        )
                        .add(Json.createObjectBuilder()
                                .add("x",carsAsNrArray())
                                .add("y", carsAsEndArray())
                                .add("type", "bar")
                                .add("name", "End")
                        )*/
                ).build();
        return root.toString();
    }

    public int countCarsForHourAndDay(String hour, String day) {
        int counterCarsForHourAndDay = 0;
        for (Car car :
                cars) {
            long begin = car.begin();
            Timestamp timestamp = new Timestamp(begin);
            LocalDateTime localDateTime = timestamp.toLocalDateTime();
            int indexOfHour = Arrays.asList(hours).indexOf(hour);
            int carBeginHour = localDateTime.getHour();
            int indexOfDay = Arrays.asList(days).indexOf(day);
            DayOfWeek carBeginDay = localDateTime.getDayOfWeek();
            if (indexOfHour == carBeginHour && carBeginDay.getValue() == indexOfDay + 1) {
                counterCarsForHourAndDay++;
            }
        }
        return counterCarsForHourAndDay;
    }

    public String generateBeginHeatmap() {
        JsonArrayBuilder data = Json.createArrayBuilder();

        for (String hour :
                hours) {
            JsonArrayBuilder hourBuilder = Json.createArrayBuilder();
            for (String day :
                    days) {
                hourBuilder.add(countCarsForHourAndDay(hour, day));
            }
            data.add(hourBuilder);
        }

        JsonObject root = Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("z", data)
                                .add("x", daysAsArray())
                                .add("y", hoursAsArray())
                                .add("type", "heatmap")
                        )
                ).build();
        return root.toString();
    }

    private JsonArrayBuilder daysAsArray() {
        JsonArrayBuilder daysArray = Json.createArrayBuilder();
        for (String day :
                days) {
            daysArray.add(day);
        }
        return daysArray;
    }

    private JsonArrayBuilder hoursAsArray() {
        JsonArrayBuilder hoursArray = Json.createArrayBuilder();
        for (String hour :
                hours) {
            hoursArray.add(hour);
        }
        return hoursArray;
    }

    private JsonArrayBuilder carsAsNrArray() {
        JsonArrayBuilder nrArray = Json.createArrayBuilder();
        for (Car car :
                cars) {
            nrArray.add(car.nr());
        }
        return nrArray;
    }

    private JsonArrayBuilder carsAsDurationArray() {
        JsonArrayBuilder durationArray = Json.createArrayBuilder();
        for (Car car :
                cars) {
            durationArray.add(car.getDuration());
        }
        return durationArray;
    }

    private JsonArrayBuilder carsAsBeginArray() {
        JsonArrayBuilder beginArray = Json.createArrayBuilder();
        for (Car car :
                cars) {
            beginArray.add(car.begin());
        }
        return beginArray;
    }

    private JsonArrayBuilder carsAsEndArray() {
        JsonArrayBuilder endArray = Json.createArrayBuilder();
        for (Car car :
                cars) {
            endArray.add(car.end());
        }
        return endArray;
    }
}
