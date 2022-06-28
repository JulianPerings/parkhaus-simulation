package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseServletTest {
  BaseServlet test;

  @BeforeEach
  void setUp() {
    test = new BaseServlet();
  }

  @Test
  void NAME_expects_Super_Duper_Parkhaus() {
    assertEquals("Super Duper Parkhaus", test.name());
  }

  @Test
  void MAX() {
    assertEquals(10, test.max());
  }

  @Test
  void config() {

    assertNull(test.config());
  }
}
