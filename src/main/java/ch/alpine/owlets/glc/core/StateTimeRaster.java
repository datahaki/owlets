// code by jph
package ch.alpine.owlets.glc.core;

import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.owlets.math.state.StateTimeTensorFunction;
import ch.alpine.tensor.Tensor;

/** @see StateTimeTensorFunction */
@FunctionalInterface
public interface StateTimeRaster {
  /** Example: Floor(eta .* represent(state))
   * 
   * @param stateTime
   * @return */
  Tensor convertToKey(StateTime stateTime);
}
