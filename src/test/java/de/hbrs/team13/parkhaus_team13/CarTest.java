package de.hbrs.team13.parkhaus_team13;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.hbrs.team13.parkhaus_team13.Car.*;
import static org.junit.jupiter.api.Assertions.*;
public class CarTest {
    String[] params1,params2,params3;
    Car car1,car2;
    @BeforeEach
    void setup() {
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
                "\"hash\": \"00008ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"SUV\"",
                "\"license\": \"SU-X 47\""};
        params3 = new String[]{"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"E_VEHICLE\"",
                "\"license\": \"SU-X 47\""};
        car1 = new Car(params1);
        car2 = new Car(params2);
        Car car3 = new Car(null);
        assertNull(car3.priority);
    }
    @Test
    void Car_expectsRandomCars(){
        List<Car> test= new ArrayList<>();
        for(int i=0;i<100;i++){
            Car testcar=new Car();
            for(Car x:test){
                assertFalse(x.equals(testcar));
            }
            test.add(testcar);
        }
    }
    @Test
    void Car_expectsRandomCarsWithSetNumber(){
        List<Car> test= new ArrayList<>();
        for(int i=0;i<100;i++){
            Car testcar=new Car(i);
            assertEquals(i,testcar.nr());
            for(Car x:test){
                assertFalse(x.equals(testcar));
            }
            test.add(testcar);
        }
    }
    @Test
    void getSecureRandomNumber_expects0(){
        assertEquals(0,getSecureRandomNumber(0L));
        assertEquals(0,getSecureRandomNumber(0));
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
    void getSpace_expects14(){
        assertEquals(14,car1.getSpace());
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
    void sortOutPriority() {
        Car car3 = new Car(new String[]{});
        assertNull(car3.priority);
        assertEquals(Arrays.toString(car1.priority),Arrays.toString(new String[]{"HANDICAPPED", "MOTORBIKE", "SUV", "PKW", "ANY"}));
        System.out.println(Arrays.toString(car2.priority));
        assertEquals(Arrays.toString(car2.priority),Arrays.toString(new String[]{"WOMEN", "SUV", "PKW", "ANY"}));
        System.out.println(Arrays.toString(new Car(params3).getPriority()));
    }

    @Test
    void getPriority() {
        assertEquals(car1.priority,car1.getPriority());
    }


    @Test
    void equals_2Cars_ShouldReturnFalse() {
        assertFalse(car1.equals(car2));
    }

    @Test
    void equals_2Cars_ShouldReturnTrue() {
        assertEquals(car1, car1);
    }
    @Test
    void equals(){
        assertFalse(new Car().equals(null));
    }

    @Test
    void export_SingleCar_ShouldReturnExportStringInFormat(){
        assertEquals("11/1650896019513/99/0.0991/c6d68ad63d346c13bd5345ec6f40b039/#f15bec/HANDICAPPED/MOTORBIKE/SU-X 47", car1.export());
        assertEquals("11/1650896019513/99/0.0991/00008ad63d346c13bd5345ec6f40b039/#f15bec/WOMEN/SUV/SU-X 47", car2.export());
    }
}
