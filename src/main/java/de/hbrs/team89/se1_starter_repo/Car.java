package de.hbrs.team89.se1_starter_repo;

import java.util.Arrays;
import java.util.Scanner;

// ToDo replace 0 by correct values read from this.params

// ToDo implement priority Array for each car that parses the params and sorts the priority
public class Car implements CarIF {
    String[] params;

    public Car(String[] params) {
        this.params = params;
    }

    @Override
    public int nr() {
        return new Scanner(params[0]).useDelimiter("\\D+").nextInt();
    }

    @Override
    public long begin() {
        return new Scanner(params[1]).useDelimiter("\\D+").nextLong();
    }

    @Override
    public long end() {
        return this.begin() + this.getDuration();
    }

    @Override
    public int getDuration() {
        return (int) ((new Scanner(params[2]).useDelimiter("\\D+").nextInt()) / 1000.);
    }

    @Override
    public double getPrice() {
        return (new Scanner(params[3]).useDelimiter("\\D+").nextInt()) / 10000.;
    }

    @Override
    public String getTicket() {
        String s = params[4].split(":")[1];
        return s.substring(2, s.length() - 1);
    }

    @Override
    public String getColor() {
        String s = params[5].split(":")[1];
        return s.substring(2, s.length() - 1);
    }

    @Override
    public int getSpace() {
        return new Scanner(params[6]).useDelimiter("\\D+").nextInt();
    }

    @Override
    public String getClientCategory() {
        String s = params[7].split(":")[1];
        return s.substring(2, s.length() - 1);
    }

    @Override
    public String getVehicleType() {
        String s = params[8].split(":")[1];
        return s.substring(2, s.length() - 1);
    }

    @Override
    public String getLicense() {
        String s = params[9].split(":")[1];
        return s.substring(2, s.length() - 1);
    }

    @Override
    public String export() {
        //Format: Nr, timer begin, duration, price, Ticket, color, space, client category, vehicle type, license
        return "" + nr() + "/" + begin() + "/" + getDuration() + "/"
                + getPrice() + "/" + getTicket() + "/" + getColor() + "/"
                + getClientCategory() + "/" + getVehicleType() + "/" + getLicense();
    }

    @Override
    public String toString() {
        return Arrays.toString(params);
    }

    @Override
    public boolean equals(CarIF c) {
        return Arrays.toString(params).equals(c.toString());
    }
}
