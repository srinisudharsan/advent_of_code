package com.srinisudharsan.aoc2023.day3.part2;

public class NumDetails {
    private int num;
    private int startIdx;
    private int endIdx;

    public NumDetails(int num, int startIdx, int endIdx){
        this.num = num;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    public int getNum(){
        return this.num;
    }

    public int getStartIdx(){
        return this.startIdx;
    }

    public int getEndIdx(){
        return this.endIdx;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + endIdx;
        result = prime * result + num;
        result = prime * result + startIdx;
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        NumDetails other = (NumDetails) obj;
        if(endIdx != other.endIdx){
            return false;
        }
        if(num != other.num){
            return false;
        }
        if(startIdx != other.startIdx){
            return false;
        }
        return true;
    }
    
}
