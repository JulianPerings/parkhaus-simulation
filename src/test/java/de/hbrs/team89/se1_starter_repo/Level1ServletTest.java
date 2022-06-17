package de.hbrs.team89.se1_starter_repo;

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
        assertEquals("Level1",test.NAME());
    }

    @Test
    void MAX() {
        assertEquals(11,test.MAX());
    }

    @Test
    void config() {
        assertNotNull(test.config());
    }
}