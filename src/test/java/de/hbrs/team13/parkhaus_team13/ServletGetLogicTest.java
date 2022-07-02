package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Consumer;

import static de.hbrs.team13.parkhaus_team13.CarParkServlet.*;
import static org.junit.jupiter.api.Assertions.*;

class ServletGetLogicTest {
  CarParkServlet test = new BaseServlet();
  Car car1, car2, car3, car4;
  String expected;

  @BeforeEach
  void setUp() {
    stats = new Statistics();
    garage = new ParkingGarage(20);
    undoList = new ArrayList<Consumer<UndoCommand>>();
    car1 = new Car();
    car2 = new Car();
    car3 = new Car();
    car4 = new Car();
    expected = "";
    garage.parkCar(car1);
    garage.parkCar(car2);
    stats.addCar(car3);
    stats.addCar(car4);
    undoList.add(uC -> uC.undoLeave(stats, garage));
    dailyEarnings = new ViewDailyEarnings(stats);
    weeklyEarnings = new ViewWeeklyEarnings(stats);
  }

  @Test
  void response_caseSum_expectsCorrectString() {
    expected = "sum = € " + stats.getSum();
    assertEquals(expected, ServletGetLogic.response("sum"));
  }

  @Test
  void response_caseAvg_expectsCorrectString() {
    expected = "avgTime in seconds = " + stats.getAvgTime() + " and avgPrize = € " + stats.getAvg();
    assertEquals(expected, ServletGetLogic.response("avg"));
  }

