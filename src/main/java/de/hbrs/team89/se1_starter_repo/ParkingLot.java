package de.hbrs.team89.se1_starter_repo;

public class ParkingLot implements ParkingLotIF {
    String[] allowed;
    CarIF vehicle = null;
    public ParkingLot(){
        allowed = null;
    }
    public ParkingLot(String[] s){
        allowed = s;
    }
    @Override
    public boolean isOccupied() {
        return vehicle != null;
    }

    @Override
    public boolean canPark(CarIF c) {
        return !isOccupied() && isAllowed(c.getVehicleType());
    }

    @Override
    public CarIF removeVehicle() {
        CarIF temp = vehicle;
        vehicle = null;
        return temp;
    }

    @Override
    public void parkVehicle(CarIF c) {
        if(vehicle != null){
            System.out.println(c.nr() + " tried to Park on a occupied Spot");
        } else {
            vehicle = c;
        }
    }

    @Override
    public CarIF getVehicle() {
        return vehicle;
    }

    @Override
    public String[] getAllowed() {
        return allowed.clone();
    }

    @Override
    public void setAllowed(String[] s) {
        allowed = s;
    }

    @Override
    public boolean isAllowed(String s) {
        for (String s1 : allowed){
            if(s1.equals(s)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean carEquals(CarIF c) {
        return vehicle.equals(c);
    }
}
