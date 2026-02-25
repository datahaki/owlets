// code by jph
package ch.alpine.owlets.glc.adapter;

import java.util.List;

import ch.alpine.owlets.math.model.StateSpaceModel;
import ch.alpine.owlets.math.state.StateIntegrator;
import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.tensor.RealScalar;
import ch.alpine.tensor.Tensor;

public record DiscreteIntegrator(StateSpaceModel stateSpaceModel) implements StateIntegrator {
  @Override // from StateIntegrator
  public List<StateTime> trajectory(StateTime stateTime, Tensor u) {
    Tensor xn = stateSpaceModel.f(stateTime.state(), u);
    return List.of(new StateTime(xn, stateTime.time().add(RealScalar.ONE)));
  }
}
