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

public class BFS {
    Node root;
    public BFS(Node n) { this.root = n;} /* constructor */

    public Node search() {
        Queue<Node> openlist = new PriorityQueue<>();
        Set<Node> considered = new HashSet<>();

        int count=0;
        openlist.add(this.root);
        while(openlist.size() > 0) {
            Node n = openlist.poll();
            Iterator<Node> iss = n.generateSuccessors().iterator();
            while(iss.hasNext()) {
                Node s = iss.next();
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
