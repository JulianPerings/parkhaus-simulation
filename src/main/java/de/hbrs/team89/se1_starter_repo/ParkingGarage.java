package de.hbrs.team89.se1_starter_repo;

import java.util.Iterator;
import java.util.Observable;

public class ParkingGarage extends Observable implements ParkingGarageIF,Iterator ,Iterable<Car> {
    ParkingLot[] spaces;
    private int cursor = 0;
    private int counter;
    int max;
    /**Initialize ParkingGarage with m parking lots which only allow PKW's.*/
    public ParkingGarage(int m){
        max = m;
        spaces = new ParkingLot[max];
        for(int i = 0; i < spaces.length;i++){
            spaces[i] = new ParkingLot(new String[]{"PKW","SUV","ANY"});
        }
    }
    /**Returns clone of garage.*/
    @Override
    public ParkingLotIF[] getGarage() {
        ParkingLotIF[] p = new ParkingLotIF[spaces.length];
        for( int i = 0; i < spaces.length; i++){
            ParkingLot p2 = new ParkingLot();
            p2.allowed = spaces[i].allowed;
            if(spaces[i].getVehicle() != null) {
                p2.vehicle = new Car(spaces[i].getVehicle().params);
            }
            p[i] = p2;
        }
        return p;
    }

    /**Returns reference to original garage so that garage can be changed.*/
    @Override
    public ParkingLotIF[] getGarageUnprotected() {
        return spaces;
    }

    /**Parks car in garage, returns true if parked successful and false if there was no fitting parking spot.  */
    @Override
    //without priority atm
    public int parkCar(Car c) {
        for(int i= 0; i < spaces.length; i++){
            if(spaces[i].canPark(c)){
                spaces[i].parkVehicle(c);
                System.out.println("Parked at spot" + (i+1));
                counter++;
                setChanged();
                notifyObservers(this);
                return i+1;
            }
        }
        return 0;
    }

    /**Takes string array of vehicle types and returns int array of free parking lots for given types.
     * Example: getFreeParkingSpaces({"PKW", "E_VEHICLE"}) returns {10, 7} would mean that there are 10 lots where a PKW can park
     * and 7 lots suitable for E_VEHICLEs.*/
    @Override
    public int[] getFreeParkingSpaces(String[] s) {
        if(s != null) {
            int[] counter = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                counter[i] = 0;
                for (ParkingLot p : spaces) {
                    if (p.isAllowed(new String[]{s[i]})) {
                        if (!p.isOccupied()) {
                            counter[i]++;
                        }
                    }
                }
            }
            return counter;
        } else {
            return new int[]{0};
        }
    }

    /**Takes string array of vehicle types and returns int array of total parking lots for given types.
     * Example: getFreeParkingSpaces({"PKW", "E_VEHICLE"}) returns {10, 7} would mean that there is a total of 10 PKS lots and 7 E_VEHICLE lots.*/
    @Override
    public int[] getParkingSpaces(String[] s) {
        if(s != null) {
            int[] counter = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                counter[i] = 0;
                for (ParkingLot p : spaces) {
                    if (p.isAllowed(new String[]{s[i]})) {
                        counter[i]++;
                    }
                }
            }
            return counter;
        } else {
            return new int[]{0};
        }
    }

    /**Removes and returns given car from the garage.*/
    @Override
    public Car removeCar(Car c) {
        for(ParkingLot p : spaces){
            if(p.carEquals(c)){
                counter--;
                setChanged();
                notifyObservers();
                return p.removeVehicle();
            }
        }
        return null;
    }

    public Car findCar(String license){
        for(ParkingLot p : spaces){
            if(p.getVehicle().getLicense().equals(license)){
                return p.getVehicle();
            }
        }
        return null;
    }
    public int findCar(Car c){
        for(int i = 0; i < spaces.length; i++){
            if(spaces[i].getVehicle().equals(c)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void resize() {
        if(max != spaces.length && max >= 0){
                ParkingLot[] temp = new ParkingLot[max];
                for(int i = 0 ; i < temp.length; i++){
                    if(spaces.length > i){
                        temp[i] = spaces[i];
                    } else {
                        temp[i] = new ParkingLot(new String[]{"PKW","SUV","ANY"});
                    }
                }
                spaces = temp;
        }
    }
    @Override
    public void changeMax(int m){
        max = m;
        resize();
    }
    @Override
    public boolean hasNext() {
        return cursor < counter;
    }

    @Override
    public Car next() {
        if(hasNext()) {
            for (int i = cursor; i < spaces.length; i++) {
                if(spaces[i].isOccupied()){
                    cursor++;
                    return spaces[i].getVehicle();
                }
            }
        }
        return null;
    }

    @Override
    public Iterator<Car> iterator() {
        return this;
    }
    public boolean parkCarAt(Car c, int i){
        if(i >= spaces.length || i < 0){
            return false;
        }
        if(spaces[i].canPark(c)){
            spaces[i].parkVehicle(c);
            System.out.println("Parked at spot" + (i+1));
            counter++;
            setChanged();
            notifyObservers(this);
            return true;
        }
        return false;
    }
}
