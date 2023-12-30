package com.srinisudharsan.aoc2023.day8.part2;

public class TreeNode {
    private String data;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(String data, TreeNode left, TreeNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeNode(String data){
        this.data = data;
    }

    public String getData(){
        return this.data;
    }

    public TreeNode getLeft(){
        return this.left;
    }

    public TreeNode getRight(){
        return this.right;
    }

    public void setLeft(TreeNode left){
        this.left = left;
    }

    public void setRight(TreeNode right){
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(!(obj instanceof TreeNode)){
            return false;
        }
        TreeNode other = (TreeNode) obj;
        if(this.data == null && other.data == null){
            return true;
        }
        if(this.data == null){
            return false;
        }
        if(other.data == null){
            return false;
        }
        return this.data.equals(other.data);
    }

    @Override
    public int hashCode() {
        if(this.data == null){
            return 0;
        }
        return this.data.hashCode();
    }
}
