package de.hbrs.team13.parkhaus_team13;

import java.security.SecureRandom;
import java.util.*;

//ToDO Protect against NULL PARAMS

/**
 * Car has values
 *  String[] params and
 *  String[] priority
 */
public class Car implements CarIF {
    /**
     * storage medium for
     * car number, time when the car entered the parking garage, duration car parked,
     * price paid by the customer , ticket as hash value, color of the car,
     * value how much space the vehicle needs, the clients' category, the vehicle type and the license
     */
    private String[] params;
    /**
     * array with parking lot priority details. Defines the order in which he will park on the remaining parking lots
     */
    String[] priority;
    public Car( String[] params ){
        this.params = params;
        if(this.params != null) {
            sortOutPriority();
        }
    }
    /**
     * random generator for car constructor with start time and duration based on current time and random nr between 0 and 1000
     */
    public Car(){
        this(getSecureRandomNumber(1000));
    }

    /**
     * random generator for car constructor with start time and duration based on current time
     * @param nr sets the first param value
     */
    public Car(int nr){
        long startzeit=System.currentTimeMillis()-(((long) getSecureRandomNumber(1000) * 60 * 60 * 24));
        long dauer=(getSecureRandomNumber(System.currentTimeMillis()-startzeit))*1000;
        StringBuilder hash= new StringBuilder();
        for(int i=0;i<32;i++){
            hash.append(Integer.toHexString((getSecureRandomNumber(16))));
        }
        String[] randParams=new String[10];
        randParams[0]="\"nr\": "+ nr;
        randParams[1]="\"timer\": "+startzeit;
        randParams[2]="\"duration\": "+dauer;
        randParams[3]="\"price\": "+(dauer/10000L);
        randParams[4]="\"hash\": \""+hash+"\"";
        randParams[5]="\"color\": \"#"+hash.substring(0,6)+"\"";
        randParams[6]="\"space\": "+getSecureRandomNumber(25);
        randParams[7]="\"client_category\": \""+(new String[]{"FAMILY","WOMEN","ANY","HANDICAPPED"}[getSecureRandomNumber(4)])+"\"";
        randParams[8]="\"vehicle_type\": \""+(new String[]{"PKW","SUV","MOTORBIKE","E_VEHICLE"}[getSecureRandomNumber(4)])+"\"";
        randParams[9]="\"license\": \"SU-X "+(getSecureRandomNumber(100)) +"\"";
        this.params=randParams;
        sortOutPriority();
    }

    /**
     *
     * @return Number of car > 0 if given, if not set then 0.
     */
    @Override
    public int nr() {
        Scanner scan = new Scanner(params[0]);
        int nr=0;
        try{
            nr=scan.useDelimiter("\\D+").nextInt();
        }catch (Exception e){
            System.out.println("scanner can't scan nr from "+this);
        }finally{
            scan.close();
        }
        return nr;
    }

    /**
     *
     * @return Time when car entered the garage in ms since 1.1.1970, if not set then 0.
     */
    @Override
    public long begin() {
        Scanner scan = new Scanner(params[1]);
        long begin=0;
        try{
            begin=scan.useDelimiter("\\D+").nextLong();
        }catch (Exception e){
            System.out.println("scanner can't scan begin from "+this);
        }finally{
            scan.close();
        }
        return begin;
    }

    /**
     *
     * @return Time the car stayed in the garage in ms, if not set then 0.
     */
    @Override
    public long end() {
        return this.begin() + this.getDuration();
    }

    /**
     * Duration how long the car parked in the parking garage in <big>seconds</big>
     * @return int time in <big>seconds</big>
     */
    @Override
    public int getDuration() {
        Scanner scan = new Scanner(params[2]);
        int duration=0;
        try{
            duration=scan.useDelimiter("\\D+").nextInt();
            duration/=1000;
        }catch (Exception e){
            System.out.println("scanner can't scan duration from "+this);
        }finally{
            scan.close();
        }
        return duration;
    }

