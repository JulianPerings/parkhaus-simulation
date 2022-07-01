package de.hbrs.team13.parkhaus_team13;

public interface ParkingGarageIF {
  // gives a copy of the array
  ParkingLotIF[] getGarage();
  // gives a pointer to the array
  ParkingLotIF[] getGarageUnprotected();
  // need to check out how response works for client (maybe needs to be an int to say which parking
  // spot was taken)
  int parkCar(Car c);

  int[] getFreeSpaces(String[] s);

  int[] getParkingSpaces(String[] s);

  Car removeCar(Car c);

  void resize();

  void changeMax(int m);

  boolean parkCarAt(Car c, int i);
}
