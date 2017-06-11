/*
 *
 */

package bfs;

import java.util.List;

public interface State {
    public boolean isGoal();
    public List<State> generateSuccessors();
    public double heuristic();
}

/*
 *
 */
