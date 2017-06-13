/*
 *
 */

import bfs.Node;
import bfs.State;

public class NodeImpl extends Node<Double> {
    NodeImpl(State<Double> s) {super(s);}
    public Double computeF(Double g, Double h){ return Double.sum(g,h);}
    public Node<Double> newNode(State<Double> s){ return new NodeImpl(s);}
}

/*
 *
 */
