package de.hbrs.team89.se1_starter_repo;

public class ParkingGarage implements ParkingGarageIF {
    ParkingLot[] spaces;
    public ParkingGarage(int m){
        spaces = new ParkingLot[m];
        for(int i = 0; i < spaces.length;i++){
            spaces[i] = new ParkingLot(new String[]{"ANY"});
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
    public boolean parkCar(CarIF c) {
        for(ParkingLot p : spaces){
            if(p.canPark(c)){
                p.parkVehicle(c);
                return true;
            }
        }
        return false;
    }

    @Override
    public int[] getFreeParkingSpaces(String[] s) {
        if(s != null) {
            int[] counter = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                counter[i] = 0;
                for (ParkingLot p : spaces) {
                    if (p.isAllowed(s[i])) {
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
                    if (p.isAllowed(s[i])) {
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
    public CarIF removeCar(CarIF c) {
        for(ParkingLot p : spaces){
            if(p.carEquals(c)){
                return p.removeVehicle();
            }
        }
        return null;
    }
}
