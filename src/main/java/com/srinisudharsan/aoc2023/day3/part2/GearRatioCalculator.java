package com.srinisudharsan.aoc2023.day3.part2;

import java.util.List;

public class GearRatioCalculator{

    public static long calcGearRatio(int lineNo, LineMetadataProvider metadataProvider){
        long retVal = 0;
        List<Integer> starIdxList = metadataProvider.getStarIdx(lineNo);
        if(starIdxList == null || starIdxList.size() == 0){
            return 0;
        }

        for(int starIdx : starIdxList){
            int numAdjacent = 0;
            int[] adjacent = new int[2];
            for(int rowIdx = lineNo - 1; rowIdx <= lineNo + 1 && numAdjacent < 2;rowIdx++)
            {
                for(int colIdx = starIdx - 1; colIdx <= starIdx + 1 && numAdjacent < 2;colIdx++){
                    Integer val = metadataProvider.getNumForLineIdx(rowIdx, colIdx);
                    if(val != null){
                        adjacent[numAdjacent++] = val;
                    }
                }
            }
            if(numAdjacent == 1){
                retVal += adjacent[0] * adjacent[1];
            }
        }
        return retVal;
    }
}
