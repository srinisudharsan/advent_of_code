package com.srinisudharsan.aoc2023.day5.part1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Almanac {
    Comparator<Mapping> mappingComparator = new Comparator<Mapping>() {
        @Override
        public int compare(Mapping m1, Mapping m2) {
            if(m1.getSource() < m2.getSource()){
                return -1;
            } else if(m1.getSource() > m2.getSource()){
                return 1;
            } else {
                return 0;
            }
        }
    };
    private Set<Long> seeds = new HashSet<Long>();
    private List<Mapping> seedToSoilMapping = new ArrayList<Mapping>();
    private List<Mapping> soilToFertilzerMap = new ArrayList<Mapping>();
    private List<Mapping> fertilizerToWaterMap = new ArrayList<Mapping>();
    private List<Mapping> waterToLightMap = new ArrayList<Mapping>();
    private List<Mapping> lightToTemperaturemap = new ArrayList<Mapping>();
    private List<Mapping> temperatureToHumidityMap = new ArrayList<Mapping>();
    private List<Mapping> humidityToLocationMap = new ArrayList<Mapping>();
    private List<List<Mapping>> mappings = new ArrayList<List<Mapping>>();


    public Almanac(String seeds, List<String> seedToSoilMapping, List<String> soilToFertilzerMap,
    List<String> fertilizerToWaterMap, List<String> waterToLightMap, List<String> lightToTemperaturemap,
    List<String> temperatureToHumidityMap, List<String> humidityToLocationMap) {
        this.seeds = parseSeeds(seeds);
        this.seedToSoilMapping = parseMapping(seedToSoilMapping);
        this.soilToFertilzerMap = parseMapping(soilToFertilzerMap);
        this.fertilizerToWaterMap = parseMapping(fertilizerToWaterMap);
        this.waterToLightMap = parseMapping(waterToLightMap);
        this.lightToTemperaturemap = parseMapping(lightToTemperaturemap);
        this.temperatureToHumidityMap = parseMapping(temperatureToHumidityMap);
        this.humidityToLocationMap = parseMapping(humidityToLocationMap);
        this.mappings.add(this.seedToSoilMapping);
        this.mappings.add(this.soilToFertilzerMap);
        this.mappings.add(this.fertilizerToWaterMap);
        this.mappings.add(this.waterToLightMap);
        this.mappings.add(this.lightToTemperaturemap);
        this.mappings.add(this.temperatureToHumidityMap);
        this.mappings.add(this.humidityToLocationMap);
    }

    public Set<Long> getSeeds(){
        return this.seeds;
    }

    public long seedToLocation(long seed){
        if(!this.seeds.contains(seed)){
            return -1;
        }
        long lookup = seed;
        for (List<Mapping> mapping: this.mappings) {
            long input = lookup;
            lookup = getMapping(lookup, mapping);
        }
        return lookup;
    }

    public void printAlmanac(){
        System.out.println("Seeds: " + this.seeds);
        System.out.println("SeedToSoilMapping: ");
        printMapping(this.seedToSoilMapping);
        System.out.println("SoilToFertilzerMap: ");
        printMapping(this.soilToFertilzerMap);
        System.out.println("FertilizerToWaterMap: ");
        printMapping(this.fertilizerToWaterMap);
        System.out.println("WaterToLightMap: ");
        printMapping(this.waterToLightMap);
        System.out.println("LightToTemperaturemap: ");
        printMapping(this.lightToTemperaturemap);
        System.out.println("TemperatureToHumidityMap: ");
        printMapping(this.temperatureToHumidityMap);
        System.out.println("HumidityToLocationMap: ");
        printMapping(this.humidityToLocationMap);
    }

    public void printMapping(List<Mapping> mapping){
        for(Mapping m : mapping){
            System.out.println(m.getSource() + " " + m.getDestination() + " " + m.getRange());
        }
    }

    private long getMapping(long val, List<Mapping> mappings){
        Mapping m = findMappingForSourceRange(val, mappings, 0, mappings.size()-1);
        if(m == null){
            return val;
        }
        return m.getDestination()+val-m.getSource();
    }

    private Mapping findMappingForSourceRange(long val, List<Mapping> mappings, int start, int end){
        while (start <= end) {
            int mid = (start + end) / 2;
            Mapping m = mappings.get(mid);
            if (val >= m.getSource() && val < m.getSource() + m.getRange()) {
                return m;
            } else if (m.getSource() < val) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return null;
    }

    private List<Mapping> parseMapping(List<String> mappingStrs){
        List<Mapping> mappings = new ArrayList<Mapping>();
        for(String mappingStr : mappingStrs){
            String[] mappingStrArr = mappingStr.split(" ");
            if(mappingStrArr.length != 3){
                continue;
            }
            mappings.add(new Mapping(Long.parseLong(mappingStrArr[0]), Long.parseLong(mappingStrArr[1]), Long.parseLong(mappingStrArr[2])));
        }
        mappings.sort(mappingComparator);
        return mappings;
    }

    private Set<Long> parseSeeds(String seeds){
        Set<Long> seedSet = new HashSet<Long>();
        String[] seedStrs = seeds.split(" ");
        for(String seedStr : seedStrs){
            seedSet.add(Long.parseLong(seedStr));
        }
        return seedSet;
    }
}


