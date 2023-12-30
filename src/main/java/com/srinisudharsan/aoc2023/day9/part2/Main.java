package com.srinisudharsan.aoc2023.day9.part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.srinisudharsan.scatterGather.ExecutorScatterGatherBuilder;
import com.srinisudharsan.scatterGather.ScatterGather;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException{
        BufferedReader bf = new BufferedReader(new FileReader("resources/input9.txt"));

        ExecutorScatterGatherBuilder<String, Long, Long> builder = new ExecutorScatterGatherBuilder<String, Long, Long>(
            (line) -> {
                if(line == null || line.trim().length() == 0){
                    return null;
                }
                String[] lineParts = line.split(" +");
                List<Long> series = new ArrayList<Long>();
                for(String part : lineParts){
                    series.add(Long.parseLong(part));
                }
                Deque<Long> stack = new ArrayDeque<Long>();
                while(!series.stream().allMatch(val -> val == 0)){
                    stack.push(series.get(0));
                    List<Long> nextSeries = new ArrayList<Long>();
                    for(int i = 0; i < series.size() - 1; i++){
                        nextSeries.add(series.get(i+1) - series.get(i));
                    }
                    series = nextSeries;
                }
                long numToSub = 0;
                while(!stack.isEmpty()){
                    long val = stack.pop();
                    numToSub = val - numToSub;
                }
                return numToSub;
            },
            (vals) -> vals.stream().mapToLong(val -> val).sum()
        );
        
        ScatterGather<String, Long, Long> scatterGather = builder.build();
        String line;
        while((line = bf.readLine()) != null){
            scatterGather.scatter(line);
        }

        System.out.println(scatterGather.gather());

    }
}
