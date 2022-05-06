package de.hbrs.team89.se1_starter_repo;

public class PriceCalc {
    private float dayPrice=1.0f;
    private float nightPrice=0.8f;
    private double defaulPriceInEuros = 10.;
    public PriceCalc(){

    }

    public float getDayPrice(){
        return dayPrice;
    }

    public float getNightPrice(){
        return nightPrice;
    }

    /**beginInMillisec: starting time in milliseconds since unix start,
     * durationInMillisec: duration in milliseconds.
     * Example: calcDayNightPrice(1651864726192, 1000)
     */
    public double calcDayNightPrice(double priceInEuros,long beginInMillisec,int durationInMillisec){ //Time in milliseconds since unix; 3600000= hour; 86400000= day
        long timer = beginInMillisec%86400000;  //timer represents millisecond of the current day
        int nightTime=0,dayTime=0;
        while(durationInMillisec>0){
            durationInMillisec-=1000;   //decrease duration by one second
            timer=(timer+1000)%86400000;    //increase hour by one second
            if(timer<3600000*6||timer>3600000*22){  //if between 22:00 and 6:00
                nightTime+=1000;    //increase nightTime by 1000
            }else{
                dayTime+=1000;  //else increase dayTime by 1000
            }
        }
        double pricePerMillisecond=Math.abs(priceInEuros/(nightTime+dayTime+1));// +1 prevents dividing with 0 and has barely any effect
        double price=pricePerMillisecond*nightTime*nightPrice+pricePerMillisecond*dayTime*dayPrice;
        //System.out.println(priceInEuros+" "+price);
        return (Math.round(price*100)/100.0d);
    }

    public double calcDayNightPrice(long beginInMillisec,int durationInMillisec){ //Time in milliseconds since unix; 3600000= hour; 86400000= day
        long timer = beginInMillisec%86400000;  //timer represents millisecond of the current day
        int nightTime=0,dayTime=0;
        while(durationInMillisec>0){
            durationInMillisec-=1000;   //decrease duration by one second
            timer=(timer+1000)%86400000;    //increase hour by one second
            if(timer<3600000*6||timer>3600000*22){  //if between 22:00 and 6:00
                nightTime+=1000;    //increase nightTime by 1000
            }else{
                dayTime+=1000;  //else increase dayTime by 1000
            }
        }
        double pricePerMillisecond=Math.abs(defaulPriceInEuros/(nightTime+dayTime+1));// +1 prevents dividing with 0 and has barely any effect
        double price=pricePerMillisecond*nightTime*nightPrice+pricePerMillisecond*dayTime*dayPrice;
        //System.out.println(priceInEuros+" "+price);
        return (Math.round(price*100)/100.0d);
    }
}

