package de.hbrs.team13.parkhaus_team13;

public interface InputAdapterIF {
  int getNr();

  long getBegin();

  int getDuration();

  int getPrice();

  String getTicket();

  String getColor();

  int getSpace();

  String getClientCategory();

  String getVehicleType();

  String getLicense();

  boolean isCorrect();
}
