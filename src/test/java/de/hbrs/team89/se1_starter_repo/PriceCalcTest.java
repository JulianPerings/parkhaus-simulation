package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalcTest {
    PriceCalc testCalc = new PriceCalc();
    @Test
    void calcDayNightPrice_methodCorrect_expectsNear8() {
        long duration=1000*60*60*5;  //5 hours
        long begin=0;
        double expectedPrice=((5*60*60)/10d)*0.8;    //should cost 8 cent
        assertEquals(expectedPrice,testCalc.calcDayNightPrice(begin,duration),0.01);
        assertEquals(expectedPrice,testCalc.calcDayNightPrice(begin,duration),0.01);
    }
    @Test
    void calcDayNightPrice_wrongInput_expects0(){
        long duration=-1000*60*60*5;
        long begin=-10;
        double price=-10;
        double expectedPrice=0;
        assertEquals(expectedPrice,testCalc.calcDayNightPrice(begin,duration));
    }
    @Test
    void getNightPrice_expects1(){
        assertEquals(1.0,testCalc.getDayPrice(),0.01);
    }
    @Test
    void getDayPrice_expects0_8(){
        assertEquals(0.8,testCalc.getNightPrice(),0.01);
    }
    @Test
    void calcDayNightPrice_methodCorrect() {
        long duration=1000*60*60*5;  //5 hours
        long begin=1000*60*60*22;
        double expectedPrice=((5*60*60)/10d)*testCalc.getNightPrice();    //should cost 8 cent
        assertEquals(expectedPrice,testCalc.calcDayNightPrice(begin,duration),0.01);

    }

}