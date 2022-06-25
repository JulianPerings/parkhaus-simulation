package de.hbrs.team13.parkhaus_team13;

import java.util.Arrays;
import java.util.Scanner;

import static de.hbrs.team13.parkhaus_team13.CarParkServlet.*;

/**
 * evaluater for http post messages
 */
public class ServletPostLogic {
    /**
     * evaluates the postmessages based on param event and param params
     * @param event possible Events are enter, leave and change_max
     * @param params input values for the possible events
     * @return String with needed response text or null when wrong input
     */
    public static String response(String event,String[] params){

        switch (event) {
            case "enter":
                InputAdapter adapter=new InputAdapter(params);
                if(!adapter.isCorrect()){
                    return null;
                }
                CarBuilder carbuilder=new CarBuilder();
                carbuilder.buildNr(adapter.getNr());
                carbuilder.buildTimer(System.currentTimeMillis());
                carbuilder.buildDuration(0);
                carbuilder.buildPrice(0);
                carbuilder.buildHash(adapter.getTicket());
                carbuilder.buildColor(adapter.getColor());
                carbuilder.buildSpace(adapter.getSpace());
                carbuilder.buildClient_Category(adapter.getClient_category());
                carbuilder.buildVehicle_type(adapter.getVehicle_type());
                carbuilder.buildLicense(adapter.getLicense());
                Car newCar= carbuilder.buildCar();
                // System.out.println( "enter," + newCar );

                // re-direct car to another parking lot
                int xi = garage.parkCar(newCar);
                if(xi != 0)  {
                    undoList.add(uC -> uC.undoEnter(garage,newCar));
                    return ""+xi;
                }

                break;
            case "leave":
                //ToDo needs to be own method so i can call it for resizing
                if (params.length > 3) {
                    long begin = System.currentTimeMillis();
                    long duration = 0;
                    Scanner beginscan = new Scanner(params[1]);
                    Scanner durationscan = new Scanner(params[2]);
                    try {
                        begin = beginscan.useDelimiter("\\D+").nextLong();
                        duration = durationscan.useDelimiter("\\D+").nextLong();
                    } catch (Exception e) {
                        System.out.println("can't scan time values from leaving car " + Arrays.toString(params));
                    } finally {
                        beginscan.close();
                        durationscan.close();
                    }
                    double price = priceCalc.calcDayNightPrice(begin, duration);
                    params[3] = "  \"price\": " + ((int) (price * 100.0d));   //adjusting the price in restParams after calculation
                    Car xc = new Car(params);
                    System.out.print("Removing: " + garage.removeCar(new Car(params)));
                    stats.addCar(xc);
                    undoList.add(uC -> uC.undoLeave(stats, garage));
                    // ToDo getContext().setAttribute("sum"+NAME(), stats.getSum() + price );
                    System.out.println("leave," + Arrays.toString(params) + ", price = " + price);
                    return "" + price;
                }
                break;
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