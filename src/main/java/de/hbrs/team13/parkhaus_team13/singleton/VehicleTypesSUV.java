package de.hbrs.team13.parkhaus_team13.singleton;

public class VehicleTypesSUV extends VehicleTypes {

  protected static VehicleTypes instance = new VehicleTypesSUV("SUV", 1.2, 30.0);

  protected VehicleTypesSUV(String v, double p, double r) {
    super(v, p, r);
  }

  public static synchronized VehicleTypes getInstance() {
    return instance;
  }
}
