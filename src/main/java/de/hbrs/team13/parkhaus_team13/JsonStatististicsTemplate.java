package de.hbrs.team13.parkhaus_team13;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.util.List;

public abstract class JsonStatististicsTemplate {
  protected List<Car> cars;

  public JsonStatististicsTemplate(List<Car> cars) {
    this.cars = cars;
  }

  public final JsonArrayBuilder createJSON() {
    JsonArrayBuilder builder = Json.createArrayBuilder();
    builder = addJson(builder);
    return buildJson(builder);
  }

  protected abstract JsonArrayBuilder addJson(JsonArrayBuilder b);

  protected abstract JsonArrayBuilder buildJson(JsonArrayBuilder b);
}
