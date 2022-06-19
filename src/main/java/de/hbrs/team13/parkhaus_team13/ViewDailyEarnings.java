package de.hbrs.team13.parkhaus_team13;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ViewDailyEarnings implements Observer {
    double sum = 0d;
    ViewDailyEarnings(Statistics obs){
        obs.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Car> cars = (ArrayList<Car>) arg;
        sum = cars.stream().filter(car -> System.currentTimeMillis() - car.end() <= 86400000l).map(car -> car.getPrice()).reduce(0d, Double::sum);
    }

    public double get(){
        return sum;
    }
}
