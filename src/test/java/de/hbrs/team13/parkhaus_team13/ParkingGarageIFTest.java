package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ParkingGarageIFTest {
  ParkingGarage p;
  String[] params, params2;

  @BeforeEach
  void setup() {
    p = new ParkingGarage(10);
    params =
        new String[] {
          "\"nr\": 11",
          "\"timer\": 1650896019513",
          "\"duration\": 99100",
          "\"price\": 991",
          "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
          "\"color\": \"#f15bec\"",
          "\"space\": 14",
          "\"client_category\": \"WOMEN\"",
          "\"vehicle_type\": \"PKW\"",
          "\"license\": \"SU-X 47\""
        };
    params2 =
        new String[] {
          "\"nr\": 11",
          "\"timer\": 1650896019513",
          "\"duration\": 99100",
          "\"price\": 991",
          "\"hash\": \"c6d68ad63d346c13bd521316f40b039\"",
          "\"color\": \"#f15bec\"",
          "\"space\": 14",
          "\"client_category\": \"WOMEN\"",
          "\"vehicle_type\": \"SUV\"",
          "\"license\": \"SU-X 48\""
        };
  }

  @Test
  void getGarage() {
    assertNotEquals(p.spaces, p.getGarage());
  }

  @Test
  void getGarageUnprotected() {
    assertEquals(p.spaces, p.getGarageUnprotected());
  }

  @Test
  void parkCar() {
    Car c = new Car();
    assertEquals(1, p.parkCar(c));
    Car c2 = new Car();
    assertEquals(2, p.parkCar(c2));
    p.changeMax(2);
    assertEquals(0, p.parkCar(c2));
    p.changeMax(0);
    assertEquals(0, p.parkCar(c2));
  }

  @Test
  void getFreeParkingSpaces() {
    assertEquals(10, p.getFreeSpaces(new String[] {"PKW"})[0]);
    p.parkCar(new Car());
    assertEquals(9, p.getFreeSpaces(new String[] {"PKW"})[0]);
    assertEquals(0, p.getFreeSpaces(new String[] {"TEST"})[0]);
    assertEquals(0, p.getFreeSpaces(new String[] {})[0]);
    assertEquals(0, p.getFreeSpaces(null)[0]);
  }

  @Test
  void getParkingSpaces() {
    assertEquals(10, p.getParkingSpaces(new String[] {"PKW"})[0]);
    p.parkCar(new Car());
    assertEquals(10, p.getParkingSpaces(new String[] {"PKW"})[0]);
    assertEquals(0, p.getParkingSpaces(new String[] {"TEST"})[0]);
    assertEquals(0, p.getParkingSpaces(new String[] {})[0]);
    assertEquals(0, p.getParkingSpaces(null)[0]);
  }

  @Test
  void findCar() {
    Car c = new Car(params);
    Car c2 = new Car(params2);
    int i = p.parkCar(c);
    int x = p.findCar(c);
    assertEquals(i, x);
    assertEquals(-1, p.findCar(c2));
    assertNull(p.findCar("KW"));
    p.changeMax(0);
    assertNull(p.findCar("KW"));
    assertEquals(-1, p.findCar(c));
  }

  @Test
  void removeCar() {
    Car c = new Car();
    assertEquals(1, p.parkCar(c));
    CarIF d;
    d = p.removeCar(c);
    assertTrue(d.equals(c));
    d = p.removeCar(c);
    assertNull(d);
  }

  @Test
  void resize() {}

  @Test
  void changeMax() {
    p.spaces[9].parkVehicle(new Car(params));
    p.changeMax(9);
    for (ParkingLot pl : p.spaces) {
      assertNotNull(pl);
      assertNull(pl.vehicle);
    }
    p.changeMax(11);
    for (ParkingLot pl : p.spaces) {
      assertNotNull(pl);
    }
    p.changeMax(111);
    for (ParkingLot pl : p.spaces) {
      assertNotNull(pl);
    }
    p.changeMax(0);
    for (ParkingLot pl : p.spaces) {
      assertNotNull(pl);
    }

    p.changeMax(-1);
    assertEquals(0, p.spaces.length);

    p.changeMax(9);
    p.changeMax(9);
    for (ParkingLot pl : p.spaces) {
      assertNotNull(pl);
    }
  }

  @Test
  void iterator_expectsFunctionalIterator() {
    Car car1 = new Car();
    Car car2 = new Car();
    p.parkCar(car1);
    p.parkCar(car2);
    Iterator<Car> test = p.iterator();
    assertEquals(car1, test.next());
    assertEquals(car2, test.next());
  }

  @Test
  void iterator_ForEmptyGarage_ShouldBeNoSuchElementException() {
    Iterator<Car> test = p.iterator();
    assertThrows(NoSuchElementException.class, test::next);
    Car car1 = new Car();
    Car car2 = new Car();
    p.parkCar(car1);
    p.parkCar(car2);
    p.removeCar(car1);
    p.removeCar(car2);
    test = p.iterator();
    assertThrows(NoSuchElementException.class, test::next);
  }

  @Test
  void parkCarAt() {
    assertFalse(p.parkCarAt(new Car(params), 11));
    assertFalse(p.parkCarAt(new Car(params), -1));
    assertTrue(p.parkCarAt(new Car(params), 1));
    assertFalse(p.parkCarAt(new Car(params), 1));
  }

  @Test
  void getCounter_2Cars_expects2() {
    p.parkCar(new Car(params));
    p.parkCar(new Car(params2));
    assertEquals(2, p.getCounter());
  }
}
