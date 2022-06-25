package de.hbrs.team13.parkhaus_team13;

public interface InputAdapterIF {
    int getNr();
    long getBegin();
    int getDuration();
    int getPrice();
    String getTicket();
    String getColor();
    int getSpace();
    String getClient_category();
    String getVehicle_type();
    String getLicense();
    boolean isCorrect();
}
