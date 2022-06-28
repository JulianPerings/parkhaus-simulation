package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class UndoCommandTest {
  Car car1;
  ParkingGarage pg;
  Statistics s;
  List<Consumer<UndoCommand>> undoList = new ArrayList<Consumer<UndoCommand>>();
  UndoCommand uComm = new UndoCommand();

  @BeforeEach
  void init() {
    pg = new ParkingGarage(10);
    s = new Statistics();
    String[] params1 =
        new String[] {
          "\"nr\": 11",
          "\"timer\": 1650896019513",
          "\"duration\": 99100",
          "\"price\": 991",
          "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
          "\"color\": \"#f15bec\"",
          "\"space\": 14",
          "\"client_category\": \"HANDICAPPED\"",
          "\"vehicle_type\": \"MOTORBIKE\"",
          "\"license\": \"SU-X 47\""
        };
    car1 = new Car(params1);
  }

  @Test
  void enter() {
    ParkingLotIF[] p2 = pg.getGarage();
    pg.parkCar(car1);
    undoList.add(uC -> uC.undoEnter(pg, car1));
    assertFalse(equals(p2, pg.getGarage()));
    undo();
    assertTrue(equals(p2, pg.getGarage()));
  }

  @Test
  void leave() {
    pg.parkCar(car1);
    ParkingLotIF[] p2 = pg.getGarage();
    s.addCar(pg.removeCar(car1));
    undoList.add(uC -> uC.undoLeave(s, pg));
    assertFalse(equals(p2, pg.getGarage()));
    assertTrue(s.getCarList().get(0).equals(car1));
    undo();
    assertTrue(s.getCarList().size() == 0);
    assertTrue(equals(p2, pg.getGarage()));
  }

  void undo() {
    uComm.undo(undoList);
  }

  boolean equals(ParkingLotIF[] p, ParkingLotIF[] p2) {
    for (int i = 0; i < p.length; i++) {
      Car c1 = p[i].getVehicle();
      Car c2 = p2[i].getVehicle();
      if (c1 != null && c2 != null) {
        if (!c1.equals(c2)) {
          return false;
        }
      } else {
        if (c1 == null && c2 == null) {
          continue;
        }
        return false;
      }
    }
    return true;
  }
}
