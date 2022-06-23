package de.hbrs.team13.parkhaus_team13;

import java.util.Arrays;
import java.util.Scanner;

import static de.hbrs.team13.parkhaus_team13.CarParkServlet.*;

public class ServletPostLogic {
    public static String response(String event,String[] params){
        switch (event) {
            case "enter":
                Car newCar = new Car(params);
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
                double price = 0.0d;
                if (params.length > 4) {
                    String priceString = params[4];
                    if (!"_".equals(priceString)) {
                        long begin=System.currentTimeMillis();
                        long duration=0;
                        Scanner beginscan= new Scanner(params[2]);
                        Scanner durationscan= new Scanner(params[3]);
                        try{
                            begin=beginscan.useDelimiter("\\D+").nextLong();
                            duration=durationscan.useDelimiter("\\D+").nextLong();
                        }catch (Exception e){
                            System.out.println("can't scan time values from leaving car "+ Arrays.toString(params));
                        }finally{
                            beginscan.close();
                            durationscan.close();
                        }
                        price=priceCalc.calcDayNightPrice(begin,duration)*100.0;
                        params[3]="  \"price\": "+((int)(price*100.0d));   //adjusting the price in restParams after calculation
                        Car xc = new Car(params);
                        System.out.print("Removing: " + garage.removeCar(new Car(params)));
                        stats.addCar(xc);
                        undoList.add(uC -> uC.undoLeave(stats,garage));
                        // ToDo getContext().setAttribute("sum"+NAME(), stats.getSum() + price );
                    }
                }
                System.out.println("leave," + Arrays.toString(params) + ", price = " + price);
                return ""+price;
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