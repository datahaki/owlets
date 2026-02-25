// code by ynager
package ch.alpine.owlets.glc.core;

/** decides if new node is better than existing node
 * TODO OWL ALG is this interface simply a (semi-)order relation: irreflexible, ... */
@FunctionalInterface
public interface RelabelDecision {
  /** @param newNode
   * @param oldNode
   * @return */
  boolean doRelabel(GlcNode newNode, GlcNode oldNode);
}
