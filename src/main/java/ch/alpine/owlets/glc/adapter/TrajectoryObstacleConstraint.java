// code by jph
package ch.alpine.owlets.glc.adapter;

import java.io.Serializable;
import java.util.List;

import ch.alpine.owlets.glc.core.GlcNode;
import ch.alpine.owlets.glc.core.PlannerConstraint;
import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.owlets.math.state.TrajectoryRegionQuery;
import ch.alpine.tensor.Tensor;

/** class that wraps a given {@link TrajectoryRegionQuery} to an obstacle constraint.
 * non-empty intersection of the trajectory with the region represents a constraint
 * violation. */
public record TrajectoryObstacleConstraint(TrajectoryRegionQuery trajectoryRegionQuery) //
    implements PlannerConstraint, Serializable {
  @Override // from PlannerConstraint
  public boolean isSatisfied(GlcNode glcNode, List<StateTime> trajectory, Tensor flow) {
    return trajectoryRegionQuery.firstMember(trajectory).isEmpty();
  }
}
