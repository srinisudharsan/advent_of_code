package com.srinisudharsan.aoc2023.day4.part1;

import java.util.HashSet;
import java.util.Set;

public class LineWinPointCalulator{

    public static int calculateWinPoints(String line){
        if(line == null || line.length() == 0){
            return 0;
        }
        line = line.substring(line.indexOf(':')+1);
        if(line == null || line.length() == 0){
            return 0;
        }
        String[] setStrs = line.split(" \\| ");
        if(setStrs.length != 2){
            return 0;
        }
        
        String[] arr1 = setStrs[0].trim().split(" ");
        String[] arr2 = setStrs[1].trim().split(" ");
        Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new HashSet<String>();
        for(String str : arr1){
            if(str == null || str.trim().length() == 0){
                continue;
            }
            set1.add(str.trim());
        }
        for(String str : arr2){
            if(str == null || str.trim().length() == 0){
                continue;
            }
            set2.add(str.trim());
        }
        int numMatch = 0;
        for(String str : set1){
            if(set2.contains(str)){
                numMatch++;
            }
        }
        return numMatch == 0 ? 0 : (int)Math.pow(2, numMatch-1);
    }
}
