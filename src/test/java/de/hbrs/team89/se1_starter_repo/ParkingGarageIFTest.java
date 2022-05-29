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
        assertTrue(1==p.parkCar(c));
        Car c2 = new Car(params2);
        assertFalse(0==p.parkCar(c2));

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
        assertTrue(1==p.parkCar(c));
        CarIF d;
        d = p.removeCar(c);
        assertTrue(d.equals(c));
    }


    @Test
    void resize() {
    }

    @Test
    void changeMax() {
        p.spaces[9].parkVehicle(new Car(params));
        p.changeMax(9);
        for(ParkingLot pl : p.spaces){
            assertNotNull(pl);
            assertNull(pl.vehicle);
        }
        p.changeMax(11);
        for(ParkingLot pl : p.spaces){
            assertNotNull(pl);
        }
        p.changeMax(111);
        for(ParkingLot pl : p.spaces){
            assertNotNull(pl);
        }
        p.changeMax(0);
        for(ParkingLot pl : p.spaces){
            assertNotNull(pl);
        }

        p.changeMax(-1);
        assertEquals(0,p.spaces.length);
    }

    @Test
    void hasNext() {
        assertFalse(p.hasNext());
        Car c = new Car(params);
        p.parkCar(c);
        assertTrue(p.hasNext());
        p.removeCar(c);
        assertFalse(p.hasNext());
    }

    @Test
    void next() {
        Car car1 = new Car(params);
        Car car2 = new Car(params2);
        p.parkCar(car1);
        p.parkCar(car2);
        Car[] cArray = new Car[]{car1,car2};
        int i = 0;
        for( Car c : p){
            assertEquals(c,cArray[i++]);
        }
        p.removeCar(car1);
        p.removeCar(car2);
        assertNull(p.next());
    }
}