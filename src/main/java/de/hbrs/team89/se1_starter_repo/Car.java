package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//ToDO Protect against NULL PARAMS
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
    // "client_categories":["FAMILY","WOMEN","ANY","HANDICAPPED"]
    @Override
    public void sortOutPriority(){
        if(params.length >= 8) {    //ToDO Protect against NULL PARAMS
            ArrayList<String> sortOrder = new ArrayList<String>();
            String[] s = new String[]{"HANDICAPPED", "MOTORBIKE", "E_VEHICLE", "WOMEN", "FAMILY", "SUV", "PKW", "ANY"};
            for (String s1 : s) {
                sortOrder.add(s1);
            }

            ArrayList<String> vSa = new ArrayList<String>();
            String vHS = getVehicleType();
            addMissing(vSa, vHS, new String[]{"SUV", "PKW"});
            ArrayList<String> cSa = new ArrayList<String>();
            String cHS = getClientCategory();
            addMissing(cSa, cHS, new String[]{"ANY"});
            priority = new String[vSa.size() + cSa.size()];
            int counterV = 0;
            int counterC = 0;
            for (int i = 0; i < priority.length; i++) {
                if (counterV == vSa.size()) {
                    addRest(i, counterC, cSa);
                    break;
                }
                if (counterC == cSa.size()) {
                    addRest(i, counterV, vSa);
                    break;
                }
                if (sortOrder.indexOf(vSa.get(counterV)) < sortOrder.indexOf(cSa.get(counterC))) {
                    priority[i] = vSa.get(counterV++);
                } else {
                    priority[i] = cSa.get(counterC++);
                }
            }
        }
    }

    @Override
    public String[] getPriority() {
        return priority;
    }

    private void addMissing(ArrayList<String> a, String type, String[] missing){
        a.add(type);
        for(String s : missing) {
            if (!type.equals(s)) {
                a.add(s);
            }
        }
    }
    private void addRest(int i,int counter, ArrayList<String> missing){
        for(; i < priority.length ; i++){
            priority[i] = missing.get(counter++);
        }
    }
}
