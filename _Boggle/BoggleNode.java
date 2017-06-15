/*
 *
 */

import bfs.BFS.Node;
import java.util.Collection;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class BoggleNode implements Node {
    int[] check;
    String word = "";
    Model mdl;

    BoggleNode(Model mdl){
        this.mdl = mdl; 
        check = new int[mdl.getCharacters().length()];
    }

    public int compareTo(Node n){
        BoggleNode n1 = (BoggleNode) n;
        int l1 = this.word.length(), l2 = n1.word.length();
        return (l1<l2?-1:l1==l2?0:1);
    }
    public boolean equals(Object o){
        if(o instanceof BoggleNode) {
            BoggleNode n = (BoggleNode) o;
            if(this.check.length != n.check.length) return false;

            for(int i=0; i<check.length; i++)
                if(this.check[i] != n.check[i]) return false;
            if(!this.word.equals(n.word)) return false;
        }
        return true;
    }
    public int hashCode(){return word.hashCode();}

    Node newNode(String s, int[] check){
        BoggleNode n = new BoggleNode(mdl);
        n.word = s;
        n.check = check;
        return n;
    }

    public boolean isGoal(){return false;}
    public Collection<Node> generateSuccessors(){
        Collection<Node> successors = new ArrayList<>();
        String letters = mdl.getCharacters();
        for(int i=0; i<check.length; i++) {
            if(check[i] == 0) {
                String w = word + letters.charAt(i);
                if(mdl.checkSequence(w)) {
                    mdl.checkWord(w);
                    int[] ack = new int[this.check.length];
                    for(int j=0; j<ack.length; j++) ack[j] = check[j];
                    ack[i] = 1;
                    successors.add(newNode(w,ack));
                }
            }
        }
        return successors;
    }
}

class Model {
    List<String> words = new ArrayList<>();
    Set<String> trie;
    String letters;

    Model(String letters, Set<String> trie){
        this.letters = letters;
        this.trie = trie;
    }

    private void addWord(String s){words.add(s);}
    void checkWord(String s){ if(checkSequence(s+'<')) addWord(s);}
    boolean checkSequence(String s){ return trie.contains(s);}
    String getCharacters(){return letters;}
    List<String> getWords(){return words;}
}

/*
 *
 */
