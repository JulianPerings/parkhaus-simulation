package de.hbrs.team89.se1_starter_repo;

import java.util.Arrays;

// ToDo replace 0 by correct values read from this.params
public class Car implements CarIF {
    String[] params;
    public Car( String[] params ){
        this.params = params;
    }

    @Override
    public int nr() {
        return Integer.parseInt(params[1]);
    }

    @Override
    public long begin() {
        return Long.parseLong(params[2]);
    }

    @Override
    public long end() {
        return 0;
    }

    @Override
    public int duration() {
        return Integer.parseInt(params[3]);
    }

    @Override
    public double price() {
        return Double.parseDouble(params[3]);
    }

    @Override
    public String toString(){
        return Arrays.toString( params );
    }
}
