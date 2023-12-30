package com.srinisudharsan.aoc2023.day5.part2;

public class Seed {
    private long seedStart;
    private long range;

    public Seed(long seedStart, long range) {
        this.seedStart = seedStart;
        this.range = range; 
    }

    public long getSeedStart() {
        return seedStart;
    }

    public long getSeedEnd(){
        return seedStart + range;
    }
}
