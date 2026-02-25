// code by gjoel
package ch.alpine.owlets.rrts;

import java.util.Objects;

import ch.alpine.owlets.rrts.core.RrtsNode;
import ch.alpine.owlets.rrts.core.RrtsPlanner;

/* package */ class RrtsPlannerProcess {
  private final RrtsPlanner rrtsPlanner;
  private final RrtsNode root;

  public RrtsPlannerProcess(RrtsPlanner rrtsPlanner, RrtsNode root) {
    this.rrtsPlanner = Objects.requireNonNull(rrtsPlanner);
    this.root = Objects.requireNonNull(root);
  }

  public RrtsPlanner planner() {
    return rrtsPlanner;
  }

  public RrtsNode root() {
    return root;
  }
}
