package de.hbrs.team89.se1_starter_repo;

public class ParkingGarage implements ParkingGarageIF {
    ParkingLot[] spaces;
    /**Initialize ParkingGarage with m parking lots which only allow PKW's.*/
    public ParkingGarage(int m){
        spaces = new ParkingLot[m];
        for(int i = 0; i < spaces.length;i++){
            spaces[i] = new ParkingLot(new String[]{"PKW"});
        }
    }
    /**Returns clone of garage.*/
    @Override
    public ParkingLotIF[] getGarage() {
        return spaces.clone();
    }

    /**Returns reference to original garage so that garage can be changed.*/
    @Override
    public ParkingLotIF[] getGarageUnprotected() {
        return spaces;
    }

    /**Parks car in garage, returns true if parked successful and false if there was no fitting parking spot.  */
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

    /**Takes string array of vehicle types and returns int array of total parking lots for given types.
     * Example: getFreeParkingSpaces({"PKW", "E_VEHICLE"}) returns {10, 7} would mean that there is a total of 10 PKS lots and 7 E_VEHICLE lots.*/
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

    /**Removes and returns given car from the garage.*/
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
