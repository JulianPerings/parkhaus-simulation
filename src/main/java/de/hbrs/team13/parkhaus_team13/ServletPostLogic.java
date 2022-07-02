package de.hbrs.team13.parkhaus_team13;

import java.util.Arrays;

import static de.hbrs.team13.parkhaus_team13.CarParkServlet.*;

/** evaluater for http post messages */
public class ServletPostLogic {

  private ServletPostLogic() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * evaluates the postmessages based on param event and param params
   *
   * @param event possible Events are enter, leave and change_max
   * @param params input values for the possible events
   * @return String with needed response text or null when wrong input
   */
  public static String response(String event, String[] params) {

    switch (event) {
      case "enter":
        Car tet = new Car(params);
        InputAdapter adapter = new InputAdapter(tet.getParams());
        if (!adapter.isCorrect()) {
          System.out.println("ENTER: Wrong input " + Arrays.toString(params));
          return null;
        }
        CarBuilder carbuilder1 = new CarBuilder(params);
        carbuilder1.buildTimer(System.currentTimeMillis());
        carbuilder1.buildDuration(0);
        carbuilder1.buildPrice(0);
        Car newCar = carbuilder1.buildCar();
        int xi = garage.parkCar(newCar);
        if (xi != 0) {
          undoList.add(uC -> uC.undoEnter(garage, newCar));
          return "" + xi;
        }
        break;
      case "leave":
        InputAdapter leaveInputAdapter = new InputAdapter(params);
        if (!leaveInputAdapter.isCorrect()) {
          System.out.println("LEAVE: Wrong input" + Arrays.toString(params));
          return null;
        }
        Car leavecar = garage.findTicket(leaveInputAdapter.getTicket());
        if (leavecar == null) {
          System.out.println(
              "\n\nLEAVE: Car not in park " + leaveInputAdapter.getTicket() + "\n\n");
          return null;
        }
        CarBuilder carbuilder2 = new CarBuilder(leavecar.getParams());
        carbuilder2.buildDuration((int) (System.currentTimeMillis() - leavecar.begin()) * 100);
        carbuilder2.buildPrice(
            (int)
                (priceCalc.calcDayNightPrice(
                        leavecar.begin(), System.currentTimeMillis() - leavecar.begin())
                    * 10000));
        System.out.print("Removing: " + garage.removeCar(leavecar));
        leavecar = carbuilder2.buildCar();
        stats.addCar(leavecar);
        undoList.add(uC -> uC.undoLeave(stats, garage));
        System.out.println("leave," + leavecar.toString() + ", price = " + leavecar.getPrice());
        return "" + (long)(leavecar.getPrice() * 100);
      case "licencePlate":
        Car foundcar;
        String licencePlate;
            if(params.length > 0) {
              licencePlate = params[0].replace("+", " ");
              System.out.println(garage.findCar(licencePlate));
              foundcar = garage.findCar(licencePlate);
            } else {
              foundcar = null;
              licencePlate = "NaN";
            }
            if(foundcar == null){
              return "No car found with "+licencePlate+"\n<html> \n" +
                      "<a href=\"costCalculator.jsp\">back to the search</a>" +
                      "</html>";
            }
            return "Cost for " + licencePlate + ": " + priceCalc.calcDayNightPrice(foundcar.begin(),System.currentTimeMillis()-foundcar.begin()) + "\n<html> \n" +
                    "<a href=\"costCalculator.jsp\">back to the carpark</a>" +
                    "</html>";
      case "invalid":
        return "";
      case "change_max":
        Integer i = Integer.valueOf(params[0]);
        System.out.println(i);
        garage.changeMax(i);
        return "";
      default:
    }
    return null;
  }
}