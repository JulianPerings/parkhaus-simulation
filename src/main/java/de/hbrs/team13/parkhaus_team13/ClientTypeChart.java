package de.hbrs.team13.parkhaus_team13;

import javax.json.JsonArrayBuilder;
import java.util.List;

public class ClientTypeChart extends TypeChartTemplate {

  String[] clientCategory = {"FAMILY", "WOMEN", "ANY", "HANDICAPPED"};

  ClientTypeChart(List<Car> cars) {
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
    for (int i = 0; i < 4; i++) {
      String category = clientCategory[i];
      counter[i] =
          (int) cars.stream().map(Car::getClientCategory).filter(e -> e.equals(category)).count();
    }
    return counter;
  }
}
