package de.hbrs.team13.parkhaus_team13;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class JSONHeatmap extends JsonStatististicsTemplate {
  String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
  String[] hours = {
    "12:00am", "01:00am", "02:00am", "03:00am", "04:00am", "05:00am", "06:00am", "07:00am",
    "08:00am", "09:00am", "10:00am", "11:00am", "12:00pm", "01:00pm", "02:00pm", "03:00pm",
    "04:00pm", "05:00pm", "06:00pm", "07:00pm", "08:00pm", "09:00pm", "10:00pm", "11:00pm"
  };

  public JSONHeatmap(List<Car> cars) {
    super(cars);
    Collections.reverse(Arrays.asList(hours));
  }

  private int countCarsForHourAndDay(String hour, String day) {
    int counterCarsForHourAndDay = 0;
    for (Car car : cars) {
      long begin = car.begin();
      Timestamp timestamp = new Timestamp(begin);
      LocalDateTime localDateTime = timestamp.toLocalDateTime();
      int indexOfHour = 23 - Arrays.asList(hours).indexOf(hour);
      int carBeginHour = localDateTime.getHour();
      int indexOfDay = Arrays.asList(days).indexOf(day);
      DayOfWeek carBeginDay = localDateTime.getDayOfWeek();
      if (indexOfHour == carBeginHour && carBeginDay.getValue() == indexOfDay + 1) {
        counterCarsForHourAndDay++;
      }
    }
    return counterCarsForHourAndDay;
  }

  private JsonArrayBuilder daysAsArray() {
    JsonArrayBuilder daysArray = Json.createArrayBuilder();
    for (String day : days) {
      daysArray.add(day);
    }
    return daysArray;
  }

  private JsonArrayBuilder hoursAsArray() {
    JsonArrayBuilder hoursArray = Json.createArrayBuilder();
    for (String hour : hours) {
      hoursArray.add(hour);
    }
    return hoursArray;
  }

  @Override
  protected JsonArrayBuilder addJson(JsonArrayBuilder b) {
    for (String hour : hours) {
      JsonArrayBuilder hourBuilder = Json.createArrayBuilder();
      for (String day : days) {
        hourBuilder.add(countCarsForHourAndDay(hour, day));
      }
      b.add(hourBuilder);
    }
    return b;
  }

  @Override
  protected JsonArrayBuilder buildJson(JsonArrayBuilder b) {
    JsonObject root =
        Json.createObjectBuilder()
            .add(
                "data",
                Json.createArrayBuilder()
                    .add(
                        Json.createObjectBuilder()
                            .add("z", b)
                            .add("x", daysAsArray())
                            .add("y", hoursAsArray())
                            .add("type", "heatmap")))
            .build();

    return Json.createArrayBuilder().add(root);
  }
}
