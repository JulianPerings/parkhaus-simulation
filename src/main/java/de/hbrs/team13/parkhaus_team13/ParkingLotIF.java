package de.hbrs.team13.parkhaus_team13;

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
