package de.hbrs.team13.parkhaus_team13;

import java.util.Observable;
import java.util.Observer;

public class ViewCurrentCost implements Observer {
    private String license;
    private PriceCalc pc = new PriceCalc();
    double price = 0d;
    ViewCurrentCost(ParkingGarage obs, String license){
        obs.addObserver(this);
        this.license = license;
    }
    @Override
    public void update(Observable o, Object arg) {
       ParkingGarage p = (ParkingGarage) arg;
       Car c = p.findCar(license);
       price = pc.calcDayNightPrice(c.begin() ,System.currentTimeMillis() - c.begin());
    }

    public double get(){
        return price;
    }
}
