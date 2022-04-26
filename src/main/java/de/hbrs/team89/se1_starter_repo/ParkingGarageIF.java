package de.hbrs.team89.se1_starter_repo;

public interface ParkingGarageIF {
    //gives a copy of the array
    ParkingLotIF[] getGarage();
    //gives a pointer to the array
    ParkingLotIF[] getGarageUnprotected();
    //need to check out how response works for client (maybe needs to be an int to say which parking spot was taken)
    boolean parkCar(CarIF c);
    int[] getFreeParkingSpaces(String[] s);
    int[] getParkingSpaces(String[] s);
    CarIF removeCar(CarIF c);

}
