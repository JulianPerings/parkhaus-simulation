package de.hbrs.team13.parkhaus_team13;

public interface ParkingGarageIF {
  // gives a copy of the array
  ParkingLotIF[] getGarage();
  // gives a pointer to the array
  ParkingLotIF[] getGarageUnprotected();
  int parkCar(Car c);
  int[] getFreeSpaces(String[] s);

  int[] getParkingSpaces(String[] s);

  Car removeCar(Car c);
  int findCar(Car c);
  Car findCar(String licence);
  Car findTicket(String ticket);
  void resize();

  void changeMax(int m);

  boolean parkCarAt(Car c, int i);
}
