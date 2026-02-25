// code by jph
package ch.alpine.owlets.glc.core;

import java.io.Serializable;
import java.util.Collection;

import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.tensor.Tensor;

public class InvariantFlows implements StateTimeFlows, Serializable {
  private final Collection<Tensor> collection;

  public InvariantFlows(Collection<Tensor> collection) {
    this.collection = collection;
  }

  @Override
  public Collection<Tensor> flows(StateTime stateTime) {
    return collection;
  }
}
