package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotIFTest {

    ParkingLot p,p1;
    String[] params;
    @BeforeEach
    void setup(){
        p = new ParkingLot(new String[]{""});
        p1 = new ParkingLot(new String[]{"PKW","SUV","MOTORBIKE","E_VEHICLE"});
    }
    @Test
    void isOccupied() {
        assertFalse(p.isOccupied());
        p.vehicle = new Car();
        assertTrue(p.isOccupied());
    }

    @Test
    void canPark() {
        assertTrue(p1.canPark(new Car()));
    }

    @Test
    void removeVehicle() {
        Car c= new Car();
        p1.parkVehicle(c);
        CarIF c1 = p1.removeVehicle();
        assertTrue(c.equals(c1));
        assertTrue(null == p.removeVehicle());
    }

    @Test
    void parkVehicle() {
        Car c= new Car();
        p1.parkVehicle(c);
        assertTrue(p1.vehicle.equals(c));
        c= new Car();
        p.parkVehicle(c);
        assertFalse(p.carEquals(c));
    }

    @Test
    void getVehicle() {
        Car c= new Car();
        p1.parkVehicle(c);
        assertTrue(p1.getVehicle().equals(c));
        c = null;
        p1.removeVehicle();
        p1.vehicle =c;
        assertNull(p1.getVehicle());
    }

    @Test
    void getAllowed() {
        assertTrue(p1.getAllowed()[0].equals(new String[]{"PKW"}[0]));
    }

    @Test
    void setAllowed() {
        p1.setAllowed(new String[]{"SUV"});
        assertTrue(p1.getAllowed()[0].equals(new String[]{"SUV"}[0]));
    }

    @Test
    void isAllowed() {
        assertTrue(p1.isAllowed(new String[]{"PKW"}));
        assertFalse(p.isAllowed(new String[]{"SUV"}));
        assertFalse(p1.isAllowed(new String[]{}));
    }

    @Test
    void carEquals() {
        Car c= new Car();
        p1.parkVehicle(c);
        assertTrue(p1.carEquals(c));
    }
}