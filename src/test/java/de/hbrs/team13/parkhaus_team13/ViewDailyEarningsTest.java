package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewDailyEarningsTest {
  @Test
  void test() {
    CarBuilder cb1 = new CarBuilder();
    cb1.buildTimer(System.currentTimeMillis() - 99101);

    CarBuilder cb2 = new CarBuilder();
    cb2.buildTimer(System.currentTimeMillis() - 9910000001l);

    Car car1 = cb1.buildCar();
    Car car2 = cb2.buildCar();
    Statistics s = new Statistics();

    ViewDailyEarnings v = new ViewDailyEarnings(s);

    s.addCar(car1);
    s.addCar(car2);

    assertEquals(car1.getPrice(), v.get());
  }
}
