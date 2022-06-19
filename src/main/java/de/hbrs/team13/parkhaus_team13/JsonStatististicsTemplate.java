package de.hbrs.team13.parkhaus_team13;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.util.ArrayList;

public abstract class JsonStatististicsTemplate {
    protected ArrayList<Car> cars;
    public JsonStatististicsTemplate(ArrayList<Car> cars){
        this.cars = cars;
    }
    final public JsonArrayBuilder createJSON(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        builder = addJson(builder);
        return buildJson(builder);
    }

    protected abstract JsonArrayBuilder addJson(JsonArrayBuilder b);
    protected abstract JsonArrayBuilder buildJson(JsonArrayBuilder b);

}
