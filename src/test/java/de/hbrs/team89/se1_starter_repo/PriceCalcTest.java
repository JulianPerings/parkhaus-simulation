package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalcTest {
    PriceCalc testCalc = new PriceCalc();
    @Test
    void calcDayNightPrice_methodCorrect_expectedNear8() {
        int duration=1000*60*60*5;
        long begin=0;
        double price=10;
        double expectedPrice=10*0.8;
        assertEquals(expectedPrice,testCalc.calcDayNightPrice(price,begin,duration),0.01);
    }
    @Test
    void calcDayNightPrice_wrongInput_expected0(){
        int duration=-1000*60*60*5;
        long begin=-10;
        double price=-10;
        double expectedPrice=0;
        assertEquals(expectedPrice,testCalc.calcDayNightPrice(price,begin,duration));
    }
}