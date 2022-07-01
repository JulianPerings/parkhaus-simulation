package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewWeeklyEarningsTest {
  @Test
  void test() {
    String[] params =
        new String[] {
          "\"nr\": 11",
          "\"timer\": " + (System.currentTimeMillis() - 99101),
          "\"duration\": 99100",
          "\"price\": 991",
          "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
          "\"color\": \"#f15bec\"",
          "\"space\": 14",
          "\"client_category\": \"WOMEN\"",
          "\"vehicle_type\": \"E_VEHICLE\"",
          "\"license\": \"SU-X 47\""
        };
    String[] params2 =
        new String[] {
          "\"nr\": 11",
          "\"timer\": " + (System.currentTimeMillis() - 9910000001l * 7),
          "\"duration\": 9910000",
          "\"price\": 991",
          "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
          "\"color\": \"#f15bec\"",
          "\"space\": 14",
          "\"client_category\": \"WOMEN\"",
          "\"vehicle_type\": \"E_VEHICLE\"",
          "\"license\": \"SU-X 47\""
        };
    Car car1 = new Car(params);
    Car car2 = new Car(params2);
    Statistics s = new Statistics();

    ViewWeeklyEarnings v = new ViewWeeklyEarnings(s);

    s.addCar(car1);
    s.addCar(car2);

    assertEquals(car1.getPrice(), v.get());
  }
}
