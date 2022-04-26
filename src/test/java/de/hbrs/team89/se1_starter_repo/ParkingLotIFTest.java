package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotIFTest {

    ParkingLot p,p1;
    @BeforeEach
    void setup(){
        p = new ParkingLot();
        p1 = new ParkingLot(new String[]{"ANY"});
    }
    @Test
    void isOccupied() {
        assertFalse(p.isOccupied());
        p.vehicle = new Car(new String[]{""});
        assertTrue(p.isOccupied());
    }

    @Test
    void canPark() {

    }

    @Test
    void removeVehicle() {
    }

    @Test
    void parkVehicle() {
    }

    @Test
    void getVehicle() {
    }

    @Test
    void getAllowed() {
    }

    @Test
    void setAllowed() {
    }

    @Test
    void isAllowed() {
    }

    @Test
    void carEquals() {
    }
}