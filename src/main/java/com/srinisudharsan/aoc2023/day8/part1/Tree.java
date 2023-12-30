package com.srinisudharsan.aoc2023.day8.part1;

import java.util.HashMap;
import java.util.Map;

public class Tree {
    private Map<String, TreeNode> nodes = new HashMap<String, TreeNode>();

    public TreeNode getRoot(){
        return this.nodes.get("AAA");
    }

    public void addNode(TreeNode node){
        this.nodes.put(node.getData(), node);
    }

    public TreeNode getNode(String data){
        return this.nodes.get(data);
    }
}
