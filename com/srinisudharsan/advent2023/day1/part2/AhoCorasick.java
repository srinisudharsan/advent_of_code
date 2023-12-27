import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AhoCorasick{
    private static final int ALPHABET_SIZE = 26;
    private static class TrieNode{
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        TrieNode failureLink = null;
        int firstOutput;
        char c;
        int numChild = 0;

        public TrieNode(char c){
            for(int i = 0; i < ALPHABET_SIZE; i++){
                this.children[i] = null;
            }
            this.firstOutput = -1;
            this.c = c;
        }
    }
    private TrieNode root;
    private String[] substrings;
    public AhoCorasick(String[] strings){
        this.substrings = strings;
        this.root = new TrieNode('*');
        buildTrie(strings);
        buildFailureLinks();
        calculateNumChildren();
        printTrie();
    }

    private void printTrie(){
        Queue<TrieNode> queue = new LinkedList<TrieNode>();
        queue.add(this.root);
        while(!queue.isEmpty()){
            TrieNode current = queue.remove();
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                TrieNode child = current.children[i];
                if (child != null){
                    queue.add(child);
                }
            }
        }
    }  

    public AhoCorasickOutput findFirstAndLastSubstrings(String mainString){
        if (mainString == null || mainString.length() == 0){
            return null;
        }

        String firstString = null;
        String lastString = null;
        int firstStringIndex = -1;
        int lastStringIndex = -1;
        char[] mainCharArr = mainString.toLowerCase().toCharArray();
        TrieNode node = this.root;
        for(int i = 0;i < mainCharArr.length;i++){
            int index = mainCharArr[i] - 'a';
            if(index < 0 || index >= ALPHABET_SIZE){
                node = root;
                continue;
            }

            while (node != root && node.children[index] == null) {
                node = node.failureLink;
            }

            node = (node.children[index] != null) ? node.children[index] : root;
            if(node.firstOutput != -1){
                if(firstString == null){
                    firstString = substrings[node.firstOutput];
                    firstStringIndex = i - firstString.length() + 1;
                    lastString = substrings[node.firstOutput];
                    lastStringIndex = i - lastString.length() + 1;
                }else{
                    lastString = substrings[node.firstOutput];
                    lastStringIndex = i - lastString.length() + 1;
                }
            }
            
        }
        return new AhoCorasickOutput(firstString, firstStringIndex, lastString, lastStringIndex);
    }

    private void buildTrie(String[] keywords) {
        for (int i = 0; i < keywords.length; i++) {
            TrieNode node = root;
            for (char c : keywords[i].toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode(c);
                }
                node = node.children[index];
            }
            if(node.firstOutput == -1){
                node.firstOutput = i; // Index of the keyword in the array
            }
        }
    }

    private void buildFailureLinks() {
        Queue<TrieNode> queue = new LinkedList<>();
        this.root.failureLink = this.root;
    
        // Initially, set failure links of children of the root to the root itself
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (this.root.children[i] != null) {
                this.root.children[i].failureLink = this.root;
                queue.add(this.root.children[i]);
            }
        }
    
        while (!queue.isEmpty()) {
            TrieNode current = queue.remove();
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                TrieNode child = current.children[i];
                if (child != null) {
                    queue.add(child);
    
                    TrieNode fallback = current.failureLink;
                    while (fallback != this.root && fallback.children[i] == null) {
                        fallback = fallback.failureLink;
                    }
    
                    if (fallback.children[i] != null && fallback.children[i] != child) {
                        child.failureLink = fallback.children[i];
                    }

                    if(child.failureLink == null){
                        child.failureLink = this.root;
                    }
    
                    if (child.firstOutput == -1) {
                        child.firstOutput = child.failureLink.firstOutput;
                    }
                }
            }
        }
    }
    
    
    

    private void calculateNumChildren(){
        Queue<TrieNode> queue = new LinkedList<TrieNode>();
        queue.add(this.root);
        while(!queue.isEmpty()){
            TrieNode current = queue.remove();
            int numChild = 0;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                TrieNode child = current.children[i];
                if (child != null){
                    numChild++;
                    queue.add(child);
                }
            }
            current.numChild = numChild;
        }
    }

}