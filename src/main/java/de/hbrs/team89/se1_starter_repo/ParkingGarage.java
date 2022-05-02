package de.hbrs.team89.se1_starter_repo;

public class ParkingGarage implements ParkingGarageIF {
    ParkingLot[] spaces;
    int max;
    public ParkingGarage(int m){
        max = m;
        spaces = new ParkingLot[max];
        for(int i = 0; i < spaces.length;i++){
            spaces[i] = new ParkingLot(new String[]{"PKW","SUV","ANY"});
        }
    }
    @Override
    public ParkingLotIF[] getGarage() {
        return spaces.clone();
    }

    @Override
    public ParkingLotIF[] getGarageUnprotected() {
        return spaces;
    }

    @Override
    //without priority atm
    public int parkCar(Car c) {
        for(int i= 0; i < spaces.length; i++){
            if(spaces[i].canPark(c)){
                spaces[i].parkVehicle(c);
                return i+1;
            }
        }
        return 0;
    }

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

    @Override
    public Car removeCar(Car c) {
        for(ParkingLot p : spaces){
            if(p.carEquals(c)){
                return p.removeVehicle();
            }
        }
        return null;
    }

    @Override
    public void resize() {
        if(max != spaces.length){

        }
    }
    @Override
    public void changeMax(int m){
        max = m;
    }
}
