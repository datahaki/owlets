// code by jph
package ch.alpine.owlets.glc.core;

import ch.alpine.owlets.math.state.TrajectoryRegionQuery;

/** the cost to goal and the goal region have to be compatible in order
 * for the planner to work properly.
 * 
 * therefore the two concepts of distance and region query
 * are assembled into one {@link GoalInterface} */
public interface GoalInterface extends CostFunction, TrajectoryRegionQuery {
  // ---
}
