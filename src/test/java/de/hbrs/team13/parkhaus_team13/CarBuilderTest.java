package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarBuilderTest {
    @Test
    void testCorrectBuild(){
        String[] params = {"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"PKW\"",
                "\"license\": \"SU-X 47\""};
        Car c = new Car(params);

        CarBuilder cb = new CarBuilder();
        cb.buildNr(11);
        cb.buildTimer(1650896019513l);
        cb.buildDuration(99100);
        cb.buildPrice(991);
        cb.buildHash("c6d68ad63d346c13bd5345ec6f40b039");
        cb.buildColor("#f15bec");
        cb.buildSpace(14);
        cb.buildClientCategory("WOMEN");
        cb.buildVehicleType("PKW");
        cb.buildLicense("SU-X 47");

        Car c2 = cb.buildCar();

        assertArrayEquals(c.getParams(), c2.getParams());

    }
}