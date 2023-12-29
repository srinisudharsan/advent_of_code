package com.srinisudharsan.aoc2023.day3.part1;

public class ValidNumberFinder{
    public static long findValidNumber(char[] prevLine, char[] line, char[] nextLine){
        int numStartIdx = -1;
        int currentNum = 0;
        long retVal = 0;
        boolean addNumToSum = false;
        for(int idx = 0;idx < line.length; idx ++){
            for(;idx < line.length && !Character.isDigit(line[idx]);idx++);
            numStartIdx = idx;
            addNumToSum = false;
            for(;idx < line.length && Character.isDigit(line[idx]);idx++)
            {
                currentNum = currentNum * 10 + line[idx] - '0';
                // Check prev line, deal with diagonals later
                if(prevLine != null && prevLine.length > 0){
                    if(idx < prevLine.length && !Character.isDigit(prevLine[idx]) && prevLine[idx] != '.'){
                        addNumToSum = true;
                    }
                }
                // Check bottom, deal with diagonals later
                if(nextLine != null && nextLine.length > 0){
                    if(idx < nextLine.length && !Character.isDigit(nextLine[idx]) && nextLine[idx] != '.'){
                        addNumToSum = true;
                    }
                }
            }

            // This can be done with a large ||, but just to improve readability
            if(!addNumToSum){
                // Check left, top left and bottom left
                if(numStartIdx - 1 >= 0){
                    int idxToCheck = numStartIdx - 1;
                    addNumToSum = line[idxToCheck] != '.';
                    addNumToSum = addNumToSum || (prevLine != null && prevLine.length > 0 && !Character.isDigit(prevLine[idxToCheck]) && prevLine[idxToCheck] != '.');
                    addNumToSum = addNumToSum || (nextLine != null && nextLine.length > 0 && !Character.isDigit(nextLine[idxToCheck]) && nextLine[idxToCheck] != '.');
                }
                if(!addNumToSum){
                    int idxToCheck = idx + 1;
                    // Check right, top right and bottom right
                    addNumToSum = (idxToCheck < line.length && line[idxToCheck] != '.') ;
                    addNumToSum = addNumToSum || (prevLine != null && idxToCheck < prevLine.length && !Character.isDigit(prevLine[idxToCheck]) && prevLine[idxToCheck] != '.');
                    addNumToSum = addNumToSum || (nextLine != null && idxToCheck < nextLine.length && !Character.isDigit(nextLine[idxToCheck]) && nextLine[idxToCheck] != '.');
                }
            }
            System.out.println(
                "PrevLine: " + prevLine != null ? String.valueOf(prevLine) : "Null" +
                ", Line: " + line  != null ? String.valueOf(line) : "Null" +
                ", NextLine: " + nextLine != null ? String.valueOf(nextLine) : " Null" +
                ", Num: " + currentNum + ", Add: " + addNumToSum);
            if(addNumToSum){
                retVal += currentNum;
            }
        }
        return retVal;
    }
}
