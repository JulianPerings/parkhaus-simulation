package de.hbrs.team89.se1_starter_repo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;

public class JSONDurationChart extends JsonStatististicsTemplate{
    public JSONDurationChart(ArrayList<Car> cars) {
        super(cars);
    }

    @Override
    protected JsonArrayBuilder addJson(JsonArrayBuilder b) {
        return b;

    }

    @Override
    protected JsonArrayBuilder buildJson(JsonArrayBuilder b) {
        JsonObject root = Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("x", carsAsNrArray())
                                        .add("y", carsAsDurationArray())
                                        .add("type", "bar")
                                        .add("name", "Duration")
                                )
                ).build();
        return Json.createArrayBuilder().add(root);
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
}
