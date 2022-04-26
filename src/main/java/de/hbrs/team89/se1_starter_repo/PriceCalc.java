package de.hbrs.team89.se1_starter_repo;

public class PriceCalc {
    float dayPrice=1.0f;
    float nightPrice=0.8f;
    public PriceCalc(){

    }
    public double calcDayNightPrice(double priceInEuros,long begin,int duration){ //Time in milliseconds since unix; 3600000= hour; 86400000= day
        System.out.println("Euros"+priceInEuros);
        long timer = begin%86400000;
        System.out.println(timer+" Timer; Duration "+duration);
        int nightTime=0,dayTime=0;
        //while(duration>=86400000){duration-=86400000;nightTime+=3600000*8;dayTime+=3600000*16;}
        while(duration>0){
            duration--;
            timer=(timer+1)%86400000;
            if(timer<3600000*6||timer>3600000*22){
                nightTime++;
            }else{
                dayTime++;
            }
        }
        double pricePerMillisecond=priceInEuros/(nightTime+dayTime+1);// +1 prevents dividing with 0 and has barely any effect
        double price=pricePerMillisecond*nightTime*nightPrice+pricePerMillisecond*dayTime*dayPrice;
        System.out.println(priceInEuros+" "+price);
        return ((double)(((int)(price*100))/100.0d));
    }
}

