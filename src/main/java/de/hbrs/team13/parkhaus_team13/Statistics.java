package de.hbrs.team13.parkhaus_team13;

import java.util.*;

public class Statistics extends Observable {
  private List<Car> cars = new ArrayList<>();
  String[] vehicleTypes = {"SUV", "PKW", "MOTORBIKE", "E_VEHICLE"};
  String[] clientCategory = {"FAMILY", "WOMEN", "ANY", "HANDICAPPED"};

  void addCar(Car car) {
    cars.add(car);
    setChanged();
    notifyObservers(cars);
  }

  List<Car> getCarList() {
    return cars;
  }

  /**
   * @return The price paid overall in cents.
   */
  double getSum() {
    return cars.stream().map(Car::getPrice).reduce(0., (x, y) -> x + y);
  }

  /**
   * @return The average price in cents cut after 2 decimal places.
   */
  double getAvg() {
    return cars.size() == 0 ? 0 : Math.floor((getSum() / cars.size()) * 100) / 100;
  }

  /**
   * @return The time parked overall in seconds.
   */
  double getTime() {
    return cars.stream().map(Car::getDuration).reduce(0, (x, y) -> x + y);
  }

  /**
   * @return The average time parked in seconds.
   */
  int getAvgTime() {
    return cars.size() == 0 ? 0 : (int) (getTime() / cars.size());
  }

  /**
   * @return Array with VehicleTypes
   */
  String[] getVehicleTypes() {
    return vehicleTypes;
  }

  /**
   * @return Array with ClientCategories
   */
  String[] getClientCategory() {
    return clientCategory;
  }

  /**
   * Returns array of Integers representing the number of each VehicleType. Use getVehicleTypes to
   * see the VehicleType each Integer belongs to.
   *
   * @return Array of Integers
   */
  int[] countVehicleType() {
    int[] counterVehicleTypes = {0, 0, 0, 0};

    for (int i = 0; i < 4; i++) {
      String type = vehicleTypes[i];
      counterVehicleTypes[i] =
          (int) cars.stream().map(Car::getVehicleType).filter(e -> e.equals(type)).count();
    }

    return counterVehicleTypes;
  }

  /**
   * @return Car that has parked over the shortest period of time
   */
  Car getMin() {
    Comparator<Car> c = (s1, s2) -> s1.getDuration() - s2.getDuration();
    return cars.stream().reduce((c1, c2) -> c.compare(c1, c2) <= 0 ? c1 : c2).orElse(null);
  }

  /**
   * @return Car that has parked over the longest period of time
   */
  Car getMax() {
    Comparator<Car> c = (s1, s2) -> s1.getDuration() - s2.getDuration();
    return cars.stream().reduce((c1, c2) -> c.compare(c1, c2) >= 0 ? c1 : c2).orElse(null);
  }

  String generateClientCategoryChart() {
    ClientTypeChart chart = new ClientTypeChart(cars);
    return chart.createJSON().build().get(0).toString();
  }

  String generateVehicleTypeChart() {
    VehicleTypeChart chart = new VehicleTypeChart(cars);
    return chart.createJSON().build().get(0).toString();
  }

  public String generateDurationChart() {
    JSONDurationChart chart = new JSONDurationChart(cars);
    return chart.createJSON().build().get(0).toString();
  }

  public String generateBeginHeatmap() {
    JSONHeatmap h = new JSONHeatmap(cars);
    return h.createJSON().build().get(0).toString();
  }
}
