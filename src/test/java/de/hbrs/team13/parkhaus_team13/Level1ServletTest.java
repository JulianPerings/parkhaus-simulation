package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Level1ServletTest {
    Level1Servlet test;
    @BeforeEach
    void setUp() {
        test= new Level1Servlet();
    }

    @Test
    void NAME_expects_Super_Duper_Parkhaus() {
        assertEquals("Level1",test.name());
    }

    @Test
    void MAX() {
        assertEquals(11,test.max());
    }

    @Test
    void config() {
        assertNotNull(test.config());
    }
}