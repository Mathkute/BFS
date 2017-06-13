/*
 *
 */

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class PathState implements bfs.State<Double> {

    private Double[] h;
    public void pH() {
        int nx = this.board.nx, ny = this.board.ny;
        for(int j=0; j<ny; j++) {
            for(int i=0; i<nx; i++)
                System.err.print(String.format("%4.1f ",h[i+j*nx]));
            System.err.println();
        }
    }

    private void generateHeuristic(int endPos){
        Set<Integer> S0 = new HashSet<>(), S1 = null, considered = new HashSet<>();
        double dmax = Math.sqrt(((double)(board.nx*board.nx + board.ny*board.ny))); 
        S0.add(endPos);
        while(S0.size() > 0) {
            S1 = new HashSet<Integer>();
            Iterator<Integer> is0 = S0.iterator();
            while(is0.hasNext()) {
                int i0 = is0.next();
                int[] nni0 = getNN(i0);
                for(int nn: nni0) {
                    if(!considered.contains(nn)) {
                        int x = nn%board.nx, y = nn/board.nx;
                        x -= goal.pos%board.nx;
                        y -= goal.pos/board.nx;
                        double distGoal = Math.sqrt(x*x + y*y);
                        distGoal /= 2.0*dmax;
                        if(h[nn]==null || h[i0] == null)
                            System.err.println(nn + " " +h[nn] + " " + i0 + " " + h[i0]);
                        h[nn] = h[i0] + 0.5 + distGoal;
                        S1.add(nn);
                    }
                }
            } /* while() */
            considered.addAll(S0);
            S0 = S1;
        } /* while() */
    } /* generateHeuristic() */

    class Board {
        String board;
        int nx, ny; 

        Board(String board, int nx, int ny) {
            this.board = board;
            this.nx = nx;
            this.ny = ny;
        } /* constructor */

        public int hashCode() { return this.board.hashCode(); } /* hashCode() */
        public boolean equals(Object o) {
            return o != null && ((o instanceof Board) && ((Board) o).board.equals(this.board));
        } /* equals() */
    } /* inner class Board */

    PathState goal;
    Board board;
    int pos;

    public PathState(int start, int end, String b, int nx, int ny) {
        this(start, b, nx, ny);
        this.setGoal(end);
        this.h = new Double[nx*ny];
        for(int i=0; i<h.length; i++) h[i] = new Double(0.0);
        this.generateHeuristic(end);
        this.pH();
    } /* constructor */

    public PathState(int start, String b, int nx, int ny) {
        this.board = this.new Board(b, nx, ny); 
        this.pos = start;
    } /* constructor */
    public PathState(int pos, Board b) {this.board = b; this.pos = pos;} /* constructor */
    //  PathState(int pos, State goal) { this.goal = goal;} /* constructor */

    private bfs.State<Double> newPathState(int pos) {
        PathState s = new PathState(pos, this.board);
        s.goal = this.goal;
        s.h = this.h;
        return s;
    } /* newPathState() */

    private void setGoal(int end){
        this.goal = new PathState(end, this.board);
    } /* setGoal() */
    public boolean isGoal(){
        boolean goalflag = this.goal.equals(this); 
        return goalflag;
    }

    // TODO: dummies
    public List<bfs.State<Double>> generateSuccessors(){
        List<bfs.State<Double>> ls = new ArrayList<>();
        int[] nna = getNN(this.pos);
        
        for(int nn: nna) ls.add(newPathState(nn));
        return ls;
    }

    public Double heuristic(){
        return h[this.pos];
    } /* heuristic() */

    private int[] getNN(int pos) {
        int x = pos%this.board.nx, y = pos/this.board.nx;
        int[][] dca = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

        List<Integer> lnn = new ArrayList<>();

        for(int[] dx: dca) {
            int x1 = x + dx[0], y1 = y + dx[1];
            if(checkCoords(x1,y1)) lnn.add(x1+this.board.nx*y1);
        }

        // TODO: it is a very clumsy way of doing things
        int i = 0;
        int[] nna = new int[lnn.size()];
        Iterator<Integer> il = lnn.iterator();
        while(il.hasNext()) {nna[i] = il.next(); i++;}

        return nna;
    } /* getNN() */

    private boolean checkCoords(int x, int y){
        return (-1 < x && x < this.board.nx && -1 < y && y < this.board.ny);
    } /* checkCoords() */

    private boolean checkCoords(int pos){
        int x = pos%this.board.nx, y = pos/this.board.nx;
        return (-1 < x && x < this.board.nx && -1 < y && y < this.board.ny);
    } /* checkCoords() */


    public int hashCode() { return (this.board.hashCode()+this.pos);} /* hashCode() */

    public boolean equals(Object o){
        return ( o != null && o instanceof PathState 
                           && this.board.equals(((PathState) o).board)
                           && this.pos == ((PathState) o).pos);
    } /* equals() */
    public String toString() {return "" + this.pos;}

    public static void main(String[] args){
        int nx = Integer.parseInt(args[0]), ny = Integer.parseInt(args[1]);
        PathState p = new PathState(0, 5+6*nx, "", nx, ny); 
    }
}

/*
 *
 */
