package de.hbrs.team13.parkhaus_team13;

public class PriceCalc {
    private float dayPrice = 1.0f;
    private float nightPrice = 0.8f;

    /**
     * calculates price for parking cars based on the time, they started parking and their parking duration
     * @param beginInMillisec timetstamp when they started parking in milliseconds since unix birth
     * @param durationInMillisec duration how long the car parked in milliseconds
     * @return price in euros as double value
     */
    public double calcDayNightPrice(long beginInMillisec, long durationInMillisec) { //Time in milliseconds since unix; 3600000= hour; 86400000= day
        //In case that either of the parameters are negative then prevent an error by setting them to 0
        beginInMillisec=Math.max(beginInMillisec,0);
        durationInMillisec=Math.max(durationInMillisec,0);

        double priceInEuros = durationInMillisec / 10000d;

        //currentTimeOfDay represents current time of day  in milliseconds
        long currentTimeOfDay = beginInMillisec % 86400000;

        //wholeDays represents total days parking
        //durationsInMilliseconds now holds the remaining time that is less than one day
        int wholeDays = (int) (durationInMillisec/86400000);
        durationInMillisec%=86400000;

        //Example: Lets say that 2 days in total were spent parking. We know that one day has 8 hours of nighttime and
        //16 hours of daytime. Thus, we know that 2*8 = 16 hours were spent parking at night and 2*16 = 32 hours were spent parking at day.
        int nightTime = wholeDays*8;
        int dayTime = wholeDays*16;

        //If after 22:00 then calculate parking time till 00:00.
        //Increase nightTime by calculated time and shorten duration by the calculated time and
        //set current time to 00:00.
        if(currentTimeOfDay>=3600000*22){
            long tmp = Math.min(durationInMillisec, 3600000 * 24 - currentTimeOfDay);
            nightTime+= tmp;
            currentTimeOfDay= 0;
            durationInMillisec-= tmp;
        }
        //If before 6:00 then calculate parking time till 6:00.
        //Increase nightTime by calculated time and shorten duration by the calculated time and
        //set current time to 6:00.
        if(currentTimeOfDay<3600000*6){
            long tmp = Math.min(durationInMillisec, 3600000 * 6 - currentTimeOfDay);
            nightTime+= tmp;
            currentTimeOfDay+= tmp;
            durationInMillisec-= tmp;
        }

        /*Some examples to explain the following:
        Lets say we started at 8:00 and have 23:00 hours remaining.
        ParkedDay would now be 14 hours.
        Because (currentTimeOfDay+durationInMillisec) would be greater than 24 hours overlap would be 1.
        Thus, overlap * ((currentTimeOfDay+durationInMillisec)%86400000) would be ((currentTimeOfDay+durationInMillisec)%86400000).

        */
        long parkedDay = Math.min(durationInMillisec, 3600000*22 - currentTimeOfDay);
        long overlap = ((currentTimeOfDay+durationInMillisec)/86400000);    //1 if overlap, 0 if not
        parkedDay += Math.max(overlap * ((currentTimeOfDay+durationInMillisec)%86400000) - 3600000*6, 0);
        dayTime += parkedDay;
        nightTime += durationInMillisec - parkedDay;
        double pricePerMillisecond = Math.abs(priceInEuros / (nightTime + dayTime + 1));// +1 prevents dividing with 0 and has barely any effect
        double price = pricePerMillisecond * nightTime * nightPrice + pricePerMillisecond * dayTime * dayPrice;
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