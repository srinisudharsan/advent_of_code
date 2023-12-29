package com.srinisudharsan.aoc2023.day4.part1;

import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LineNumMatchCalulator{
    private Map<Integer, Integer> lineNoToNumMatch = new ConcurrentHashMap<Integer, Integer>();
    private Queue<Integer> copyBacklogLineNo = new ConcurrentLinkedQueue<Integer>();

    public int calculateNumMatch(String line){
        if(line == null || line.length() == 0){
            return 0;
        }
        int lineNo = Integer.parseInt(line.substring(5, line.indexOf(':')).trim());
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
        lineNoToNumMatch.put(lineNo, numMatch);
        return numMatch;
    }

    // There should be only one 
    public synchronized int computeTotalCardCount(){
        int retVal = this.lineNoToNumMatch.size();
        copyBacklogLineNo.addAll(this.lineNoToNumMatch.keySet());
        while(!copyBacklogLineNo.isEmpty()){
            int lineNo = copyBacklogLineNo.poll();
            int matches = lineNoToNumMatch.get(lineNo);
            retVal += matches;
            for(int i = 0; i< matches;i++){
                this.copyBacklogLineNo.add(lineNo+i+1);
            }
        }
        return retVal;
    }
}
