package de.hbrs.team89.se1_starter_repo;

public interface ParkingLotIF {
    boolean isOccupied();
    boolean canPark(Car c);
    Car removeVehicle();
    void parkVehicle(Car c);
    Car getVehicle();
    String[] getAllowed();
    void setAllowed(String[] s);
    boolean isAllowed(String[] s);
    boolean carEquals(Car c);
}
