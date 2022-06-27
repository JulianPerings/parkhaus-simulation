package de.hbrs.team13.parkhaus_team13;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UndoCommand {

  /**
   * Undo entering of a car.
   *
   * @param p ParkingGarage-Pointer
   * @param c Car-Pointer
   */
  static void undoEnter(ParkingGarage p, Car c) {
    p.removeCar(c);
  }

  /**
   * Undo leaving of a car.
   *
   * @param s Statistics-Pointer
   * @param p ParkingGarage-Pointer
   */
  static void undoLeave(Statistics s, ParkingGarage p) {
    ArrayList<Car> cars = s.getCarList();
    Car c = cars.remove(cars.size() - 1);
    p.parkCar(c);
  }

  /**
   * Executes last added UndoCommand Method on List.
   *
   * @param undoList List-Pointer
   */
  void undo(List<Consumer<UndoCommand>> undoList) {
    if (!undoList.isEmpty()) {
      undoList.remove(undoList.size() - 1).accept(this);
    }
  }
}
