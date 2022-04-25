package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsTest {
    Statistics s;
    @BeforeEach
    void setup(){
        s = new Statistics();
        String[] params = {"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"PKW\"",
                "\"license\": \"SU-X 47\""};

        String[] params2 = {"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 130000",
                "\"price\": 1300",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"PKW\"",
                "\"license\": \"SU-X 47\""};
        s.addCar(new Car(params));
        s.addCar(new Car(params2));
    }

    @Test
    void testSum(){
        assertEquals(0.23, s.getSum(), 0.1 );
    }

    @Test
    void testAvg(){
        assertEquals(0.115, s.getAvg(), 0.1);
    }

    @Test
    void testAvgTime(){
        assertEquals(114.5, s.getAvgTime(), 1);
    }
}
