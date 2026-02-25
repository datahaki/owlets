// code by jph
package ch.alpine.owlets.math.state;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TimeInvariantRegionTest {
  @Test
  void testFailNull() {
    assertThrows(Exception.class, () -> new TimeInvariantRegion(null));
  }
}