    /**
     *
     * @return The price the car had to pay, if not set then 0.
     */
    @Override
    public double getPrice() {
        Scanner scan = new Scanner(params[3]);
        double price=0;
        try{
            price=scan.useDelimiter("\\D+").nextInt();
            price/=10000;
        }catch (Exception e){
            System.out.println("scanner can't scan price from "+this);
        }finally{
            scan.close();
        }
        return price;
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

    /**
     * generates a random number between 0 and maxnumber via the secure random byte generation method
     * @param maxnumber maximum of the random number
     * @return random int between 0 and maxnumber
     */
    static int getSecureRandomNumber(int maxnumber){
        if(maxnumber == 0){
            return 0;
        }
        SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
        byte[] randbytes = new byte[2];
        random.nextBytes(randbytes);
        return Math.abs(randbytes[0]+randbytes[1])%maxnumber;
    }
    /**
     * generates a random number between 0 and maxnumber via the secure random byte generation method
     * @param maxnumber maximum of the random number
     * @return random int between 0 and maxnumber
     */
    static long getSecureRandomNumber(long maxnumber){
        if(maxnumber == 0){
            return 0;
        }
        SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        return Math.abs(bytes[0]+bytes[1]+bytes[2]+bytes[3]+bytes[4]+bytes[5])%maxnumber;
    }

    @Override
    public int getSpace(){
        Scanner scan = new Scanner(params[6]);
        int space=0;
        try{
            space=scan.useDelimiter("\\D+").nextInt();
        }catch (Exception e){
            System.out.println("scanner can't scan space from "+this);
        }finally{
            scan.close();
        }
        return space;
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
    /**
     * returns all car values saved in params[] as one string split with /
     * @return string of all params[] values split with /
     */
    @Override
    public String export() {
        //Format: Nr, timer begin, duration, price, Ticket, color, space, client category, vehicle type, license
        return "" + nr() + "/" + begin() + "/" + getDuration() + "/"
                + getPrice() + "/" + getTicket() + "/" + getColor() + "/"
                + getClientCategory() + "/" + getVehicleType() + "/" + getLicense();}
    @Override
    public String toString() {
        return Arrays.toString(params);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car other = (Car)o;
        return this.getTicket().equals(other.getTicket());
    }
    /**
     * calculates the array priority for "this." Car from the standard arrangment:
     * {1_"HANDICAPPED", 2_"MOTORBIKE", 3_"E_VEHICLE", 4_"WOMEN", 5_"FAMILY", 6_"SUV", 7_"PKW", 8_"ANY"}
     */
    @Override
    public void sortOutPriority(){
        if(params.length >= 8) {    //ToDO Protect against NULL PARAMS
            ArrayList<String> sortOrder = new ArrayList<>();
            String[] s = new String[]{"HANDICAPPED", "MOTORBIKE", "E_VEHICLE", "WOMEN", "FAMILY", "SUV", "PKW", "ANY"};
            Collections.addAll(sortOrder, s);

            ArrayList<String> vSa = new ArrayList<>();
            String vHS = getVehicleType();
            addMissing(vSa, vHS, new String[]{"SUV", "PKW"});
            ArrayList<String> cSa = new ArrayList<>();
            String cHS = getClientCategory();
            addMissing(cSa, cHS, new String[]{"ANY"});
            priority = new String[vSa.size() + cSa.size()];
            int counterV = 0;
            int counterC = 0;
            for (int i = 0; i < priority.length; i++) {
                if (counterV == vSa.size()) {
                    addRest(i, counterC, cSa);
                    break;
                }/*Unreachable condition
                if (counterC == cSa.size()) {
                    addRest(i, counterV, vSa);
                    break;
                }*/
                if (sortOrder.indexOf(vSa.get(counterV)) < sortOrder.indexOf(cSa.get(counterC))) {
                    priority[i] = vSa.get(counterV++);
                } else {
                    priority[i] = cSa.get(counterC++);
                }
            }
        }
    }

    @Override
    public String[] getPriority() {return priority;}

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

    /**
     * @return params String[]
     */
    public String[] getParams(){
        return params;
    }
}
