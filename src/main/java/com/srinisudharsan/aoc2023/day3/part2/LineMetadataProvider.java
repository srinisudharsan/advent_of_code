package com.srinisudharsan.aoc2023.day3.part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineMetadataProvider {
    private Map<LineIdx, Integer> lineIdxToNum = new HashMap<LineIdx, Integer>();
    private Map<Integer, List<Integer>> starIxForLine = new HashMap<Integer, List<Integer>>();

    public List<Integer> getStarIdx(int lineNo){
        return this.starIxForLine.get(lineNo);
    }

    // given a line no and idx gives a num if the num is running through this idx. Not just start or end
    public Integer getNumForLineIdx(int lineNo, int idx){
        return lineIdxToNum.get(new LineIdx(lineNo, idx));
    }

    public void addLine(char[] line, int lineNo){
        List<Integer> starIdx = new ArrayList<Integer>();
        for(int idx = 0;idx < line.length; idx ++){
            if(line[idx] == '*'){
                starIdx.add(idx);
            }else if(Character.isDigit(line[idx])){
                int numStartIdx = idx;
                int currentNum = 0;
                for(;idx < line.length && Character.isDigit(line[idx]);idx++)
                {
                    currentNum = currentNum * 10 + line[idx] - '0';
                }
                for(int i = numStartIdx; i < idx; i++){
                    this.lineIdxToNum.put(new LineIdx(lineNo, i), currentNum);
                }
            }
        }
        this.starIxForLine.put(lineNo, starIdx);
    }

    private class LineIdx{
        private int lineNum;
        private int idx;
        public LineIdx(int lineNum, int idx){
            this.lineNum = lineNum;
            this.idx = idx;
        }

        @Override
        public boolean equals(Object other){
            if(other == null){
                return false;
            }
            if(other instanceof LineIdx){
                LineIdx otherLineIdx = (LineIdx)other;
                return otherLineIdx.lineNum == this.lineNum && otherLineIdx.idx == this.idx;
            }
            return false;
        }
    }
}

