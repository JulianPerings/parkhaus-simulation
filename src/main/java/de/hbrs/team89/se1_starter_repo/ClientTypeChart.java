package de.hbrs.team89.se1_starter_repo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientTypeChart extends TypeChartTemplate{

    String[] clientCategory = {"FAMILY", "WOMEN", "ANY", "HANDICAPPED"};

    ClientTypeChart(ArrayList<Car> cars) {
        super(cars);
    }

    @Override
    protected JsonArrayBuilder addJson(JsonArrayBuilder b) {
        for (String clientCat : clientCategory) {
            b.add(clientCat);
        }
        return b;
    }

    @Override
    int[] getCounter() {
        int[] counter = {0, 0, 0, 0};
        for(int i = 0; i < 4; i++){
            String category = clientCategory[i];
            counter[i] = (int) cars.stream().map(Car::getClientCategory).filter(e -> e.equals(category)).count();
        }
        return counter;
    }
}
