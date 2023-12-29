package com.srinisudharsan.aoc2023.day3.part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.srinisudharsan.scatterGather.ExecutorScatterGatherBuilder;
import com.srinisudharsan.scatterGather.ScatterGather;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("resources/input3.txt"));
        String lineStr;
        int lineNo = 0;
        try {
            // We try to submit two sets of jobs, one to preprocess each line and another to actually 
            // do the calculations
            ExecutorScatterGatherBuilder<String, Integer, Long> scatterGatherBuilder = new ExecutorScatterGatherBuilder<String, Integer, Long>(
                (line)->{
                    try{
                        return LineWinPointCalulator.calculateWinPoints(line);
                    }catch(Exception e){
                        return null;
                    }
                },
                (winPoints) -> {
                    if(winPoints == null || winPoints.size() == 0){
                        return (long)0;
                    }
                    long totalPoints = 0;
                    for(Integer point : winPoints){
                        if(point != null){
                            totalPoints += point;
                        }
                    }
                    return totalPoints;
                }
            );
            ScatterGather<String, Integer, Long> scatterGather = scatterGatherBuilder.build();
            while((lineStr = reader.readLine()) != null)
            {
                scatterGather.scatter(lineStr);
            }

            System.out.println("Ouput: " + scatterGather.gather());
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }
}
