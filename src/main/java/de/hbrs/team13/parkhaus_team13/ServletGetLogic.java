package de.hbrs.team13.parkhaus_team13;

import java.util.ArrayList;

import static de.hbrs.team13.parkhaus_team13.CarParkServlet.*;

public class ServletGetLogic {
    public static String response(String event){
        switch (event) {
            case "sum":
                return "sum = € " + stats.getSum();
            case "avg":
                return "avgTime in seconds = " + stats.getAvgTime() + " and avgPrize = € " + stats.getAvg();
            case "stats":
                int[] counter = stats.countVehicleType();
                String[] vehicleType = stats.getVehicleTypes();
                StringBuilder result= new StringBuilder();
                for (int i = 0; i < counter.length; i++) {
                    result.append(vehicleType[i]).append(": ").append(counter[i]).append("; ");
                }
                return result.toString();
            case "min":
                if(stats.getMin() != null){
                    Car min=stats.getMin();
                    return "Car "+min.getLicense()+" with "+min.getDuration()+" seconds";
                }else{
                    return "No car left the carpark";
                }
            case "max":
                if(stats.getMax() != null){
                    Car max=stats.getMax();
                    return "Car "+max.getLicense()+" with "+max.getDuration()+" seconds";
                }else{
                    return "No car left the carpark";
                }
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
                result = new StringBuilder();
                for (Car car:
                        exportCars) {
                    result.append(car.export());
                    if(exportCars.indexOf(car) != exportCars.size()-1){
                        result.append(",");
                    }
                }
                return result.toString();
            case "clientCategoryChart":
                return stats.generateClientCategoryChart();
            case "vehicleTypeChart":
                return stats.generateVehicleTypeChart();
            case "durationChart":
                return stats.generateDurationChart();
            case "beginHeatmap":
                return stats.generateBeginHeatmap();
            case "getPrices":
                String prices = " price per minute day/night:€" +
                        Math.round(priceCalc.calcDayNightPrice(12 * 60 * 60 * 1000, 60 * 1000) / 100d * 100.0) / 100.0 +
                        "/€" +
                        Math.round(priceCalc.calcDayNightPrice(0, 60 * 1000) / 100d * 100.0) / 100.0 +
                        " | " +
                        " price per hour day/night:€" +
                        Math.round(priceCalc.calcDayNightPrice(12 * 60 * 60 * 1000, 60 * 60 * 1000) / 100d * 100.0) / 100.0 +
                        "/€" +
                        Math.round(priceCalc.calcDayNightPrice(0, 60 * 60 * 1000) / 100d * 100.0) / 100.0 +
                        " | " +
                        " price per 24 hours:€" +
                        Math.round(priceCalc.calcDayNightPrice(0, 24 * 60 * 60 * 1000) / 100d * 100.0) / 100.0 +
                        " | " +
                        " price per month:€" +
                        Math.round(priceCalc.calcDayNightPrice(0, (30L * 24 * 60 * 60 * 1000)) / 100d * 100.0) / 100.0 +
                        " | " +
                        " price per year:€" +
                        Math.round(priceCalc.calcDayNightPrice(0, 365L * 24 * 60 * 60 * 1000) / 100d * 100.0) / 100.0;
                return prices;
            default:
        }
        return null;
    }
}
