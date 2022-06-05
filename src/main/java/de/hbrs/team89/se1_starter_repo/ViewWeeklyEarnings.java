package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ViewWeeklyEarnings implements Observer {
    double sum = 0d;
    ViewWeeklyEarnings(Statistics obs){
        obs.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Car> cars = (ArrayList<Car>) arg;
        sum = cars.stream().filter(car -> System.currentTimeMillis() - car.end() <= 86400000l*7).map(car -> car.getPrice()).reduce(0d, Double::sum);
    }

    public double get(){
        return sum;
    }
}
