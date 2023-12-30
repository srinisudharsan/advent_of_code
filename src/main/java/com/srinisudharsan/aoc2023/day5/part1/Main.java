package com.srinisudharsan.aoc2023.day5.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("resources/input5.txt"));
        String line;
        String seeds = null;
        List<String> seedToSoilMapping = null, soilToFertilzerMap = null;
        List<String> fertilizerToWaterMap = null, waterToLightMap = null;
        List<String> lightToTemperaturemap = null, temperatureToHumidityMap = null, humidityToLocationMap = null;
        while((line = reader.readLine()) != null){
            int idx;
            if((idx = line.indexOf("seeds:")) != -1){
                seeds = line.substring(idx+6).trim();
            }
            else{
                switch(line){
                    case "seed-to-soil map:":
                        seedToSoilMapping = new ArrayList<String>();
                        seedToSoilMapping = buildMappingList(reader);
                        break;
                    case "soil-to-fertilizer map:":
                        soilToFertilzerMap = new ArrayList<String>();
                        soilToFertilzerMap = buildMappingList(reader);
                        break;
                    case "fertilizer-to-water map:":
                        fertilizerToWaterMap = new ArrayList<String>();
                        fertilizerToWaterMap = buildMappingList(reader);
                        break;
                    case "water-to-light map:":
                        waterToLightMap = new ArrayList<String>();
                        waterToLightMap = buildMappingList(reader);
                        break;
                    case "light-to-temperature map:":
                        lightToTemperaturemap = new ArrayList<String>();
                        lightToTemperaturemap = buildMappingList(reader);
                        break;
                    case "temperature-to-humidity map:":
                        temperatureToHumidityMap = new ArrayList<String>();
                        temperatureToHumidityMap = buildMappingList(reader);
                        break;
                    case "humidity-to-location map:":
                        humidityToLocationMap = new ArrayList<String>();
                        humidityToLocationMap = buildMappingList(reader);
                        break;
                }
            }
        }
        Almanac almanac = new Almanac(seeds, seedToSoilMapping, soilToFertilzerMap, fertilizerToWaterMap,
        waterToLightMap, lightToTemperaturemap, temperatureToHumidityMap, humidityToLocationMap);
        almanac.printAlmanac();
        almanac.getSeeds().stream().map((seed) -> {
            long location = almanac.seedToLocation(seed);
            System.out.println("Seed: " + seed + " Location: " + location);
            return location;
        }).min((loc1, loc2) -> {
            return loc1 < loc2 ? -1 : (loc1 > loc2 ? 1 : 0);
        }).ifPresent((loc) -> {
            System.out.println("Output: " + loc);
        });
    }

    private static List<String> buildMappingList(BufferedReader reader){
        List<String> mappingList = new ArrayList<String>();
        String line;
        try{
            while((line = reader.readLine()) != null && line.trim().length() > 0){
                mappingList.add(line.trim());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return mappingList;
    }
}
