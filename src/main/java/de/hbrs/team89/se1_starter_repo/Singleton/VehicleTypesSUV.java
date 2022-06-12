package de.hbrs.team89.se1_starter_repo.Singleton;
public class VehicleTypesSUV  extends VehicleTypes{

    protected static VehicleTypes instance = new VehicleTypesSUV("SUV",1.2,30.0);
    protected VehicleTypesSUV(String v, double p, double r) {
        super(v, p, r);
    }
    public static synchronized VehicleTypes getInstance(){
        return instance;
    }
}
