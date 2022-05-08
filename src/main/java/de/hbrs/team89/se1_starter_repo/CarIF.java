package de.hbrs.team89.se1_starter_repo;

 interface CarIF {
    int nr();
    long begin();
    long end();
    /**Return duration in seconds*/
    int getDuration();

    /**Returns price in euros*/
    double getPrice();
    String getVehicleType();
    boolean equals(CarIF c);
    String getTicket();
    String getColor();
    int getSpace();
    String getClientCategory();
    String getLicense();
    String export();
    void sortOutPriority();
    String[] getPriority();
}
