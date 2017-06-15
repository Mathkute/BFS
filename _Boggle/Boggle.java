/*
 *
 */

import bfs.BFS;
import bfs.BFS.Node;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Boggle {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String letters = in.nextLine();
        Set<String> trie = new HashSet<>();
        while(in.hasNext()) {
            String s = in.nextLine();
            for(int i=0; i<s.length()+1; i++) trie.add(s.substring(0,i));
            trie.add(s+'<');
        }
        Model mdl = new Model(letters, trie);
        BoggleNode n = new BoggleNode(mdl);

        BFS bfs = new BFS(n);
        bfs.search();
        Iterator<String> iw = mdl.getWords().iterator();
        while(iw.hasNext()) System.out.println(iw.next());

    }

}

/*
 *
 */
