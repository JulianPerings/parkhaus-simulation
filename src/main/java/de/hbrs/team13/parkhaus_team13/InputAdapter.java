package de.hbrs.team13.parkhaus_team13;

import java.util.Arrays;
import java.util.Scanner;

public class InputAdapter implements InputAdapterIF{
    String[] params;
    public InputAdapter(String[] params){
        this.params=params;
    }
    @Override
    public int getNr() {
        Scanner scan = new Scanner(params[0]);
        int nr=-1;
        try{
            nr=scan.useDelimiter("\\D+").nextInt();
        }catch (Exception e){
            System.out.println("scanner can't scan nr from "+ Arrays.toString(this.params));
        }finally{
            scan.close();
        }
        return nr;
    }

    @Override
    public long getBegin() {
        Scanner scan = new Scanner(params[1]);
        long begin=-1;
        try{
            begin=scan.useDelimiter("\\D+").nextLong();
        }catch (Exception e){
            System.out.println("scanner can't scan begin from "+ Arrays.toString(this.params));
        }finally{
            scan.close();
        }
        return begin;
    }

    @Override
    public int getDuration() {
        Scanner scan = new Scanner(params[2]);
        int duration=-1;
        try{
            duration=scan.useDelimiter("\\D+").nextInt();
            duration/=1000;
        }catch (Exception e){
            System.out.println("scanner can't scan duration from "+ Arrays.toString(this.params));
        }finally{
            scan.close();
        }
        return duration;
    }

    @Override
    public int getPrice() {
        Scanner scan = new Scanner(params[3]);
        int price=-1;
        try{
            price=scan.useDelimiter("\\D+").nextInt();
        }catch (Exception e){
            System.out.println("scanner can't scan price from "+ Arrays.toString(this.params));
        }finally{
            scan.close();
        }
        return price;
    }

    @Override
    public String getTicket() {
        Scanner scan = new Scanner(params[4]);
        String ticket=null;
        try{
            ticket=scan.useDelimiter("\\D+").findInLine("[\\da-f]{32}");
        }finally{
            scan.close();
        }
        return ticket;
    }

    @Override
    public String getColor() {
        Scanner scan = new Scanner(params[5]);
        String color=null;
        try{
            color=scan.useDelimiter("\\D+").findInLine("#[\\da-f]{6}");
        }finally{
            scan.close();
        }
        return color;
    }

    @Override
    public int getSpace() {
        Scanner scan = new Scanner(params[6]);
        int space=-1;
        try{
            space=scan.useDelimiter("\\D+").nextInt();
        }catch (Exception e){
            System.out.println("scanner can't scan space from "+ Arrays.toString(this.params));
        }finally{
            scan.close();
        }
        return space;
    }

    @Override
    public String getClient_category() {
        Scanner scan = new Scanner(params[7]);
        String client_category=null;
        try{
            client_category=scan.useDelimiter("\\D+").findInLine("(FAMILY|WOMEN|ANY|HANDICAPPED)");
        }finally{
            scan.close();
        }
        return client_category;
    }
    @Override
    public String getVehicle_type() {
        Scanner scan = new Scanner(params[8]);
        String vehicle_type=null;
        try{
            vehicle_type=scan.useDelimiter("\\D+").findInLine("(PKW|SUV|MOTORBIKE|E_VEHICLE)");
        }finally{
            scan.close();
        }
        return vehicle_type;
    }
    @Override
    public String getLicense() {
        Scanner scan = new Scanner(params[9]);
        String license=null;
        try{
            license=scan.useDelimiter("\\D+").findInLine("SU-[A-Z] [\\d]{1,3}");
        }finally{
            scan.close();
        }
        return license;
    }

    @Override
    public boolean isCorrect() {
        return (this.getNr()>-1&&this.getBegin()>-1&&this.getDuration()>-1&&
                this.getPrice()>-1&&this.getTicket()!=null&&
                this.getColor()!=null&&this.getClient_category()!=null&&
                this.getVehicle_type()!=null&&this.getLicense()!=null);
    }
}
