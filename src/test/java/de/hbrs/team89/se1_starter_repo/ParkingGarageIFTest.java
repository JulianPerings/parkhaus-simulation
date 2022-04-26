package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingGarageIFTest {
    ParkingGarage p;
    @BeforeEach
    void setup(){
        p = new ParkingGarage(10);
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
    }

    @Test
    void getFreeParkingSpaces() {
    }

    @Test
    void removeCar() {
    }
}