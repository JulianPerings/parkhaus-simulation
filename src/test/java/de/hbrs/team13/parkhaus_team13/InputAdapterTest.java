package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputAdapterTest {
    InputAdapter testadapter,wrongadapter;
    Car testcar;
    @BeforeEach
    void setUp() {
        testcar=new Car();
        String[] params1=testcar.getParams();
        testadapter=new InputAdapter(params1);
        String[] params2= new String[]{"\"nr\": ",
                "\"timer\": ",
                "\"duration\": ",
                "\"price\": ",
                "\"hash\": \"b039\"",
                "\"color\": \"#f42\"",
                "\"space\": ",
                "\"client_category\": \"HANDICAPEDt\"",
                "\"vehicle_type\": \"MOTORBIKS\"",
                "\"license\": \"SU- 47\""};
        wrongadapter=new InputAdapter(params2);
    }

    @Test
    void getNr() {
        assertEquals(testcar.nr(),testadapter.getNr());
        assertEquals(-1,wrongadapter.getNr());
    }

    @Test
    void getBegin() {
        assertEquals(testcar.begin(),testadapter.getBegin());
        assertEquals(-1,wrongadapter.getBegin());
    }

    @Test
    void getDuration() {
        assertEquals(testcar.getDuration(),testadapter.getDuration());
        assertEquals(-1,wrongadapter.getDuration());
    }

    @Test
    void getPrice() {
        assertEquals(testcar.getPrice(),testadapter.getPrice()/10000.0);
        assertEquals(-1,wrongadapter.getPrice());
    }

    @Test
    void getTicket() {
        assertEquals(testcar.getTicket(),testadapter.getTicket());
        assertNull(wrongadapter.getTicket());
    }

    @Test
    void getColor() {
        assertEquals(testcar.getColor(),testadapter.getColor());
        assertNull(wrongadapter.getColor());
    }

    @Test
    void getSpace() {
        assertEquals(testcar.getSpace(),testadapter.getSpace());
        assertEquals(-1,wrongadapter.getSpace());
    }

    @Test
    void getClient_category() {
        assertEquals(testcar.getClientCategory(),testadapter.getClient_category());
        assertNull(wrongadapter.getClient_category());
    }

    @Test
    void getVehicle_type() {
        assertEquals(testcar.getVehicleType(),testadapter.getVehicle_type());
        assertNull(wrongadapter.getVehicle_type());
    }

    @Test
    void getLicense() {
        assertEquals(testcar.getLicense(),testadapter.getLicense());
        assertNull(wrongadapter.getLicense());
    }

    @Test
    void isCorrect() {
        assertTrue(testadapter.isCorrect());
        assertFalse(wrongadapter.isCorrect());
    }
}