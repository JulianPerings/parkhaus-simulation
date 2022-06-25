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
                Car tet=new Car(params);
                InputAdapter adapter=new InputAdapter(tet.getParams());
                if(!adapter.isCorrect()){
                    System.out.println(adapter.getNr());
                    System.out.println(adapter.getBegin());
                    System.out.println(adapter.getDuration());
                    System.out.println(adapter.getPrice());
                    System.out.println(adapter.getTicket());
                    System.out.println(adapter.getColor());
                    System.out.println(adapter.getSpace());
                    System.out.println(adapter.getClient_category());
                    System.out.println(adapter.getVehicle_type());
                    System.out.println(adapter.getLicense());
                    System.out.println("ENTER: Wrong input"+params.length +Arrays.toString(params));
                    return null;
                }
                CarBuilder carbuilder1=new CarBuilder(params);
                carbuilder1.buildTimer(System.currentTimeMillis());
                carbuilder1.buildDuration(0);
                carbuilder1.buildPrice(0);
                Car newCar= carbuilder1.buildCar();
                int xi = garage.parkCar(newCar);
                if(xi != 0)  {
                    undoList.add(uC -> uC.undoEnter(garage,newCar));
                    return ""+xi;
                }

                break;
            case "leave":
                InputAdapter leaveInputAdapter=new InputAdapter(params);
                if(!leaveInputAdapter.isCorrect()){
                    System.out.println("LEAVE: Wrong input"+ Arrays.toString(params));
                    return null;
                }
                Car leavecar=garage.findCar(leaveInputAdapter.getTicket());
                CarBuilder carbuilder2=new CarBuilder(leavecar.getParams());
                carbuilder2.buildDuration((int)(System.currentTimeMillis()-leavecar.begin()));
                carbuilder2.buildPrice((int)(priceCalc.calcDayNightPrice(leavecar.begin(),System.currentTimeMillis()-leavecar.begin())*10000));
                System.out.print("Removing: " + garage.removeCar(leavecar));
                leavecar=carbuilder2.buildCar();
                stats.addCar(leavecar);
                undoList.add(uC -> uC.undoLeave(stats, garage));
                System.out.println("leave," + leavecar.toString() + ", price = " + leavecar.getPrice());
                return "" + leavecar.getPrice();
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