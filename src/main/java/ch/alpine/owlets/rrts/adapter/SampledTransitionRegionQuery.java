// code by jph
package ch.alpine.owlets.rrts.adapter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.owlets.math.state.StateTimeCollector;
import ch.alpine.owlets.rrts.core.TransitionRegionQuery;
import ch.alpine.sophis.ts.Transition;
import ch.alpine.tensor.Scalar;
import ch.alpine.tensor.Tensor;

public class SampledTransitionRegionQuery implements TransitionRegionQuery, StateTimeCollector, Serializable {
  private final Predicate<Tensor> region;
  private final Scalar dt;

  public SampledTransitionRegionQuery(Predicate<Tensor> region, Scalar dt) {
    this.region = region;
    this.dt = dt;
  }

  @Override
  public boolean isDisjoint(Transition transition) {
    return transition.sampled(dt).stream() //
        .noneMatch(region);
  }

  @Override
  public Collection<StateTime> getMembers() {
    // if (trajectoryRegionQuery instanceof StateTimeCollector)
    // return ((StateTimeCollector) trajectoryRegionQuery).getMembers();
    return Collections.emptyList();
  }
}
