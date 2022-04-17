package de.hbrs.team89.se1_starter_repo;

import java.util.Arrays;
import java.util.Scanner;

// ToDo replace 0 by correct values read from this.params
public class Car implements CarIF {
    String[] params;
    public Car( String[] params ){
        this.params = params;
    }

    @Override
    public int nr() {
        return new Scanner( params[0] ).useDelimiter("\\D+").nextInt();
    }

    @Override
    public long begin() {
        return new Scanner( params[1] ).useDelimiter("\\D+").nextLong();
    }

    @Override
    public long end() {
        return this.begin()+this.duration();
    }

    @Override
    public int duration() {
        return new Scanner( params[2] ).useDelimiter("\\D+").nextInt();
    }

    @Override
    public int price() {
        return new Scanner( params[3] ).useDelimiter("\\D+").nextInt();
    }

    @Override
    public String toString(){
        return Arrays.toString( params );
    }
}
