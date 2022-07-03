package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Consumer;

import static de.hbrs.team13.parkhaus_team13.CarParkServlet.*;
import static org.junit.jupiter.api.Assertions.*;

class ServletPostLogicTest {
  CarParkServlet test = new BaseServlet();
  Car car1, car2, car3, car4;
  String expected;

  @BeforeEach
  void setUp() {
    stats = new Statistics();
    garage = new ParkingGarage(20);
    undoList = new ArrayList<Consumer<UndoCommand>>();
    car1 = new Car();
    car2 = new Car();
    car3 = new Car();
    car4 = new Car();
    expected = "";
    garage.parkCar(car1);
    garage.parkCar(car2);
    stats.addCar(car3);
    stats.addCar(car4);
    undoList.add(uC -> uC.undoLeave(stats, garage));
  }
  @Test
  void response_caseEnterWorngInput_expectsNull(){
    CarBuilder builder=new CarBuilder();
    builder.buildHash("");
    String[] params=builder.buildCar().getParams();
    assertNull(ServletPostLogic.response("enter",params));
  }
  @Test
  void response_caseEnter_expectsCorrectStringAndCarInGarage() {
    expected = "" + garage.parkCar(car3);
    garage.removeCar(car3);
    assertEquals(2, garage.getCounter());
    assertEquals(expected, ServletPostLogic.response("enter", car3.getParams()));
    assertEquals(3, garage.getCounter());
  }
  @Test
  void response_caseLeaveWrongInput_expectsNull(){
    CarBuilder builder=new CarBuilder();
    builder.buildHash("");
    String[] params=builder.buildCar().getParams();
    assertNull(ServletPostLogic.response("leave",params));
  }
  @Test
  void response_caseLeaveWrongCar_expectsNull(){
    CarBuilder builder=new CarBuilder();
    String[] params=builder.buildCar().getParams();
    assertNull(ServletPostLogic.response("leave",params));
  }
  @Test
  void response_caseLeave_expectsCorrectStringAndCarInStats() {
    expected =
        ""
            + (int) (priceCalc.calcDayNightPrice(car1.begin(), System.currentTimeMillis() - car1.begin()))
                * 100;
    System.out.println(car1.begin() + " " + car1.getDuration());
    assertEquals(Long.parseLong(expected), Long.valueOf(ServletPostLogic.response("leave", car1.getParams())),100);
  }
  @Test
  void response_caseInvalid(){
    assertNull(ServletPostLogic.response("invalide",new String[]{}));
    assertEquals("",ServletPostLogic.response("invalid",new String[]{}));
  }
  @Test
  void response_caseChangeMax_expectsCorrectStringandCarparkSize999(){
    assertEquals("",ServletPostLogic.response("change_max",new String[]{"999"}));
    assertEquals(999,garage.getGarage().length);
  }
  @Test
  void response_caseLicencePlateWrongCar_expectsCorrectString(){
    String licencePlate=new Car().getLicense();
    expected="No car found with "+ licencePlate +"\n<html> \n" +
            "<a href=\"costCalculator.jsp\">back to the search</a>" +
            "</html>";
    assertEquals(expected,ServletPostLogic.response("licencePlate",new String[]{licencePlate}));
  }
  @Test
  void response_caseLicencePlateButNoInput_expectsCorrectString(){
    expected="No car found with "+ "NaN" +"\n<html> \n" +
            "<a href=\"costCalculator.jsp\">back to the search</a>" +
            "</html>";
    assertEquals(expected,ServletPostLogic.response("licencePlate",new String[]{}));
  }
  @Test
  void response_caseLicencePlate_expectsCorrectString(){
    String licencePlate= car1.getLicense();
    expected="No car found with "+ licencePlate +"\n<html> \n" +
            "<a href=\"costCalculator.jsp\">back to the search</a>" +
            "</html>";
    assertNotEquals(expected,ServletPostLogic.response("licencePlate",new String[]{car1.getLicense()}));
  }
}
