// code by gjoel
package ch.alpine.owlets.rrts.core;

import ch.alpine.owlets.data.tree.TreePlanner;

public interface TransitionPlanner extends TreePlanner<RrtsNode> {
  void checkConsistency();
}
