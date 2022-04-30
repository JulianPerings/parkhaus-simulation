package de.hbrs.team89.se1_starter_repo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CarTest {
    Car car;
    Car car2;
    @BeforeEach
    void setup() {

        String[] car1Params = {"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"PKW\"",
                "\"license\": \"SU-X 47\""};

        String[] car2Params = {"\"nr\": 12",
                "\"timer\": 1650896019513",
                "\"duration\": 130000",
                "\"price\": 1300",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"PKW\"",
                "\"license\": \"SU-X 47\""};
        car = new Car(car1Params);
        car2 = new Car(car2Params);
    }

    @Test
    void equals_2Cars_ShouldReturnFalse() {
        assertFalse(car.equals(car2));
    }

    @Test
    void export_SingleCar_ShouldReturnExportStringInFormat(){
        assertEquals("11/1650896019513/99/0.0991/c6d68ad63d346c13bd5345ec6f40b039/#f15bec/WOMEN/PKW/SU-X 47", car.export());
        assertEquals("12/1650896019513/130/0.13/c6d68ad63d346c13bd5345ec6f40b039/#f15bec/WOMEN/PKW/SU-X 47", car2.export());
    }
}
