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
    String getClientCategory();
    boolean equals(CarIF c);
    void sortOutPriority();
    String[] getPriority();
}
