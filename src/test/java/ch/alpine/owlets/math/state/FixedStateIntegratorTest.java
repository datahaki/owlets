// code by jph
package ch.alpine.owlets.math.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import ch.alpine.sophis.flow.StateSpaceModel;
import ch.alpine.sophis.flow.StateSpaceModels;
import ch.alpine.sophis.flow.TimeIntegrators;
import ch.alpine.tensor.Rational;
import ch.alpine.tensor.RealScalar;
import ch.alpine.tensor.Scalars;
import ch.alpine.tensor.Tensor;
import ch.alpine.tensor.Tensors;
import ch.alpine.tensor.qty.Quantity;

class FixedStateIntegratorTest {
  @Test
  void testSimple() {
    StateSpaceModel stateSpaceModel = StateSpaceModels.SINGLE_INTEGRATOR;
    FixedStateIntegrator fsi = //
        new FixedStateIntegrator(TimeIntegrators.EULER, stateSpaceModel, Quantity.of(Rational.of(1, 2), "s"), 3);
    Tensor u = Tensors.fromString("{1[m*s^-1],2[m*s^-1]}");
    Tensor init = Tensors.fromString("{2[m],3[m]}");
    List<StateTime> list = fsi.trajectory(new StateTime(init, Quantity.of(Rational.of(10, 1), "s")), u);
    assertEquals(list.size(), 3);
    assertEquals(list.get(2).time(), Scalars.fromString("(10+3/2)[s]"));
    assertEquals(fsi.getTimeStepTrajectory(), Quantity.of(Rational.of(3, 2), "s"));
    assertEquals(list.get(2).state(), Tensors.fromString("{7/2[m], 6[m]}"));
  }

  @Test
  void testFail1() {
    assertThrows(Exception.class, () -> new FixedStateIntegrator(TimeIntegrators.EULER, StateSpaceModels.SINGLE_INTEGRATOR, RealScalar.of(-.1), 3));
  }

  @Test
  void testFail2() {
    assertThrows(Exception.class, () -> new FixedStateIntegrator(TimeIntegrators.EULER, StateSpaceModels.SINGLE_INTEGRATOR, RealScalar.of(0), 3));
  }
}
