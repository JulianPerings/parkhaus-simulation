package de.hbrs.team13.parkhaus_team13.singleton;

public class VehicleTypes {
  protected String vehicleType;
  protected double priceFactor;
  protected double reqSpace;
  protected static VehicleTypes instance = new VehicleTypes("", 0, 0);

  protected VehicleTypes(String v, double p, double r) {
    this.vehicleType = v;
    this.priceFactor = p;
    this.reqSpace = r;
  }

  public static synchronized VehicleTypes getInstance() {
    return instance;
  }

  public String getVehicleType() {
    return vehicleType;
  }

  public double getPriceFactor() {
    return priceFactor;
  }

  public double getReqSpace() {
    return reqSpace;
  }

  @Override
  public String toString() {
    return "Type : " + vehicleType;
  }
}
