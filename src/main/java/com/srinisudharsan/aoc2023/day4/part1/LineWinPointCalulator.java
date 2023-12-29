package com.srinisudharsan.aoc2023.day4.part1;

public class LineWinPointCalulator{

    public static int calculateWinPoints(String line){
        if(line == null || line.length() == 0){
            return 0;
        }
        line = line.substring(line.indexOf(':'), 0);
        if(line == null || line.length() == 0){
            return 0;
        }
        String[] setStrs = line.split(" | ");
        if(setStrs.length != 2){
            return 0;
        }
        
        // Other approaches include sort and compare and using a HashSet. Space complexity of bool array is O(1)
        // although we can discuss realistically what it would be
        String[] set1 = setStrs[0].split(" ");
        String[] set2 = setStrs[0].split(" ");
        boolean[] set1bool = new boolean[Integer.MAX_VALUE];
        boolean[] set2bool = new boolean[Integer.MAX_VALUE];
        for(String val: set1){
            set1bool[Integer.parseInt(val)] = true;
        }
        for(String val: set2){
            set2bool[Integer.parseInt(val)] = true;
        }
        int numMatch = 0;
        for(int i = 0; i < Integer.MAX_VALUE; i++){
            if (set1bool[i] && set2bool[i]){
                numMatch++;
            }
        }
        return numMatch == 0 ? 0 : (int)Math.pow(2, numMatch);
    }
}
