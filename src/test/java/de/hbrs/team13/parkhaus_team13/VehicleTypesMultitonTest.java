package de.hbrs.team13.parkhaus_team13;

import de.hbrs.team13.parkhaus_team13.singleton.VehicleTypesMultiton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTypesMultitonTest {

  @Test
  void getInstance() {
    assertSame(VehicleTypesMultiton.getInstance("PKW"), VehicleTypesMultiton.getInstance("PKW"));
    assertTrue(
        VehicleTypesMultiton.getInstance("PKW").hashCode()
            == VehicleTypesMultiton.getInstance("PKW").hashCode());

    assertSame(VehicleTypesMultiton.getInstance("SUV"), VehicleTypesMultiton.getInstance("SUV"));
    assertTrue(
        VehicleTypesMultiton.getInstance("SUV").hashCode()
            == VehicleTypesMultiton.getInstance("SUV").hashCode());

    assertSame(
        VehicleTypesMultiton.getInstance("MOTORBIKE"),
        VehicleTypesMultiton.getInstance("MOTORBIKE"));
    assertTrue(
        VehicleTypesMultiton.getInstance("MOTORBIKE").hashCode()
            == VehicleTypesMultiton.getInstance("MOTORBIKE").hashCode());

    assertSame(
        VehicleTypesMultiton.getInstance("E_VEHICLE"),
        VehicleTypesMultiton.getInstance("E_VEHICLE"));
    assertTrue(
        VehicleTypesMultiton.getInstance("E_VEHICLE").hashCode()
            == VehicleTypesMultiton.getInstance("E_VEHICLE").hashCode());

    assertNull(VehicleTypesMultiton.getInstance("A>VB"));
  }
}
