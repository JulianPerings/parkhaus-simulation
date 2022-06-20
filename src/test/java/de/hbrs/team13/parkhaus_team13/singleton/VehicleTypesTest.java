package de.hbrs.team13.parkhaus_team13.singleton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTypesTest {
    VehicleTypes a,c;
    @BeforeEach
    void setUp() {
        a= new VehicleTypes("MOTORBIKE",0.8,0.5);
        c= new VehicleTypes("",0,0);
    }

    @Test
    void getInstance() {
        VehicleTypes b = VehicleTypes.getInstance();
        assertEquals(VehicleTypes.instance,b);
    }

    @Test
    void getVehicleType() {
        assertEquals("MOTORBIKE",a.getVehicleType());
    }

    @Test
    void getPriceFactor() {
        assertEquals(0.8,a.getPriceFactor());
    }

    @Test
    void getReqSpace() {
        assertEquals(0.5,a.getReqSpace());
    }

    @Test
    void testToString() {
        assertEquals("Type : MOTORBIKE",a.toString());
    }
}