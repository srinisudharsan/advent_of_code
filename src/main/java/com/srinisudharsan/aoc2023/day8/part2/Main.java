package com.srinisudharsan.aoc2023.day8.part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("resources/input8.txt"));
        Tree tree = new Tree();
        char[] instruction = bf.readLine().trim().toCharArray();
        String line;
        while((line = bf.readLine()) != null){
            line = line.trim();
            if(line.length() == 0){
                continue;
            }
            String[] lineParts = line.split(" = ");
            if(lineParts.length != 2){
                continue;
            }
            String parentStr = lineParts[0].trim().toUpperCase();
            int comma = lineParts[1].indexOf(",");
            String leftStr = lineParts[1].substring(1, comma).trim().toUpperCase();
            String rightStr = lineParts[1].substring(comma+1, lineParts[1].length()-1).trim().toUpperCase();
            TreeNode leftNode = tree.getNode(leftStr);
            if(leftNode == null){
                leftNode = new TreeNode(leftStr, null, null);
                tree.addNode(leftNode);
            }
            TreeNode rightNode = tree.getNode(rightStr);
            if(rightNode == null){
                rightNode = new TreeNode(rightStr, null, null);
                tree.addNode(rightNode);
            }
            TreeNode parentNode = tree.getNode(parentStr);
            if(parentNode == null){
                parentNode = new TreeNode(parentStr, leftNode, rightNode);
                tree.addNode(parentNode);
            }else{
                parentNode.setLeft(leftNode);
                parentNode.setRight(rightNode);
            }
        }
        bf.close();
        Set<TreeNode> roots = tree.getRoots();

        // Find LCM of steps! wow!
        long output = roots.stream().parallel().mapToLong(root -> {
            int instructionIdx = 0;
            long steps = 0;
            while(!root.getData().endsWith("Z")){
                steps++;
                if(instructionIdx == instruction.length){
                    instructionIdx = 0;
                }
                if(instruction[instructionIdx++] == 'L'){
                    root = root.getLeft();
                }else{
                    root = root.getRight();
                }
            }
            return steps;
        }).reduce(1, (a,b) -> {
            long larger = Math.max(a, b);
            long smaller = Math.min(a, b);
            long lcm = larger;
            while(lcm % smaller != 0){
                lcm += larger;
            }
            return lcm;
        });
        System.out.println("Output: " + output);
    }
    
}
