package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingGarageIFTest {
    ParkingGarage p;
    String[] params,params2;
    @BeforeEach
    void setup(){
        p = new ParkingGarage(10);
        params = new String[]{"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"PKW\"",
                "\"license\": \"SU-X 47\""};
        params2 = new String[]{"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"SUV\"",
                "\"license\": \"SU-X 47\""};
    }

    @Test
    void getGarage() {
        assertNotEquals(p.spaces,p.getGarage());
    }

    @Test
    void getGarageUnprotected() {
        assertEquals(p.spaces,p.getGarageUnprotected());
    }

    @Test
    void parkCar() {
        Car c = new Car(params);
        assertTrue(p.parkCar(c));
        Car c2 = new Car(params2);
        assertFalse(p.parkCar(c2));

    }

    @Test
    void getFreeParkingSpaces() {
        assertEquals(10,p.getFreeParkingSpaces(new String[]{"PKW"})[0]);
        p.parkCar(new Car(params));
        assertEquals(9,p.getFreeParkingSpaces(new String[]{"PKW"})[0]);

    }
    @Test
    void getParkingSpaces() {
        assertEquals(10,p.getParkingSpaces(new String[]{"PKW"})[0]);
        p.parkCar(new Car(params));
        assertEquals(10,p.getParkingSpaces(new String[]{"PKW"})[0]);
    }
    @Test
    void removeCar() {
        Car c = new Car(params);
        assertTrue(p.parkCar(c));
        CarIF d;
        d = p.removeCar(c);
        assertTrue(d.equals(c));
    }


}