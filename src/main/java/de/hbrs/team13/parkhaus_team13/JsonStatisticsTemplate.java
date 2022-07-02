package de.hbrs.team13.parkhaus_team13;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.util.List;

public abstract class JsonStatisticsTemplate {
  protected List<Car> cars;

  public JsonStatisticsTemplate(List<Car> cars) {
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
