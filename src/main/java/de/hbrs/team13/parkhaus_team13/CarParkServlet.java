package de.hbrs.team13.parkhaus_team13;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * common superclass for all servlets
 * groups all auxiliary common methods used in all servlets
 */
public abstract class CarParkServlet extends HttpServlet {

    /* abstract methods, to be defined in subclasses */
    abstract String NAME(); // each ParkhausServlet should have a name, e.g. "Level1"

    abstract int MAX(); // maximum number of parking slots of a single parking level

    abstract String config(); // configuration of a single parking level

    Statistics stats = new Statistics();    //Handles all our statistics
    PriceCalc priceCalc= new PriceCalc();   //Handles price calculations
    ParkingGarage garage = new ParkingGarage(this.MAX());   //Stores vehicles currently parking in our garage
    List<Consumer<UndoCommand>> undoList = new ArrayList<>();  //Undo-list to undo actions regarding entering and leaving
    UndoCommand uComm = new UndoCommand();  //Handles undo-requests

    /**
     * HTTP GET
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        System.out.println(cmd + " requested: " + request.getQueryString());
        switch (cmd) {
            case "config":
                // Overwrite CarPark config parameters
                // Max, open_from, open_to, delay, simulation_speed
                out.println(config());
                break;
            case "sum":
                out.println("sum = € " + stats.getSum());
                break;
            case "avg":
                out.println("avgTime in seconds = " + stats.getAvgTime() + " and avgPrize = € " + stats.getAvg());
                break;
            case "stats":
                int[] counter = stats.countVehicleType();
                String[] vehicleType = stats.getVehicleTypes();
                for (int i = 0; i < counter.length; i++) {
                    out.print(vehicleType[i] + ": " + counter[i] + "; ");
                }
                break;
            case "min":
                if(stats.getMin() != null){
                    Car min=stats.getMin();
                    out.println("Car "+min.getLicense()+" with "+min.getDuration()+" seconds");
                }else{
                    out.println("No car left the carpark");
                }
                break;
            case "max":
                if(stats.getMax() != null){
                    Car max=stats.getMax();
                    out.println("Car "+max.getLicense()+" with "+max.getDuration()+" seconds");
                }else{
                    out.println("No car left the carpark");
                }
                break;
            case "undo":
                uComm.undo(undoList);
                break;
            case "cars":
                // Cars are separated by comma.
                // Values of a single car are separated by slash.
                // Format: Nr, timer begin, duration, price, Ticket, color, space, client category, vehicle type, license (PKW Kennzeichen)
                // For example:
                // out.println("1/1648465400000/_/_/Ticket1/#0d1e0a/2/any/PKW/1,2/1648465499999/_/_/Ticket2/#dd10aa/3/any/PKW/2");
                ArrayList<Car> exportCars = stats.getCarList();
                StringBuilder result = new StringBuilder();
                for (Car car:
                     exportCars) {
                    result.append(car.export());
                    if(exportCars.indexOf(car) != exportCars.size()-1){
                        result.append(",");
                    }
                }
                out.println(result);
                break;
            case "clientCategoryChart":
                out.println(stats.generateClientCategoryChart());
                break;
            case "vehicleTypeChart":
                out.println(stats.generateVehicleTypeChart());
                break;
            case "durationChart":
                out.println(stats.generateDurationChart());
                break;
            case "beginHeatmap":
                out.println(stats.generateBeginHeatmap());
                break;
            case "getPrices":
                out.printf(" price per minute day/night:€%.2f/€%.2f"+
                        " | "+
                        " price per hour day/night:€%.2f/€%.2f"+
                        " | "+
                        " price per 24 hours:€%.2f"+
                        " | "+
                        " price per month:€%.2f"+
                        " | "+
                        " price per year:€%.2f",
                        priceCalc.calcDayNightPrice(12*60*60*1000,60*1000)/100d,priceCalc.calcDayNightPrice(0,60*1000)/100d,
                        priceCalc.calcDayNightPrice(12*60*60*1000,60*60*1000)/100d,priceCalc.calcDayNightPrice(0,60*60*1000)/100d,
                        priceCalc.calcDayNightPrice(0,24*60*60*1000)/100d,priceCalc.calcDayNightPrice(0,(30L*24*60*60*1000))/100d,
                        priceCalc.calcDayNightPrice(0, 365L*24*60*60*1000)/100d);
                break;
            default:
                System.out.println("Invalid Command: " + request.getQueryString());
        }

    }

    /**
     * HTTP POST
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String body = getBody(request);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println(body);
        String[] params = body.split(",");
        String event = params[0];
        String[] restParams = Arrays.copyOfRange(params, 1, params.length);

        switch (event) {
            case "enter":
                Car newCar = new Car(restParams);
                // System.out.println( "enter," + newCar );

                // re-direct car to another parking lot
                int xi = locator(newCar);
                if(xi != 0)  {
                    undoList.add(uC -> uC.undoEnter(garage,newCar));
                    out.println(xi);
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
                            System.out.println("can't scan time values from leaving car "+Arrays.toString(params));
                        }finally{
                            beginscan.close();
                            durationscan.close();
                        }
                        price=priceCalc.calcDayNightPrice(begin,duration);
                        restParams[3]="  \"price\": "+((int)(price*100.0d));   //adjusting the price in restParams after calculation
                        Car xc = new Car(restParams);
                        System.out.print("Removing: " + garage.removeCar(new Car(restParams)));
                        stats.addCar(xc);
                        undoList.add(uC -> uC.undoLeave(stats,garage));
                        // ToDo getContext().setAttribute("sum"+NAME(), stats.getSum() + price );
                    }
                }
                out.println(price);  // server calculated price
                System.out.println("leave," + Arrays.toString(restParams) + ", price = " + price);
                break;
            case "invalid":
                break;
            case "occupied":
                System.out.println(body);
                break;
            case "tomcat":
                out.println(getServletConfig().getServletContext().getServerInfo()
                        + getServletConfig().getServletContext().getMajorVersion()
                        + getServletConfig().getServletContext().getMinorVersion());
                break;
            case "change_max":
                Integer i = Integer.valueOf(restParams[0]);
                System.out.println(i);
                garage.changeMax(i);
                break;
            default:
                System.out.println(body);
                // System.out.println( "Invalid Command: " + body );
        }

    }

    // auxiliary methods used in HTTP request processing
    /**
     * @return the servlet context
     */
    ServletContext getContext() {
        return getServletConfig().getServletContext();
    }

    /**
     * TODO: replace this by your own function
     *
     * @return the number of the free parking lot to which the next incoming car will be directed
     */
    int locator(Car car) {
        // numbers of parking lots start at 1, not zero
        // return 1;  // always use the first space
        return cars().parkCar(car);
    }

    /**
     * @return the list of all cars stored in the servlet context so far
     */

    ParkingGarage cars() {
        if (getContext().getAttribute("cars" + NAME()) == null) {
            getContext().setAttribute("cars" + NAME(), garage);
        }
        return (ParkingGarage) getContext().getAttribute("cars" + NAME());
    }

    /**
     * @param request the HTTP POST request
     * @return the body of the request
     */
    String getBody(HttpServletRequest request) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void destroy() {
        System.out.println("Servlet destroyed.");
    }
}