package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Consumer;

import static de.hbrs.team13.parkhaus_team13.CarParkServlet.*;
import static org.junit.jupiter.api.Assertions.*;

class ServletPostLogicTest {
    CarParkServlet test=new BaseServlet();
    Car car1,car2,car3,car4;
    String expected;
    @BeforeEach
    void setUp() {
        stats=new Statistics();
        garage=new ParkingGarage(20);
        undoList=new ArrayList<Consumer<UndoCommand>>();
        car1=new Car();
        car2=new Car();
        car3=new Car();
        car4=new Car();
        expected="";
        garage.parkCar(car1);
        garage.parkCar(car2);
        stats.addCar(car3);
        stats.addCar(car4);
        undoList.add(uC -> uC.undoLeave(stats, garage));
    }

    @Test
    void response_caseEnter_expectsCorrectStringAndCarInGarage() {
        expected=""+garage.parkCar(car3);
        garage.removeCar(car3);
        assertEquals(2,garage.getCounter());
        assertEquals(expected,ServletPostLogic.response("enter",car3.getParams()));
        assertEquals(3,garage.getCounter());
    }
    @Test
    void response_caseLeave_expectsCorrectStringAndCarInStats(){
        assertEquals("",ServletPostLogic.response("leave", car1.getParams()));
    }
}