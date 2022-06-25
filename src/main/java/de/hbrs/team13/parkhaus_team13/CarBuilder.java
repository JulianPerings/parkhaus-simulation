package de.hbrs.team13.parkhaus_team13;

public class CarBuilder implements CarBuilderIF{
    String[] params;

    /**
     * Builds a car. Single Parameters can be set using the Methods, otherwise they will be random.
     */
    public CarBuilder(){
        params = new Car().getParams();
    }
    public CarBuilder(String[] params){ this.params=params;  }

    @Override
    public void buildNr(int nr) {
        params[0] = "\"nr\": " + nr;
    }

    @Override
    public void buildTimer(long timer) {
        params[1] = "\"timer\": " + timer;
    }

    @Override
    public void buildDuration(int duration) {
        params[2] = "\"duration\": " + duration;
    }

    @Override
    public void buildPrice(int price) {
        params[3] = "\"price\": " + price;
    }

    @Override
    public void buildHash(String hash) {
        params[4] = "\"hash\": \"" + hash + "\"";
    }

    @Override
    public void buildColor(String color) {
        params[5] = "\"color\": \"" +color + "\"";
    }

    @Override
    public void buildSpace(int space) {
        params[6] = "\"space\": " + space;
    }

    @Override
    public void buildClient_Category(String clientCat) {
        params[7] = "\"client_category\": \"" + clientCat + "\"";
    }

    @Override
    public void buildVehicle_type(String VehicleType) {
        params[8] = "\"vehicle_type\": \"" + VehicleType + "\"";
    }

    @Override
    public void buildLicense(String license) {
        params[9] = "\"license\": \"" + license + "\"";
    }

    @Override
    public Car buildCar() {
        return new Car(params);
    }
}
