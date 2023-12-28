package com.srinisudharsan.aoc2023.day2.part1;

public class GameRule {
    private int maxRed;
    private int maxGreen;
    private int maxBlue;
    public GameRule(int maxRed, int maxGreen, int maxBlue){
        this.maxRed = maxRed;
        this.maxGreen = maxGreen;
        this.maxBlue = maxBlue;
    }

    public int getMaxRed() {
        return maxRed;
    }

    public int getMaxGreen() {
        return maxGreen;
    }

    public int getMaxBlue() {
        return maxBlue;
    }
}
