/*
 *
 */

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if(args.length!=6) {
            System.err.println("Main (PathState test): error: it takes six parameters." +
                               "\nUsage: java Main <nx> <ny> <x0> <y0> <x1> <y1>");
            System.exit(-1);
        }
        int nx = Integer.parseInt(args[0]), ny = Integer.parseInt(args[1]);
        int x0 = Integer.parseInt(args[2]), y0 = Integer.parseInt(args[3]);
        int x1 = Integer.parseInt(args[4]), y1 = Integer.parseInt(args[5]);
        int pos0 = x0 + y0*nx, pos1 = x1 + y1*nx;
        String b = "";
        for(int j=0; j<ny; j++)
            for(int i=0; i<nx; i++)
                b += " ";
        bfs.Node<Double> root = new NodeImpl(new PathState(pos0, pos1, b, nx, ny)); 
        bfs.BFS<Double> sc = new bfs.BFS<Double>(root);
        bfs.Node<Double> end = sc.search();
        if(end == null) System.err.println("Error: end null");
        List<Integer> lp = new ArrayList<>();
        while(end != null) {lp.add(((PathState) end.getState()).pos); end = end.getParent();}
        Iterator<Integer> ilp = lp.iterator();
        while(ilp.hasNext()) System.err.print(ilp.next() + " ");
        System.err.println();
        pPath(nx, ny, lp);
    } /* main() */

    static void pPath(int nx, int ny, List<Integer> lpath){
        char[] board = new char[nx*ny];
        for(int i=0; i<nx*ny; i++) board[i] = ' ';
        Iterator<Integer> ilp = lpath.iterator();
        while(ilp.hasNext()) board[ilp.next()] = '*';
        String bs = new String(board);
        for(int i=0; i<ny; i++) System.out.println(">"+bs.substring(i*nx,(i+1)*nx)+"<");
    } /* pPath() */
} /* class Main */

/*
 *
 */
