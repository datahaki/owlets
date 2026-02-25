// code by ynager
package ch.alpine.owlets.glc.adapter;

import java.util.Collection;
import java.util.List;

import ch.alpine.owlets.glc.core.GlcNode;
import ch.alpine.owlets.glc.core.PlannerConstraint;
import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.tensor.Tensor;

/** combines multiple PlannerConstraints
 * 
 * @see GoalAdapter */
public record MultiConstraintAdapter(Collection<PlannerConstraint> plannerConstraints) implements PlannerConstraint {
  /** @param plannerConstraints non-null
   * @return */
  @Override // from PlannerConstraint
  public boolean isSatisfied(GlcNode glcNode, List<StateTime> trajectory, Tensor flow) {
    return plannerConstraints.stream() //
        .allMatch(constraint -> constraint.isSatisfied(glcNode, trajectory, flow));
  }
}
