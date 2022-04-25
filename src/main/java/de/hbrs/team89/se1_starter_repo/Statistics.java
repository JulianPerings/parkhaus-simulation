package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;

public class Statistics {
    ArrayList<Car> cars = new ArrayList<Car>();

    void addCar(Car car){
        cars.add(car);
    }
    CarIF[] getCarList(){
        return cars.toArray(new CarIF[cars.size()]);
    }

    double getSum(){
        double sum = 0;
        for(int i = 0; i < cars.size(); i++){
            sum += cars.get(i).price()/10000.;
        }
        return Math.floor(sum*100)/100;
    }
    double getAvg(){
        return cars.size() == 0 ? 0 : Math.floor((getSum()/cars.size())*100)/100;
    }

    double getTime(){
        double time = 0;
        for(int i = 0; i < cars.size(); i++){
            time += cars.get(i).duration()/1000.;
        }
        return time;
    }

    int getAvgTime(){
        return cars.size() == 0 ? 0 : (int) (getTime()/ cars.size());
    }
}
