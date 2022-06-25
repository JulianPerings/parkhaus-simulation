package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {
    Statistics s;

    @BeforeEach
    void setup() {
        s = new Statistics();
        String[] car1 = {"\"nr\": 11",
                "\"timer\": 1650896019513",
                "\"duration\": 99100",
                "\"price\": 991",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"WOMEN\"",
                "\"vehicle_type\": \"PKW\"",
                "\"license\": \"SU-X 47\""};

        String[] car2 = {"\"nr\": 12",
                "\"timer\": 1650896019513",
                "\"duration\": 130000",
                "\"price\": 1300",
                "\"hash\": \"c6d68ad63d346c13bd5345ec6f40b039\"",
                "\"color\": \"#f15bec\"",
                "\"space\": 14",
                "\"client_category\": \"ANY\"",
                "\"vehicle_type\": \"E_VEHICLE\"",
                "\"license\": \"SU-X 47\""};
        s.addCar(new Car(car1));
        s.addCar(new Car(car2));
    }

    @Test
    void getSum_2Cars_ShouldReturnValue() {
        assertEquals(0.23, s.getSum(), 0.1);
    }

    @Test
    void getTime_2Cars_ShouldReturnValue() {
        assertEquals(229.1, s.getTime(), 0.1);
    }

    @Test
    void getAvg_2Cars_ShouldReturnValue() {
        assertEquals(0.115, s.getAvg(), 0.1);
    }

    @Test
    void getAvg_0Cars_ShouldReturn0(){
        s = new Statistics();
        assertEquals(0, s.getAvg());
    }

    @Test
    void getAvgTime_2Cars_ShouldReturnValue() {
        assertEquals(114.5, s.getAvgTime(), 1);
    }

    @Test
    void getAvgTime_0Cars_ShouldReturn0(){
        s = new Statistics();
        assertEquals(0, s.getAvgTime());
    }

    @Test
    void test_VehicleTypes(){
        String[] vehicleTypes = {"SUV", "PKW", "MOTORBIKE", "E_VEHICLE"};
        assertArrayEquals(vehicleTypes, s.getVehicleTypes());
    }

    @Test
    void test_clientCategory(){
        String[] clientCategory = {"FAMILY", "WOMEN", "ANY", "HANDICAPPED"};
        assertArrayEquals(clientCategory, s.getClientCategory());
    }

    @Test
    void test_countVehicleType_Should_Return(){

        CarBuilder motorbikeCar = new CarBuilder();
        motorbikeCar.buildVehicle_type("MOTORBIKE");

        CarBuilder suvCar = new CarBuilder();
        suvCar.buildVehicle_type("SUV");

        assertArrayEquals(new int[]{0,1,0,1}, s.countVehicleType());
        s.addCar(motorbikeCar.buildCar());
        assertArrayEquals(new int[]{0,1,1,1}, s.countVehicleType());
        s.addCar(suvCar.buildCar());
        assertArrayEquals(new int[]{1,1,1,1}, s.countVehicleType());
        s.addCar(motorbikeCar.buildCar());
        assertArrayEquals(new int[]{1,1,2,1}, s.countVehicleType());
        s.addCar(suvCar.buildCar());
        assertArrayEquals(new int[]{2,1,2,1}, s.countVehicleType());
    }

    @Test
    void generateClientCategoryChart_2Cars_ShouldReturn50WomanAnd50Any() {
        assertEquals("{\"data\":[{\"values\":[0.0,50.0,50.0,0.0],\"labels\":[\"FAMILY\",\"WOMEN\",\"ANY\",\"HANDICAPPED\"],\"type\":\"pie\"}]}",
                s.generateClientCategoryChart());
    }

    @Test
    void generateVehicleTypeChart_2Cars_ShouldReturn50PkwAnd50EVehicle() {
        assertEquals("{\"data\":[{\"values\":[0.0,50.0,0.0,50.0],\"labels\":[\"SUV\",\"PKW\",\"MOTORBIKE\",\"E_VEHICLE\"],\"type\":\"pie\"}]}",
                s.generateVehicleTypeChart());
    }

    @Test
    void generateDurationChart_2Cars_ShouldReturnDurationChart() {
        assertEquals("{\"data\":[{\"x\":[11,12],\"y\":[99,130],\"type\":\"bar\",\"name\":\"Duration\"}]}",
                s.generateDurationChart());
    }

    @Test
    void generateBeginHeatmap_2Cars_ShouldReturn2AtMonday4pmHeatMap() {
        assertEquals("{\"data\":[{\"z\":[[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0]," +
                        "[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0]," +
                        "[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[2,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0]," +
                        "[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0]]," +
                        "\"x\":[\"Monday\",\"Tuesday\",\"Wednesday\",\"Thursday\",\"Friday\",\"Saturday\",\"Sunday\"]," +
                        "\"y\":[\"00:00am\",\"01:00am\",\"02:00am\",\"03:00am\",\"04:00am\",\"05:00am\",\"06:00am\",\"07:00am\"," +
                        "\"08:00am\",\"09:00am\",\"10:00am\",\"11:00am\",\"00:00pm\",\"01:00pm\",\"02:00pm\",\"03:00pm\",\"04:00pm\"," +
                        "\"05:00pm\",\"06:00pm\",\"07:00pm\",\"08:00pm\",\"09:00pm\",\"10:00pm\",\"11:00pm\"],\"type\":\"heatmap\"}]}",
                s.generateBeginHeatmap());
    }
    @Test
    void getMin_2Cars_ShouldReturnCar1(){
        assertEquals(s.getCarList().get(0), s.getMin());
    }

    @Test
    void getMin_2Cars_ShouldReturnFirstCar(){
        s = new Statistics();

        CarBuilder cb1 = new CarBuilder();
        cb1.buildDuration(99100);

        CarBuilder cb2 = new CarBuilder();
        cb2.buildDuration(130000);

        s.addCar(cb1.buildCar());
        s.addCar(cb2.buildCar());
        assertEquals(cb1.buildCar(), s.getMin());
    }

    @Test
    void getMin_0Cars_ShouldReturnNull(){
        s = new Statistics();
        assertNull(s.getMin());
    }

    @Test
    void getMax_2Cars_ShouldReturnCar2(){
        assertEquals(s.getCarList().get(1), s.getMax());
    }

    @Test
    void getMax_2Cars_ShouldReturnSecondCar(){
        s = new Statistics();
        CarBuilder cb1 = new CarBuilder();
        cb1.buildDuration(99100);

        CarBuilder cb2 = new CarBuilder();
        cb2.buildDuration(130000);

        s.addCar(cb1.buildCar());
        s.addCar(cb2.buildCar());
        assertEquals(cb2.buildCar(), s.getMax());
    }

    @Test
    void getMax_0Cars_ShouldReturnNull(){
        s = new Statistics();
        assertNull(s.getMax());
    }
}
