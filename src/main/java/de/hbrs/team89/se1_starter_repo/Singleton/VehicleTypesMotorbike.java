package de.hbrs.team89.se1_starter_repo.Singleton;

public class VehicleTypesMotorbike extends  VehicleTypes{

    protected static VehicleTypes instance = new VehicleTypesMotorbike("MOTORBIKE",0.7,5.0);
    protected VehicleTypesMotorbike(String v, double p, double r) {
        super(v, p, r);
    }
    public static synchronized VehicleTypes getInstance(){
        return instance;
    }
}
