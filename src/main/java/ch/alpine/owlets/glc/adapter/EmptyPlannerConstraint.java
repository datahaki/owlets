// code by jph
package ch.alpine.owlets.glc.adapter;

import java.util.List;

import ch.alpine.owlets.glc.core.GlcNode;
import ch.alpine.owlets.glc.core.PlannerConstraint;
import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.tensor.Tensor;

/** adapter for planning without constraint, for instance no obstacles
 * the planning then only depends on the cost function and heuristic */
public enum EmptyPlannerConstraint implements PlannerConstraint {
  INSTANCE;

  @Override // from PlannerConstraint
  public boolean isSatisfied(GlcNode glcNode, List<StateTime> trajectory, Tensor flow) {
    return true;
  }
}
