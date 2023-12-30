package com.srinisudharsan.aoc2023.day5.part1;

public class Mapping {
    private long destination;
    private long source;
    private long range;

    public Mapping(long destination, long source, long range) {
        this.destination = destination;
        this.source = source;
        this.range = range;
    }

    public long getDestination() {
        return destination;
    }

    public long getSource() {
        return source;
    }

    public long getRange() {
        return range;
    }
}
