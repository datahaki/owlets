// code by jph
package ch.alpine.owlets.math.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import ch.alpine.sophis.flow.StateSpaceModel;
import ch.alpine.sophis.flow.StateSpaceModels;
import ch.alpine.sophis.flow.TimeIntegrators;
import ch.alpine.tensor.RealScalar;
import ch.alpine.tensor.Scalar;
import ch.alpine.tensor.Scalars;
import ch.alpine.tensor.Tensor;
import ch.alpine.tensor.Tensors;

class SimpleEpisodeIntegratorTest {
  @ParameterizedTest
  @EnumSource
  void testSimple(TimeIntegrators integrators) {
    StateSpaceModel stateSpaceModel = StateSpaceModels.SINGLE_INTEGRATOR;
    Tensor x = Tensors.vector(1, 2);
    Tensor u = Tensors.vector(5, -2);
    Scalar t = RealScalar.of(3);
    Scalar p = RealScalar.of(2);
    AbstractEpisodeIntegrator aei = new SimpleEpisodeIntegrator( //
        stateSpaceModel, //
        integrators, new StateTime(x, t));
    Tensor flow = u;
    List<StateTime> list = aei.abstract_move(flow, p);
    assertEquals(list.size(), 1);
    Tensor cmp = x.add(u.multiply(p));
    assertEquals(list.get(0).state(), cmp);
    assertEquals(list.get(0).time(), t.add(p));
  }

  @ParameterizedTest
  @EnumSource
  void testUnits1(TimeIntegrators integrators) {
    StateSpaceModel stateSpaceModel = StateSpaceModels.SINGLE_INTEGRATOR;
    Tensor x = Tensors.fromString("{1[m], 2[m]}");
    Tensor u = Tensors.fromString("{5[m], -2[m]}");
    Scalar t = RealScalar.of(3);
    Scalar p = RealScalar.of(2);
    AbstractEpisodeIntegrator aei = new SimpleEpisodeIntegrator( //
        stateSpaceModel, //
        integrators, new StateTime(x, t));
    Tensor flow = u;
    List<StateTime> list = aei.abstract_move(flow, p);
    assertEquals(list.size(), 1);
    Tensor cmp = x.add(u.multiply(p));
    assertEquals(list.get(0).state(), cmp);
    assertEquals(list.get(0).time(), t.add(p));
  }

  @ParameterizedTest
  @EnumSource
  void testUnits2(TimeIntegrators integrators) {
    StateSpaceModel stateSpaceModel = StateSpaceModels.SINGLE_INTEGRATOR;
    Tensor x = Tensors.fromString("{1[m], 2[m]}");
    Tensor u = Tensors.fromString("{5[m*s^-1], -2[m*s^-1]}");
    Scalar t = Scalars.fromString("3[s]");
    Scalar p = Scalars.fromString("2[s]");
    AbstractEpisodeIntegrator aei = new SimpleEpisodeIntegrator( //
        stateSpaceModel, //
        integrators, new StateTime(x, t));
    Tensor flow = u;
    List<StateTime> list = aei.abstract_move(flow, p);
    assertEquals(list.size(), 1);
    Tensor cmp = x.add(u.multiply(p));
    assertEquals(list.get(0).state(), cmp);
    assertEquals(list.get(0).time(), t.add(p));
  }
}
