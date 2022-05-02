package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    String[] params1,params2;
    Car car1,car2;
    @BeforeEach
    void init(){
        params1 = new String[]{"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"HANDICAPPED\"",
                "\"vehicle_type\": \"MOTORBIKE\"",
                "\"license\": \"SU-X 47\""};
        params2 = new String[]{"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"SUV\"",
                "\"license\": \"SU-X 47\""};
        car1 = new Car(params1);
        car2 = new Car(params2);
    }
    @Test
    void nr() {
        assertEquals(11,car1.nr());
    }

    @Test
    void begin() {
        assertEquals(car2.begin(),car1.begin());
    }

    @Test
    void end() {
        assertEquals(car2.begin()+car2.getDuration(),car1.end());
    }

    @Test
    void getDuration() {
        assertEquals(99,car1.getDuration());
    }

    @Test
    void getPrice() {
        assertEquals(car1.getPrice(),991/10000.0);
    }

    @Test
    void getVehicleType() {
        assertEquals("MOTORBIKE",car1.getVehicleType());
    }

    @Test
    void getClientCategory() {
        assertEquals("HANDICAPPED",car1.getClientCategory());
    }

    @Test
    void testToString() {
        assertEquals(Arrays.toString(params1),car1.toString());
    }

    @Test
    void testEquals() {
        assertTrue(car1.equals(car1));
        assertFalse(car1.equals(car2));
    }

    @Test
    void sortOutPriority() {
        assertEquals(Arrays.toString(car1.priority),Arrays.toString(new String[]{"HANDICAPPED", "MOTORBIKE", "SUV", "PKW", "ANY"}));
        assertEquals(Arrays.toString(car2.priority),Arrays.toString(new String[]{"WOMEN", "SUV", "PKW", "ANY"}));
    }

    @Test
    void getPriority() {
        assertEquals(car1.priority,car1.getPriority());
    }
}