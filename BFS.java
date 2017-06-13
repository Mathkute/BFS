/*
 *
 */

package bfs;

import java.util.List;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.PriorityQueue;

public class BFS<T extends Comparable<T>> {
    Node<T> root;
    public BFS(Node<T> n) { this.root = n;} /* constructor */

    public Node<T> search() {
        Queue<Node<T>> openlist = new PriorityQueue<>();
        Set<Node<T>> considered = new HashSet<>();

        int count=0;
        openlist.add(this.root);
        while(openlist.size() > 0) {
            Node<T> n = openlist.poll();
            Iterator<Node<T>> iss = n.generateSuccessors().iterator();
            while(iss.hasNext()) {
                Node<T> s = iss.next();
                if(s.isGoal()) { return s;}
                if(!considered.contains(s)) openlist.add(s);
            } /* while() successors */
            considered.add(n);
        } /* while() queue*/

        return null;
    } /* search() */
} /* class BFS */

/*
 *
 */
