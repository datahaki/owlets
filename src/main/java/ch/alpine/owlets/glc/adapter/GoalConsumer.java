// code by jph
package ch.alpine.owlets.glc.adapter;

import ch.alpine.tensor.Tensor;

@FunctionalInterface
public interface GoalConsumer {
  /** @param goal */
  void accept(Tensor goal);
}
