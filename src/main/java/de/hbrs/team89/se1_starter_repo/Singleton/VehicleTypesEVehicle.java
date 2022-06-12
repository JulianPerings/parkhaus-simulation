package de.hbrs.team89.se1_starter_repo.Singleton;

public class VehicleTypesEVehicle extends  VehicleTypes{
    protected static VehicleTypes instance = new VehicleTypesEVehicle("MOTORBIKE",0.7,30.0);
    protected VehicleTypesEVehicle(String v, double p, double r) {
        super(v, p, r);
    }
    public static synchronized VehicleTypes getInstance(){
        return instance;
    }
}
