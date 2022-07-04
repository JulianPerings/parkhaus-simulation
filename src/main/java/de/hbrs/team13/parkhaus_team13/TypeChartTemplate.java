package de.hbrs.team13.parkhaus_team13;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Arrays;
import java.util.List;

public abstract class TypeChartTemplate extends JsonStatisticsTemplate {

  TypeChartTemplate(List<Car> cars) {
    super(cars);
  }

  abstract int[] getCounter();

  @Override
  protected JsonArrayBuilder buildJson(JsonArrayBuilder b) {
    int[] counter = getCounter();
    int total = Arrays.stream(counter).sum();
    JsonArrayBuilder percentageBuilder = Json.createArrayBuilder();
    for (int clientCategoryCount : counter) {
      percentageBuilder.add((float) clientCategoryCount / total * 100);
    }

    JsonObject jObject =
        Json.createObjectBuilder()
            .add(
                "data",
                Json.createArrayBuilder()
                    .add(
                        Json.createObjectBuilder()
                            .add("values", percentageBuilder)
                            .add("labels", b)
                            .add("type", "pie")))
            .build();

    return Json.createArrayBuilder().add(jObject);
  }
}