  @Test
  void response_caseStats_expectsCorrectString() {
    int[] counter = stats.countVehicleType();
    String[] vehicleType = stats.getVehicleTypes();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < counter.length; i++) {
      result.append(vehicleType[i]).append(": ").append(counter[i]).append("; ");
    }
    expected = result.toString();
    assertEquals(expected, ServletGetLogic.response("stats"));
  }

  @Test
  void response_caseMinWithCar_expectsCorrectString() {
    expected =
        "Car " + stats.getMin().getLicense() + " with " + stats.getMin().getDuration() + " seconds";
    assertEquals(expected, ServletGetLogic.response("min"));
  }

  @Test
  void response_caseMinWithoutCar_expectsCorrectString() {
    stats = new Statistics();
    expected = "No car left the carpark";
    assertEquals(expected, ServletGetLogic.response("min"));
  }

  @Test
  void response_caseMaxWithCar_expectsCorrectSting() {
    expected =
        "Car " + stats.getMax().getLicense() + " with " + stats.getMax().getDuration() + " seconds";
    assertEquals(expected, ServletGetLogic.response("max"));
  }

  @Test
  void response_caseMaxWithoutCar_expectsCorrectString() {
    stats = new Statistics();
    expected = "No car left the carpark";
    assertEquals(expected, ServletGetLogic.response("max"));
  }

  @Test
  void response_caseUndo_expectsNullAndUndolistEmptyAndStatsSize1() {
    assertNull(ServletGetLogic.response("undo"));
    assertEquals(0, undoList.size());
    assertEquals(1, stats.getCarList().size());
  }

  @Test
  void response_caseCars_expectsCorrectString() {
    for (Car car : stats.getCarList()) expected += car.export() + ",";
    expected = expected.substring(0, expected.length() - 1);
    assertEquals(expected, ServletGetLogic.response("cars"));
  }

  @Test
  void response_caseClientCategoryChart_expectsCorrectString() {
    expected = stats.generateClientCategoryChart();
    assertEquals(expected, ServletGetLogic.response("clientCategoryChart"));
  }

  @Test
  void response_caseVehicleTypeChart_expectsCorrectString() {
    expected = stats.generateVehicleTypeChart();
    assertEquals(expected, ServletGetLogic.response("vehicleTypeChart"));
  }

  @Test
  void response_caseClientDurationChart_expectsCorrectString() {
    expected = stats.generateDurationChart();
    assertEquals(expected, ServletGetLogic.response("durationChart"));
  }

  @Test
  void response_caseBeginHeatmap_expectsCorrectString() {
    expected = stats.generateBeginHeatmap();
    assertEquals(expected, ServletGetLogic.response("beginHeatmap"));
  }

  @Test
  void response_caseGetPrices_expectsCorrectString() {
    expected =
        " price per minute day/night:€"
            + Math.round(priceCalc.calcDayNightPrice(12 * 60 * 60 * 1000, 60 * 1000) / 100d * 100.0)
                / 100.0
            + "/€"
            + Math.round(priceCalc.calcDayNightPrice(0, 60 * 1000) / 100d * 100.0) / 100.0
            + " | "
            + " price per hour day/night:€"
            + Math.round(
                    priceCalc.calcDayNightPrice(12 * 60 * 60 * 1000, 60 * 60 * 1000) / 100d * 100.0)
                / 100.0
            + "/€"
            + Math.round(priceCalc.calcDayNightPrice(0, 60 * 60 * 1000) / 100d * 100.0) / 100.0
            + " | "
            + " price per 24 hours:€"
            + Math.round(priceCalc.calcDayNightPrice(0, 24 * 60 * 60 * 1000) / 100d * 100.0) / 100.0
            + " | "
            + " price per month:€"
            + Math.round(priceCalc.calcDayNightPrice(0, (30L * 24 * 60 * 60 * 1000)) / 100d * 100.0)
                / 100.0
            + " | "
            + " price per year:€"
            + Math.round(priceCalc.calcDayNightPrice(0, 365L * 24 * 60 * 60 * 1000) / 100d * 100.0)
                / 100.0;
    assertEquals(expected, ServletGetLogic.response("getPrices"));
  }

  @Test
  void response_wrongInput_expectsNull() {
    assertNull(ServletGetLogic.response(""));
    assertNull(ServletGetLogic.response("test"));
    assertNull(ServletGetLogic.response("<script>Alert(1)</script>"));
  }

  @Test
  void response_caseDailyEarnings_expectsCorrectString() {
    stats = new Statistics();
    dailyEarnings = new ViewDailyEarnings(stats);
    assertEquals("0.0 €", ServletGetLogic.response("dailyEarnings"));
    car1 = new Car(new String[] {
            "\"nr\": 11",
            "\"timer\": "+ (System.currentTimeMillis() - 99101),
            "\"duration\": 99100",
            "\"price\": 1000",
            "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
            "\"color\": \"#f15bec\"",
            "\"space\": 14",
            "\"client_category\": \"HANDICAPPED\"",
            "\"vehicle_type\": \"MOTORBIKE\"",
            "\"license\": \"SU-X 47\""
    });

    car2 = new Car(new String[] {
            "\"nr\": 11",
            "\"timer\": "+ (System.currentTimeMillis() - 99101),
            "\"duration\": 99100",
            "\"price\": 25000",
            "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
            "\"color\": \"#f15bec\"",
            "\"space\": 14",
            "\"client_category\": \"HANDICAPPED\"",
            "\"vehicle_type\": \"MOTORBIKE\"",
            "\"license\": \"SU-X 47\""
    });
    stats.addCar(car1);
    assertEquals("0.1 €", ServletGetLogic.response("dailyEarnings"));
    stats.addCar(car2);
    assertEquals("2.6 €", ServletGetLogic.response("dailyEarnings"));
  }

  @Test
  void response_caseWeeklyEarnings_expectsCorrectString() {
    stats = new Statistics();
    weeklyEarnings = new ViewWeeklyEarnings(stats);
    assertEquals("0.0 €", ServletGetLogic.response("weeklyEarnings"));
    car1 = new Car(new String[] {
            "\"nr\": 11",
            "\"timer\": "+ (System.currentTimeMillis() - 99101),
            "\"duration\": 99100",
            "\"price\": 1000",
            "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
            "\"color\": \"#f15bec\"",
            "\"space\": 14",
            "\"client_category\": \"HANDICAPPED\"",
            "\"vehicle_type\": \"MOTORBIKE\"",
            "\"license\": \"SU-X 47\""
    });

    car2 = new Car(new String[] {
            "\"nr\": 11",
            "\"timer\": "+ (System.currentTimeMillis() - 99101),
            "\"duration\": 99100",
            "\"price\": 25000",
            "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
            "\"color\": \"#f15bec\"",
            "\"space\": 14",
            "\"client_category\": \"HANDICAPPED\"",
            "\"vehicle_type\": \"MOTORBIKE\"",
            "\"license\": \"SU-X 47\""
    });
    stats.addCar(car1);
    assertEquals("0.1 €", ServletGetLogic.response("weeklyEarnings"));
    stats.addCar(car2);
    assertEquals("2.6 €", ServletGetLogic.response("weeklyEarnings"));
  }
}
