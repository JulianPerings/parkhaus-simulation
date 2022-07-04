package de.hbrs.team13.parkhaus_team13;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ViewWeeklyEarnings implements Observer {
  double sum = 0d;

  ViewWeeklyEarnings(Statistics obs) {
    obs.addObserver(this);
  }

  @Override
  public void update(Observable o, Object arg) {
    List<Car> cars = (ArrayList<Car>) arg;
    sum =
        cars.stream()
            .filter(car -> System.currentTimeMillis() - car.end() <= 86400000l * 7)
            .map(car -> car.getPrice())
            .reduce(0d, Double::sum);
  }

  public double get() {
    return sum;
  }
}
