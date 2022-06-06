package de.hbrs.team89.se1_starter_repo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Flow;

public class Statistics extends Observable {
    ArrayList<Car> cars = new ArrayList<>();
    String[] vehicleTypes = {"SUV", "PKW", "MOTORBIKE", "E_VEHICLE"};
    String[] clientCategory = {"FAMILY", "WOMEN", "ANY", "HANDICAPPED"};

    ArrayList<Car> getState(){
        return cars;
    }
    void addCar(Car car) {
        cars.add(car);
        setChanged();
        notifyObservers(cars);
    }

    ArrayList<Car> getCarList() {
        return cars;
    }

    /**Returns the sum of all prices in cents.*/
    double getSum() {
        return cars.stream().map(Car::getPrice).reduce( 0., (x, y) -> x + y );
    }

    /**Return the average price in cents cut after 2 decimal places.  */
    double getAvg() {
        return cars.size() == 0 ? 0 : Math.floor((getSum() / cars.size()) * 100) / 100;
    }

    double getTime() {
        return cars.stream().map(Car::getDuration).reduce( 0, (x, y) -> x + y );
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



    int[] countVehicleType() {
        int[] counterVehicleTypes = {0, 0, 0, 0};

        for(int i = 0; i < 4; i++){
            String type = vehicleTypes[i];
            counterVehicleTypes[i] = (int) cars.stream().map(Car::getVehicleType).filter(e -> e.equals(type)).count();
        }

        return counterVehicleTypes;
    }

    Car getMin(){
        Comparator<Car> c = (s1, s2) -> s1.getDuration() - s2.getDuration();
        Car min = cars.stream().reduce((c1, c2) -> c.compare(c1, c2) <= 0 ? c1 : c2).orElse(null);
        return min;
    }

    Car getMax(){
        Comparator<Car> c = (s1, s2) -> s1.getDuration() - s2.getDuration();
        Car max = cars.stream().reduce((c1, c2) -> c.compare(c1, c2) >= 0 ? c1 : c2).orElse(null);
        return max;
    }

    String generateClientCategoryChart() {
        /*
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
        */
         ClientTypeChart chart = new ClientTypeChart(cars);
         return chart.createJSON().build().get(0).toString();
    }

    String generateVehicleTypeChart() {
        /*
        JsonArrayBuilder vehicleTypeBuilder = Json.createArrayBuilder();
        for (String vehicleType : vehicleTypes) {
            vehicleTypeBuilder.add(vehicleType);
        }

        int[] counter = countVehicleType();
        int total = Arrays.stream(counter).sum();
        JsonArrayBuilder percentageVehicleTypeBuilder = Json.createArrayBuilder();
        for (int vehicleTypeCount : counter) {
            percentageVehicleTypeBuilder.add( (float) vehicleTypeCount / total * 100);
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
        */
        VehicleTypeChart chart = new VehicleTypeChart(cars);
        return chart.createJSON().build().get(0).toString();
    }

    public String generateDurationChart() {
        /*
        JsonObject root = Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("x", carsAsNrArray())
                                        .add("y", carsAsDurationArray())
                                        .add("type", "bar")
                                        .add("name", "Duration")
                                )
                        .add(Json.createObjectBuilder()
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
                        )


                ).build();
        return root.toString();*/
        JSONDurationChart chart = new JSONDurationChart(cars);
        return chart.createJSON().build().get(0).toString();
    }



    public String generateBeginHeatmap() {
        /*
        JsonArrayBuilder data = Json.createArrayBuilder();

        for (String hour : hours) {
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
        return root.toString();*/
        JSONHeatmap h = new JSONHeatmap(cars);
        return h.createJSON().build().get(0).toString();
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
