package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewWeeklyEarningsTest {
  @Test
  void test() {
    CarBuilder cb1 = new CarBuilder();
    cb1.buildTimer(System.currentTimeMillis() - 99101);

    CarBuilder cb2 = new CarBuilder();
    cb2.buildTimer(System.currentTimeMillis() - 9910000001l * 7);

    Car car1 = cb1.buildCar();
    Car car2 = cb2.buildCar();
    Statistics s = new Statistics();

    ViewWeeklyEarnings v = new ViewWeeklyEarnings(s);

    s.addCar(car1);
    s.addCar(car2);

    assertEquals(car1.getPrice(), v.get());
  }
}
