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
        Scanner scan = new Scanner(params[0].replace("\n",""));
        int nr=-1;
        try{
            nr=scan.useDelimiter("\\D+").nextInt();
        }catch (Exception ignored){
        }finally{
            scan.close();
        }
        if(nr==-1){
            System.out.println("scanner can't scan nr from "+ Arrays.toString(this.params));
        }
        return nr;
    }

    @Override
    public long getBegin() {
        Scanner scan = new Scanner(params[1].replace("\n",""));
        long begin=-1;
        try{
            begin=scan.useDelimiter("\\D+").nextLong();
        }catch (Exception ignored){

        }finally{
            scan.close();
        }
        if(begin==-1){
            System.out.println("scanner can't scan begin from "+ Arrays.toString(this.params));
        }
        return begin;
    }

    @Override
    public int getDuration() {
        Scanner scan = new Scanner(params[2].replace("\n",""));
        int duration=-1;
        try{
            duration=scan.useDelimiter("\\D+").nextInt();
            duration/=1000;
        }catch (Exception ignored){
        }finally{
            scan.close();
        }
        if(duration==-1){
            System.out.println("scanner can't scan duration from "+ Arrays.toString(this.params));
        }
        return duration;
    }

    @Override
    public int getPrice() {
        Scanner scan = new Scanner(params[3].replace("\n",""));
        int price=-1;
        try{
            price=scan.useDelimiter("\\D+").nextInt();
        }catch (Exception ignored){

        }finally{
            scan.close();
        }
        if(price==-1){
            System.out.println("scanner can't scan price from "+ Arrays.toString(this.params));
        }
        return price;
    }

    @Override
    public String getTicket() {
        Scanner scan = new Scanner(params[4].replace("\n",""));
        String ticket=null;
        try{
            ticket=scan.findInLine("[\\da-f]{32}");
        }catch (Exception ignored){

        } finally {
            scan.close();
        }
        if(ticket==null){
            System.out.println("scanner can't scan ticket from "+ Arrays.toString(this.params));
        }
        return ticket;
    }

    @Override
    public String getColor() {
        Scanner scan = new Scanner(params[5].replace("\n",""));
        String color=null;
        try{
            color=scan.useDelimiter("\\D+").findInLine("#[\\da-f]{6}");
        }catch(Exception ignored) {
        }finally{
            scan.close();
        }
        if(color==null){
            System.out.println("scanner can't scan price from "+ Arrays.toString(this.params));
        }
        return color;
    }

    @Override
    public int getSpace() {
        Scanner scan = new Scanner(params[6].replace("\n",""));
        int space=-1;
        try{
            space=scan.useDelimiter("\\D+").nextInt();
        }catch (Exception ignored){

        }finally{
            scan.close();
        }
        if(space==-1){
            System.out.println("scanner can't scan space from "+ Arrays.toString(this.params));
        }
        return space;
    }

    @Override
    public String getClient_category() {
        Scanner scan = new Scanner(params[7].replace("\n",""));
        String client_category = null;
        try {
            client_category = scan.useDelimiter("\\D+").findInLine("(FAMILY|WOMEN|ANY|HANDICAPPED)");
        }catch(Exception ingonred){

        }finally{
            scan.close();
        }
        if(client_category==null){
            System.out.println("scanner can't scan client_category from "+ Arrays.toString(this.params));
        }
        return client_category;
    }
    @Override
    public String getVehicle_type() {
        Scanner scan = new Scanner(params[8].replace("\n",""));
        String vehicle_type=null;
        try{
            vehicle_type=scan.useDelimiter("\\D+").findInLine("(PKW|SUV|MOTORBIKE|E_VEHICLE)");
        }catch(Exception ignored) {

        }finally{
            scan.close();
        }
        if(vehicle_type==null){
            System.out.println("scanner can't scan vehicle_type from "+ Arrays.toString(this.params));
        }
        return vehicle_type;
    }
    @Override
    public String getLicense() {
        Scanner scan = new Scanner(params[9].replace("\n",""));
        String license=null;
        try{
            license=scan.useDelimiter("\\D+").findInLine("SU-[A-Z] [\\d]{1,3}");
        }catch(Exception ignored){

        }finally{
            scan.close();
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
