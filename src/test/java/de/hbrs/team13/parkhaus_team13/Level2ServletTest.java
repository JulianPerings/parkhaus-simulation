package de.hbrs.team13.parkhaus_team13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Level2ServletTest {
  Level2Servlet test;

  @BeforeEach
  void setUp() {
    test = new Level2Servlet();
  }

  @Test
  void NAME_expects_Super_Duper_Parkhaus() {
    assertEquals("Level2", test.name());
  }

  @Test
  void MAX() {
    assertEquals(14, test.max());
  }

  @Test
  void config() {
    assertNotNull(test.config());
  }
}
