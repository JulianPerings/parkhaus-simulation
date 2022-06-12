package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UndoCommand {
    void enter(ParkingGarage p, Car c){
        p.removeCar(c);
    }
    void leave(Statistics s, ParkingGarage p){
        ArrayList<Car> cars = s.getCarList();
        Car c= cars.remove(cars.size()-1);
        p.parkCar(c);
    }
    void undo(List<Consumer<UndoCommand>> undoList){
        if(undoList.size() > 0){
            undoList.remove(undoList.size()-1).accept(this);
        }
    }
}
