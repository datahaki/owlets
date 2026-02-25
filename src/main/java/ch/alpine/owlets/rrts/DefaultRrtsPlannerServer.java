// code by jph, gjoel
package ch.alpine.owlets.rrts;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.alpine.owlets.math.model.StateSpaceModel;
import ch.alpine.owlets.math.state.StateTime;
import ch.alpine.owlets.rrts.core.DefaultRrts;
import ch.alpine.owlets.rrts.core.DefaultRrtsPlanner;
import ch.alpine.owlets.rrts.core.GreedyRrtsPlanner;
import ch.alpine.owlets.rrts.core.Rrts;
import ch.alpine.owlets.rrts.core.RrtsNode;
import ch.alpine.owlets.rrts.core.RrtsNodeCollection;
import ch.alpine.owlets.rrts.core.RrtsPlanner;
import ch.alpine.owlets.rrts.core.TransitionCostFunction;
import ch.alpine.owlets.rrts.core.TransitionRegionQuery;
import ch.alpine.sophis.ts.TransitionSpace;
import ch.alpine.tensor.Scalar;
import ch.alpine.tensor.Tensor;
import ch.alpine.tensor.Tensors;
import ch.alpine.tensor.pdf.RandomSampleInterface;

public abstract class DefaultRrtsPlannerServer extends RrtsPlannerServer {
  private Tensor state = Tensors.empty();
  private Tensor goal = Tensors.empty();
  /** collection of control points */
  private Collection<Tensor> greeds = Collections.emptyList();

  protected DefaultRrtsPlannerServer( //
      TransitionSpace transitionSpace, //
      TransitionRegionQuery obstacleQuery, //
      Scalar resolution, //
      StateSpaceModel stateSpaceModel, //
      TransitionCostFunction costFunction) {
    super(transitionSpace, obstacleQuery, resolution, stateSpaceModel, costFunction);
  }

  @Override // from RrtsPlannerServer
  public final void setState(StateTime stateTime) {
    super.setState(stateTime);
    state = stateTime.state();
  }

  @Override // from RrtsPlannerServer
  public final void setGoal(Tensor goal) {
    this.goal = goal;
  }

  @Override // from RrtsPlannerServer
  protected final RrtsPlannerProcess setupProcess(StateTime stateTime) {
    Rrts rrts = new DefaultRrts(getTransitionSpace(), rrtsNodeCollection(), obstacleQuery, costFunction);
    Optional<RrtsNode> optional = rrts.insertAsNode(stateTime.state(), 5);
    if (optional.isPresent()) {
      Collection<Tensor> greeds_ = greeds.stream() //
          .filter(point -> !optional.orElseThrow().state().equals(point)) //
          .collect(Collectors.toList());
      RrtsPlanner rrtsPlanner = greeds_.isEmpty() //
          ? new DefaultRrtsPlanner(rrts, spaceSampler(state), goalSampler(goal)) //
          : new GreedyRrtsPlanner(rrts, spaceSampler(state), goalSampler(goal), greeds_).withGoal(goal);
      return new RrtsPlannerProcess(rrtsPlanner, optional.orElseThrow());
    }
    return null;
  }

  /** @param greeds collection of control points */
  public final void setGreeds(Collection<Tensor> greeds) {
    this.greeds = Objects.requireNonNull(greeds);
  }

  /** @return new rrts node collection */
  protected abstract RrtsNodeCollection rrtsNodeCollection();

  protected abstract RandomSampleInterface spaceSampler(Tensor state);

  protected abstract RandomSampleInterface goalSampler(Tensor state);
}
