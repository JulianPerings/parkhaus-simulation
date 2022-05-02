package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// ToDo replace 0 by correct values read from this.params

// ToDo implement priority Array for each car that parses the params and sorts the priority
public class Car implements CarIF {
    String[] params;
    String[] priority;
    public Car( String[] params ){
        this.params = params;
        sortOutPriority();
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
        return this.begin()+this.getDuration();
    }

    @Override
    public int getDuration() {
        return (int) ((new Scanner( params[2] ).useDelimiter("\\D+").nextInt()) / 1000.);
    }

    @Override
    public double getPrice() {
        return (new Scanner( params[3] ).useDelimiter("\\D+").nextInt())/10000.;
    }
    public String getVehicleType(){
        String s =params[8].split(":")[1];
        return s.substring(2,s.length()-1);
    }
    public String getClientCategory(){
        String s = params[7].split(":")[1];
        return s.substring(2,s.length()-1);
    }
    @Override
    public String toString(){
        return Arrays.toString( params );
    }
    @Override
    public boolean equals(CarIF c){
        return Arrays.toString(params).equals(c.toString());
    }
    //"vehicle_types":["PKW","SUV","MOTORBIKE","E_VEHICLE"],
      //      "client_categories":["FAMILY","WOMEN","ANY","HANDICAPPED"]
    @Override
    public void sortOutPriority(){
        ArrayList<String> sortOrder = new ArrayList<String>();
        String[] s = new String[]{"HANDICAPPED","MOTORBIKE","E_VEHICLE","WOMEN","FAMILY","SUV","PKW","ANY"};
        for(String s1 : s){
            sortOrder.add(s1);
        }
    }
}
