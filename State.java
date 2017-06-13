/*
 *
 */

package bfs;

import java.util.Collection;

public interface State<T extends Comparable<T>> {
    public boolean isGoal();
    public Collection<State<T>> generateSuccessors(); 
    public T heuristic();
}

/*
 *
 */
