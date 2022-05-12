package de.hbrs.team89.se1_starter_repo;

public class PriceCalc {
    private float dayPrice = 1.0f;
    private float nightPrice = 0.8f;

    public PriceCalc() {

    }

    public double calcDayNightPrice(long beginInMillisec, long durationInMillisec) { //Time in milliseconds since unix; 3600000= hour; 86400000= day
        beginInMillisec=Math.max(beginInMillisec,0);
        durationInMillisec=Math.max(durationInMillisec,0);
        double priceInEuros = durationInMillisec / 10000d;
        long timer = beginInMillisec % 86400000;
        int nightTime = (int)(durationInMillisec/86400000)*8;
        int dayTime = (int)(durationInMillisec/86400000)*16;
        durationInMillisec%=86400000;
        if(timer>=3600000*22){
            long tmp = Math.min(durationInMillisec, 3600000 * 24 - timer);
            nightTime+= tmp;
            timer= 0;
            durationInMillisec-= tmp;
        }
        if(timer<3600000*6){
            long tmp = Math.min(durationInMillisec, 3600000 * 6 - timer);
            nightTime+= tmp;
            timer+= tmp;
            durationInMillisec-= tmp;
        }
        long parkedDay = Math.min(durationInMillisec, 3600000*22 -timer);
        parkedDay += Math.max(((timer+durationInMillisec)/86400000) * ((timer+durationInMillisec)%86400000) - 3600000*6, 0);
        dayTime += parkedDay;
        nightTime += durationInMillisec - parkedDay;
        double pricePerMillisecond = Math.abs(priceInEuros / (nightTime + dayTime + 1));// +1 prevents dividing with 0 and has barely any effect
        double price = pricePerMillisecond * nightTime * nightPrice + pricePerMillisecond * dayTime * dayPrice;
        System.out.println(priceInEuros + " " + price);
        return (Math.round(price * 100) / 100.0d);
    }

    public float getDayPrice() {
        return dayPrice;
    }

    public float getNightPrice() {
        return nightPrice;
    }

    /**
     * beginInMillisec: starting time in milliseconds since unix start,
     * durationInMillisec: duration in milliseconds.
     * Example: calcDayNightPrice(1651864726192, 1000)
     */
}