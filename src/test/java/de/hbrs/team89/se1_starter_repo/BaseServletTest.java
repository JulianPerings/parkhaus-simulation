package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseServletTest {
    BaseServlet test;
    @BeforeEach
    void setUp() {
        test= new BaseServlet();
    }

    @Test
    void NAME_expects_Super_Duper_Parkhaus() {
        assertEquals("Super Duper Parkhaus",test.NAME());
    }

    @Test
    void MAX() {
        assertEquals(10,test.MAX());
    }

    @Test
    void config() {
        assertNotNull(test.config());
    }
}