// code by jph
package ch.alpine.owlets.rrts.adapter;

import ch.alpine.owlets.rrts.core.TransitionRegionQuery;
import ch.alpine.sophis.ts.Transition;

public enum EmptyTransitionRegionQuery implements TransitionRegionQuery {
  INSTANCE;

  // ---
  @Override // from TransitionRegionQuery
  public boolean isDisjoint(Transition transition) {
    return true;
  }
}
