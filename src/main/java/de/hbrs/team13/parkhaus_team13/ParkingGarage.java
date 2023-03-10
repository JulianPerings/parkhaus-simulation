package de.hbrs.team13.parkhaus_team13;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;

public class ParkingGarage extends Observable implements ParkingGarageIF, Iterable<Car> {
  ParkingLot[] spaces;
  private int counter;
  int max;
  /** Initialize ParkingGarage with m parking lots which only allow PKW's. */
  public ParkingGarage(int m) {
    max = m;
    counter = 0;
    spaces = new ParkingLot[max];
    for (int i = 0; i < spaces.length; i++) {
      spaces[i] = new ParkingLot(new String[] {"PKW", "SUV", "ANY"});
    }
  }
  /** Returns clone of garage. */
  @Override
  public ParkingLotIF[] getGarage() {
    ParkingLotIF[] p = new ParkingLotIF[spaces.length];
    for (int i = 0; i < spaces.length; i++) {
      ParkingLot p2 = new ParkingLot();
      p2.allowed = spaces[i].allowed;
      if (spaces[i].getVehicle() != null) {
        p2.parkVehicle(new Car(spaces[i].getVehicle().getParams()));
      }
      p[i] = p2;
    }
    return p;
  }

  /** Returns reference to original garage so that garage can be changed. */
  @Override
  public ParkingLotIF[] getGarageUnprotected() {
    return spaces;
  }

  /**
   * Parks car in garage, returns true if parked successful and false if there was no fitting
   * parking spot.
   */
  @Override
  // without priority atm
  public int parkCar(Car c) {
    if(c != null) {
      for (int i = 0; i < spaces.length; i++) {
        if (spaces[i].canPark(c)) {
          spaces[i].parkVehicle(c);
          System.out.println("Parked at spot " + (i + 1));
          counter++;
          setChanged();
          notifyObservers(this);
          return i + 1;
        }
      }
    }
    return 0;
  }

  /**
   * Takes string array of vehicle types and returns int array of free parking lots for given types.
   * Example: getFreeParkingSpaces({"PKW", "E_VEHICLE"}) returns {10, 7} would mean that there are
   * 10 lots where a PKW can park and 7 lots suitable for E_VEHICLEs.
   */
  @Override
  public int[] getFreeSpaces(String[] s) {
    if (s != null && s.length != 0) {
      int[] counter2 = new int[s.length];
      for (int i = 0; i < s.length; i++) {
        counter2[i] = 0;
        for (ParkingLot p : spaces) {
          if (p.isAllowed(new String[] {s[i]}) && !p.isOccupied()) {
            counter2[i]++;
          }
        }
      }
      return counter2;
    } else {
      return new int[] {0};
    }
  }

  /**
   * Takes string array of vehicle types and returns int array of total parking lots for given
   * types. Example: getFreeParkingSpaces({"PKW", "E_VEHICLE"}) returns {10, 7} would mean that
   * there is a total of 10 PKS lots and 7 E_VEHICLE lots.
   */
  @Override
  public int[] getParkingSpaces(String[] s) {
    if (s != null && s.length != 0) {
      int[] counter2 = new int[s.length];
      for (int i = 0; i < s.length; i++) {
        counter2[i] = 0;
        for (ParkingLot p : spaces) {
          if (p.isAllowed(new String[] {s[i]})) {
            counter2[i]++;
          }
        }
      }
      return counter2;
    } else {
      return new int[] {0};
    }
  }

  /** Removes and returns given car from the garage. */
  @Override
  public Car removeCar(Car c) {
    for (ParkingLot p : spaces) {
      if (p.carEquals(c)) {
        counter--;
        setChanged();
        notifyObservers();
        return p.removeVehicle();
      }
    }
    return null;
  }

  public Car findCar(String license) {
    for (ParkingLot p : spaces) {
      if(p.isOccupied()){
        license=license.replace("\"","");
        String carlicense=p.getVehicle().getLicense().replace("\"","");
        carlicense=carlicense.replace("\n","");
        if (license.equals(carlicense)) {
          return p.getVehicle();
        }
      }
    }
    return null;
  }

  public int findCar(Car c) {
    for (int i = 0; i < spaces.length; i++) {

      if (spaces[i].isOccupied() && spaces[i].getVehicle().equals(c)) {
        return i + 1;
      }
    }
    return -1;
  }

  public Car findTicket(String ticket) {
    for (ParkingLot p : spaces) {
      if (p.isOccupied() && p.getVehicle().getTicket().equals(ticket)) {
        return p.getVehicle();
      }
    }
    return null;
  }

  @Override
  public void resize() {
    if (max != spaces.length && max >= 0) {
      ParkingLot[] temp = new ParkingLot[max];
      for (int i = 0; i < temp.length; i++) {
        if (spaces.length > i) {
          temp[i] = spaces[i];
        } else {
          temp[i] = new ParkingLot(new String[] {"PKW", "SUV", "ANY"});
        }
      }
      spaces = temp;
    }
  }

  @Override
  public void changeMax(int m) {
    max = m;
    resize();
  }

  /**
   * gives < Car > Iterator over Parking garge slots <big> Notvication: only for direct use
   *
   * @return Iterator< Car >
   */
  @Override
  public Iterator<Car> iterator() {
    return new Iterator<>() {
      private int cursor = 0;
      private int position = 0;

      @Override
      public boolean hasNext() {
        return cursor < counter;
      }

      @Override
      public Car next() {
        if (hasNext()) {
          for (; position < spaces.length; position++) {
            if (spaces[position].isOccupied()) {
              cursor++;
              return spaces[position++].getVehicle();
            }
          }
        }
        throw new NoSuchElementException();
      }
    };
  }

  public boolean parkCarAt(Car c, int i) {
    if (i >= spaces.length || i < 0) {
      return false;
    }
    if (spaces[i].canPark(c)) {
      spaces[i].parkVehicle(c);
      System.out.println("Parked at spot" + (i + 1));
      counter++;
      setChanged();
      notifyObservers(this);
      return true;
    }
    return false;
  }

  public int getCounter() {
    return counter;
  }
}
