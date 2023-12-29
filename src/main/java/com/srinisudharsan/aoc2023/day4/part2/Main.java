package com.srinisudharsan.aoc2023.day4.part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.srinisudharsan.aoc2023.day4.part1.LineNumMatchCalulator;
import com.srinisudharsan.scatterGather.ExecutorScatterGatherBuilder;
import com.srinisudharsan.scatterGather.ScatterGather;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("resources/input4.txt"));
        String lineStr;
        try {
            // We try to submit two sets of jobs, one to preprocess each line and another to actually 
            // do the calculations
            LineNumMatchCalulator lineNumMatchCalulator = new LineNumMatchCalulator();
            ExecutorScatterGatherBuilder<String, Integer, Integer> scatterGatherBuilder = new ExecutorScatterGatherBuilder<String, Integer, Integer>(
                (line)->{
                    try{
                        return lineNumMatchCalulator.calculateNumMatch(line);
                    }catch(Exception e){
                        return null;
                    }
                },
                (numMatches) -> {
                    if(numMatches == null || numMatches.size() == 0){
                        return (int)0;
                    }
                    return lineNumMatchCalulator.computeTotalCardCount();
                }
            );
            ScatterGather<String, Integer, Integer> scatterGather = scatterGatherBuilder.build();
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
