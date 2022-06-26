package de.hbrs.team13.parkhaus_team13;

import java.rmi.server.ExportException;
import java.util.Arrays;
import java.util.Scanner;

public class InputAdapter implements InputAdapterIF{
    String[] params;
    public InputAdapter(String[] params){
        this.params=params;
    }
    @Override
    public int getNr() {
        int nr=-1;
        try{
            Scanner scan = new Scanner(params[0].replace("\n",""));
            nr=scan.useDelimiter("\\D+").nextInt();
            scan.close();
        }catch (Exception ignored){
        }
        if(nr==-1){
            System.out.println("scanner can't scan nr from "+ Arrays.toString(this.params));
        }
        return nr;
    }

    @Override
    public long getBegin() {

        long begin=-1;
        try{
            Scanner scan = new Scanner(params[1].replace("\n",""));
            begin=scan.useDelimiter("\\D+").nextLong();
            scan.close();
        }catch (Exception ignored){

        }
        if(begin==-1){
            System.out.println("scanner can't scan begin from "+ Arrays.toString(this.params));
        }
        return begin;
    }

    @Override
    public int getDuration() {

        int duration=-1;
        try{
            Scanner scan = new Scanner(params[2].replace("\n",""));
            duration=scan.useDelimiter("\\D+").nextInt();
            duration/=1000;
            scan.close();
        }catch (Exception ignored){
        }
        if(duration==-1){
            System.out.println("scanner can't scan duration from "+ Arrays.toString(this.params));
        }
        return duration;
    }

    @Override
    public int getPrice() {

        int price=-1;
        try{
            Scanner scan = new Scanner(params[3].replace("\n",""));
            price=scan.useDelimiter("\\D+").nextInt();
            scan.close();
        }catch (Exception ignored){

        }
        if(price==-1){
            System.out.println("scanner can't scan price from "+ Arrays.toString(this.params));
        }
        return price;
    }

    @Override
    public String getTicket() {

        String ticket=null;
        try{
            Scanner scan = new Scanner(params[4].replace("\n",""));
            ticket=scan.findInLine("[\\da-f]{32}");
            scan.close();
        }catch (Exception ignored){

        }
        if(ticket==null){
            System.out.println("scanner can't scan ticket from "+ Arrays.toString(this.params));
        }
        return ticket;
    }

    @Override
    public String getColor() {

        String color=null;
        try{
            Scanner scan = new Scanner(params[5].replace("\n",""));
            color=scan.useDelimiter("\\D+").findInLine("#[\\da-f]{6}");
            scan.close();
        }catch(Exception ignored) {
        }
        if(color==null){
            System.out.println("scanner can't scan price from "+ Arrays.toString(this.params));
        }
        return color;
    }

    @Override
    public int getSpace() {

        int space=-1;
        try{
            Scanner scan = new Scanner(params[6].replace("\n",""));
            space=scan.useDelimiter("\\D+").nextInt();
            scan.close();
        }catch (Exception ignored){

        }
        if(space==-1){
            System.out.println("scanner can't scan space from "+ Arrays.toString(this.params));
        }
        return space;
    }

    @Override
    public String getClient_category() {

        String client_category = null;
        try {
            Scanner scan = new Scanner(params[7].replace("\n",""));
            client_category = scan.useDelimiter("\\D+").findInLine("(FAMILY|WOMEN|ANY|HANDICAPPED)");
            scan.close();
        }catch(Exception ingonred){

        }
        if(client_category==null){
            System.out.println("scanner can't scan client_category from "+ Arrays.toString(this.params));
        }
        return client_category;
    }
    @Override
    public String getVehicle_type() {

        String vehicle_type=null;
        try{
            Scanner scan = new Scanner(params[8].replace("\n",""));
            vehicle_type=scan.useDelimiter("\\D+").findInLine("(PKW|SUV|MOTORBIKE|E_VEHICLE)");
            scan.close();
        }catch(Exception ignored) {

        }
        if(vehicle_type==null){
            System.out.println("scanner can't scan vehicle_type from "+ Arrays.toString(this.params));
        }
        return vehicle_type;
    }
    @Override
    public String getLicense() {

        String license=null;
        try{
            Scanner scan = new Scanner(params[9].replace("\n",""));
            license=scan.useDelimiter("\\D+").findInLine("SU-[A-Z] [\\d]{1,3}");
            scan.close();
        }catch(Exception ignored){

        }
        if(license==null){
            System.out.println("scanner can't scan license from "+ Arrays.toString(this.params));
        }
        return license;
    }

    @Override
    public boolean isCorrect() {
        return (this.getNr()>=0&&this.getBegin()>=0&&this.getDuration()>=0&&
                this.getPrice()>=0&&this.getTicket()!=null&&
                this.getColor()!=null&&this.getClient_category()!=null&&
                this.getVehicle_type()!=null&&this.getLicense()!=null);
    }
}
