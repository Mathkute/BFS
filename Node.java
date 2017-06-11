/*
 *
 */

package bfs;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Node implements Comparable<Node> {

    Node parent;
    State s;
    double f, g, h;
    Map<State, Node> mn;

    public State getState() {return this.s;}
    public Node getParent() {return this.parent;}

    public Node(State s1){ 
        this.s = s1; 
        this.mn = new HashMap<>(); 
        this.h  = s1.heuristic();
        this.g  = 0.0;
        this.f = this.g + this.h;
        this.mn.put(this.s, this);
    } /* constructor */

    private Node newNode(State s1) {
        Node n = new Node(s1);
        n.h = s1.heuristic();
        n.g = this.g + 1;
        n.f = n.g + n.h;
        n.mn = this.mn;
        n.parent = this;
        return n;
    } /* newNode() */

    public List<Node> generateSuccessors() {
        List<Node> ln = new ArrayList<>();
        Iterator<State> iss = this.s.generateSuccessors().iterator();
        while(iss.hasNext()) { 
            State s = iss.next(); 
            Node n = this.newNode(s);
            if(mn.get(s) == null) { mn.put(n.s, n); ln.add(n);}
            if(n.f < mn.get(n.s).f) ln.add(n); 
        }
        return ln;
    } /* generateSuccessors() */

    public boolean isGoal(){return this.s.isGoal();}

    // TODO: review if there is an issue about comparing two nodes that 
    //       correspond to the same state: the logic presumes compareTo() will
    //       be called only by the priority queue, but one may use it for
    //       other purposes, so it could be a flawed design.
    public int compareTo(Node n) { return (this.f < n.f? -1:(this.f > n.f? 1: 0));} /* compareTo() */
} /* class Node */

/*
 *
 */
