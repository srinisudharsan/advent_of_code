package com.srinisudharsan.aoc2023.day5.part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.LongStream;

import com.srinisudharsan.scatterGather.ExecutorScatterGatherBuilder;
import com.srinisudharsan.scatterGather.ScatterGather;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
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
        ExecutorScatterGatherBuilder<Seed, Long, Long> sgBuilder= new ExecutorScatterGatherBuilder<Seed, Long, Long>(
            (seed)->{
                // make this a stream and do parallel processing
                long min = LongStream.range(seed.getSeedStart(), seed.getSeedEnd()) // Creates a stream of long values from seedStart (inclusive) to seedEnd (exclusive)
                .parallel() // Enables parallel processing
                .mapToObj(seedNum -> {
                    long location = almanac.seedToLocation(seedNum); // Converts each seed number to a location
                    return location;
                })
                .min(Long::compare) // Finds the minimum location
                .orElse(-1L);
                return min;
            },
            (locations)->{
                long min = -1L;
                for(long location : locations){
                    if(min == -1){
                        min = location;
                    }
                    else if(location < min){
                        min = location;
                    }
                }
                return min;
            }
        );
            

        ScatterGather<Seed, Long, Long> sg = sgBuilder.build();
        for(Seed seed: almanac.getSeeds()){
            sg.scatter(seed);
        }
        System.out.println("Output: " + sg.gather());
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
