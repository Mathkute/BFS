/*
 *
 */

package bfs;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public abstract class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    public abstract T computeF(Double g, T h); /* computeF() */
    public abstract Node<T> newNode(State<T> st);

    Node<T> parent;
    State<T> s;
    T f, h; 
    double g;
    Map<State<T>, Node<T>>mn;

    public State<T> getState() {return this.s;}
    public Node<T> getParent() {return this.parent;}

    public Node(State<T> s1){ 
        this.s = s1; 
        this.mn = new HashMap<>(); 
        this.h  = s1.heuristic();
        this.g  = 0.0;
        this.f  = computeF(this.g, this.h);
        this.mn.put(this.s, this);
    } /* constructor */

    private Node<T> getNewNode(State<T> s1) {
        Node<T> n = newNode(s1);
        n.h = s1.heuristic();
        n.g = this.g + 1.0;
        n.f = n.computeF(n.g, n.h);
        n.mn = this.mn;
        n.parent = this;
        return n;
    } /* newNode() */

    public List<Node<T>> generateSuccessors() {
        List<Node<T>> ln = new ArrayList<>();
        Iterator<State<T>> iss = this.s.generateSuccessors().iterator();
        while(iss.hasNext()) { 
            State<T> s = iss.next(); 
            Node<T> n = this.getNewNode(s);
            if(mn.get(s) == null) { mn.put(n.s, n); ln.add(n);}
            if(n.f.compareTo(mn.get(n.s).f) <0 ) ln.add(n); 
        }
        return ln;
    } /* generateSuccessors() */

    public boolean isGoal(){return this.s.isGoal();}

    // TODO: review if there is an issue about comparing two nodes that 
    //       correspond to the same state: the logic presumes compareTo() will
    //       be called only by the priority queue, but one may use it for
    //       other purposes, so it could be a flawed design.
    public int compareTo(Node<T> n) {
        return this.f.compareTo(n.f);
        /* (this.f < n.f? -1:(this.f > n.f? 1: 0)); */
    } /* compareTo() */

} /* class Node */

/*
 *
 */
