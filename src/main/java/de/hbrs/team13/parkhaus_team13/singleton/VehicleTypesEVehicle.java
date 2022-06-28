package de.hbrs.team13.parkhaus_team13.singleton;

public class VehicleTypesEVehicle extends VehicleTypes {
  protected static VehicleTypes instance = new VehicleTypesEVehicle("MOTORBIKE", 0.7, 30.0);

  protected VehicleTypesEVehicle(String v, double p, double r) {
    super(v, p, r);
  }

  public static synchronized VehicleTypes getInstance() {
    return instance;
  }
}
