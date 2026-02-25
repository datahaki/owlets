// code by jph
package ch.alpine.owlets.math.state;

import java.io.Serializable;
import java.util.function.Predicate;

import ch.alpine.sophis.api.Region;
import ch.alpine.tensor.Tensor;

/** StateTimeRegion that depends on time */
public final class TimeDependentRegion implements Region<StateTime>, Serializable {
  private final Predicate<Tensor> region;

  /** @param region */
  public TimeDependentRegion(Predicate<Tensor> region) {
    this.region = region;
  }

  /** @param StateTime of point to check
   * @return true if stateTime is member/part of/inside region */
  @Override
  public boolean test(StateTime stateTime) {
    return region.test(stateTime.joined());
  }
}
