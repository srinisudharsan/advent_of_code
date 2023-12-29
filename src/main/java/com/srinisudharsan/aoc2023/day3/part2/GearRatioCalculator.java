package com.srinisudharsan.aoc2023.day3.part2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GearRatioCalculator{

    public static long calcGearRatio(int lineNo, LineMetadataProvider metadataProvider){
        long retVal = 0;
        List<Integer> starIdxList = metadataProvider.getStarIdx(lineNo);
        if(starIdxList == null || starIdxList.size() == 0){
            return 0;
        }

        for(int starIdx : starIdxList){
            int numAdjacent = 0;
            Set<NumDetails> adjacent = new HashSet<NumDetails>();
            for(int rowIdx = lineNo - 1; rowIdx <= lineNo + 1 && numAdjacent < 3;rowIdx++)
            {
                for(int colIdx = starIdx - 1; colIdx <= starIdx + 1 && numAdjacent < 3;colIdx++){
                    NumDetails val = metadataProvider.getNumDetailsForLineIdx(rowIdx, colIdx);
                    if(val != null && !adjacent.contains(val)){
                        adjacent.add(val);
                        numAdjacent++;
                    }
                }
            }
            if(numAdjacent == 2){
                long multi = 1;
                for(NumDetails numDetails : adjacent){
                    multi *= numDetails.getNum();
                }
                retVal += multi;
            }
        }
        System.out.println("Line No: " + lineNo + " Gear Ratio: " + retVal);
        return retVal;
    }
}
