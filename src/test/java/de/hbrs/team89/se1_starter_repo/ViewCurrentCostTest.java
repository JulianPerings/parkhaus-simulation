package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewCurrentCostTest {

    @Test
    void get() {
        String[] params = new String[]{"\"nr\": 11",
                "\"timer\": "+System.currentTimeMillis(),
                "\"duration\": 0",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"E_VEHICLE\"",
                "\"license\": \"SU-X 47\""};

        Car car = new Car(params);
        ParkingGarage p = new ParkingGarage();

        ViewCurrentCost v = new ViewCurrentCost(p, "SU-X 47");
        PriceCalc price = new PriceCalc();

        double tmp = price.calcDayNightPrice(car.begin(), System.currentTimeMillis() - car.begin());

        p.parkCar(car);

        assertEquals(tmp, v.get());
     }
}