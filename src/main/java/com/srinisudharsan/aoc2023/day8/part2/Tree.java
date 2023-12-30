package com.srinisudharsan.aoc2023.day8.part2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tree {
    private Map<String, TreeNode> nodes = new HashMap<String, TreeNode>();
    private Set<TreeNode> roots = new HashSet<TreeNode>();

    public Set<TreeNode> getRoots(){
        return roots;
    }

    public void addNode(TreeNode node){
        if(node.getData().endsWith("A")){
            this.roots.add(node);
        }
        this.nodes.put(node.getData(), node);
    }

    public TreeNode getNode(String data){
        return this.nodes.get(data);
    }
}
