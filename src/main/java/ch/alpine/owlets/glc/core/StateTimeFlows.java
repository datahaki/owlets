// code by jph
package ch.alpine.owlets.glc.core;

import java.util.Collection;

import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.tensor.Tensor;

@FunctionalInterface
public interface StateTimeFlows {
  Collection<Tensor> flows(StateTime stateTime);
}
