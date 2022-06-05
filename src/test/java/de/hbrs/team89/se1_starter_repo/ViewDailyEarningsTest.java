package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewDailyEarningsTest {
    @Test
    void test(){
        String[] params = new String[]{"\"nr\": 11",
                "\"timer\": "+(System.currentTimeMillis() - 99101),
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"E_VEHICLE\"",
                "\"license\": \"SU-X 47\""};
        String[] params2 = new String[]{"\"nr\": 11",
                "\"timer\": "+(System.currentTimeMillis() - 9910000001l) ,
                "\"duration\": 9910000",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"E_VEHICLE\"",
                "\"license\": \"SU-X 47\""};
        Car car1 = new Car(params);
        Car car2 = new Car(params2);
        Statistics s = new Statistics();

        ViewDailyEarnings v = new ViewDailyEarnings(s);

        s.addCar(car1);
        s.addCar(car2);

        assertEquals(car1.getPrice(), v.get());
    }


}