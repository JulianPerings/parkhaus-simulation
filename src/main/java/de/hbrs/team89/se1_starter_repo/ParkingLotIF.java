package de.hbrs.team89.se1_starter_repo;

public interface ParkingLotIF {
    boolean isOccupied();
    boolean canPark(CarIF c);
    CarIF removeVehicle();
    void parkVehicle(CarIF c);
    CarIF getVehicle();
    String[] getAllowed();
    void setAllowed(String[] s);
    boolean isAllowed(String s);
    boolean carEquals(CarIF c);
}
