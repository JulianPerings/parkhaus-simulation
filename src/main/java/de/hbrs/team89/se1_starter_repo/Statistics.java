package de.hbrs.team89.se1_starter_repo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.util.*;

public class Statistics extends Observable {
    private ArrayList<Car> cars = new ArrayList<>();
    String[] vehicleTypes = {"SUV", "PKW", "MOTORBIKE", "E_VEHICLE"};
    String[] clientCategory = {"FAMILY", "WOMEN", "ANY", "HANDICAPPED"};
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
         ClientTypeChart chart = new ClientTypeChart(cars);
         return chart.createJSON().build().get(0).toString();
    }

    String generateVehicleTypeChart() {
        VehicleTypeChart chart = new VehicleTypeChart(cars);
        return chart.createJSON().build().get(0).toString();
    }

    public String generateDurationChart() {
        JSONDurationChart chart = new JSONDurationChart(cars);
        return chart.createJSON().build().get(0).toString();
    }



    public String generateBeginHeatmap() {
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
