package de.hbrs.team13.parkhaus_team13;

import javax.json.JsonArrayBuilder;
import java.util.ArrayList;

public class VehicleTypeChart extends TypeChartTemplate{
    String[] vehicleTypes = {"SUV", "PKW", "MOTORBIKE", "E_VEHICLE"};

    VehicleTypeChart(ArrayList<Car> cars) {
        super(cars);
    }

    @Override
    protected JsonArrayBuilder addJson(JsonArrayBuilder b) {
        for (String clientCat : vehicleTypes) {
            b.add(clientCat);
        }
        return b;
    }

    @Override
    int[] getCounter() {
        int[] counter = {0, 0, 0, 0};

        for(int i = 0; i < 4; i++){
            String type = vehicleTypes[i];
            counter[i] = (int) cars.stream().map(Car::getVehicleType).filter(e -> e.equals(type)).count();
        }

        return counter;
    }
}
