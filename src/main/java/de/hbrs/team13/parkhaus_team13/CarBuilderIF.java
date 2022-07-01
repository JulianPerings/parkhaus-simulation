package de.hbrs.team13.parkhaus_team13;

public interface CarBuilderIF {
  public void buildNr(int nr);

  public void buildTimer(long timer);

  public void buildDuration(int duration);

  public void buildPrice(int price);

  public void buildHash(String hash);

  public void buildColor(String color);

  public void buildSpace(int space);

  public void buildClientCategory(String clientCat);

  public void buildVehicleType(String vehicleType);

  public void buildLicense(String license);

  public Car buildCar();
}
