// code by jph
package ch.alpine.owlets.glc.adapter;

import java.util.List;
import java.util.function.Predicate;

import ch.alpine.owlets.glc.core.CostFunction;
import ch.alpine.owlets.glc.core.GlcNode;
import ch.alpine.owlets.glc.core.GoalInterface;
import ch.alpine.owlets.math.state.SimpleTrajectoryRegionQuery;
import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.tensor.Scalar;
import ch.alpine.tensor.Tensor;
import ch.alpine.tensor.chq.MemberQ;

/** suggested base class for a goal region with cost function based on elapsed time */
public abstract class AbstractMinTimeGoalManager implements MemberQ, CostFunction {
  private final Predicate<Tensor> region;

  protected AbstractMinTimeGoalManager(Predicate<Tensor> region) {
    this.region = region;
  }

  @Override // from CostFunction
  public final Scalar costIncrement(GlcNode glcNode, List<StateTime> trajectory, Tensor flow) {
    return StateTimeTrajectories.timeIncrement(glcNode, trajectory);
  }

  @Override // from Region
  public final boolean test(Tensor element) {
    return region.test(element);
  }

  public final GoalInterface getGoalInterface() {
    return new GoalAdapter(SimpleTrajectoryRegionQuery.timeInvariant(this), this);
  }
}
