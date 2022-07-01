package de.hbrs.team13.parkhaus_team13;

public class ParkingLot implements ParkingLotIF {
  String[] allowed;
  Car vehicle = null;

  public ParkingLot() {
    allowed = null;
  }

  public ParkingLot(String[] s) {
    allowed = s;
  }

  @Override
  public boolean isOccupied() {
    return vehicle != null;
  }

  @Override
  public boolean canPark(Car c) {
    return !isOccupied() && isAllowed(c.getPriority());
  }

  @Override
  public Car removeVehicle() {
    Car temp = vehicle;
    vehicle = null;
    return temp;
  }

  @Override
  public void parkVehicle(Car c) {
    if (vehicle == null) {
      if (canPark(c)) {
        vehicle = c;
      }
    } else {
      System.out.println(c.nr() + " tried to Park on a occupied Spot");
    }
  }

  @Override
  public Car getVehicle() {
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
  public boolean isAllowed(String[] s) {
    for (String s1 : allowed) {
      for (String s2 : s)
        if (s1.equals(s2)) {
          return true;
        }
    }
    return false;
  }

  @Override
  public boolean carEquals(Car c) {
    if (vehicle == null) {
      return false;
    }
    return vehicle.equals(c);
  }
}
