// code by gjoel
package ch.alpine.owlets.rrts.core;

import java.util.Collection;

import ch.alpine.owlets.data.tree.ExpandInterface;

public interface RrtsPlanner extends ExpandInterface<RrtsNode> {
  /** @return unmodifiable view on queue for display and tests */
  Collection<RrtsNode> getQueue();

  /** @return obstacle query */
  TransitionRegionQuery getObstacleQuery();
}
