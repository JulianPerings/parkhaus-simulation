package de.hbrs.team89.se1_starter_repo.Singleton;

public class VehicleTypesPkw  extends VehicleTypes{

    protected static VehicleTypes instance = new VehicleTypesPkw("PKW",1.0,25.0);
    protected VehicleTypesPkw(String v, double p, double r) {
        super(v, p, r);
    }
    public static synchronized VehicleTypes getInstance(){
        return instance;
    }
}
