// code by bapaden and jph
package ch.alpine.owlets.math.flow;

import ch.alpine.owlets.math.model.StateSpaceModel;
import ch.alpine.tensor.Scalar;
import ch.alpine.tensor.Tensor;

/** Numerical Recipes 3rd Edition (17.1.1) */
public enum EulerIntegrator implements Integrator {
  INSTANCE;

  @Override // from Integrator
  public Tensor step(StateSpaceModel stateSpaceModel, Tensor x, Tensor u, Scalar h) {
    return x.add(stateSpaceModel.f(x, u).multiply(h));
  }
}
