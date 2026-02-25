// code by jl, jph
package ch.alpine.owlets.glc.adapter;

import ch.alpine.sophis.api.CoordinateWrap;
import ch.alpine.tensor.Tensor;

public enum IdentityWrap implements CoordinateWrap {
  INSTANCE;

  @Override // from CoordinateWrap
  public Tensor represent(Tensor x) {
    return x.copy();
  }

  @Override // from TensorDifference
  public Tensor difference(Tensor p, Tensor q) {
    return q.subtract(p);
  }
}
