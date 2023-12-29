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

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("resources/input3.txt"));
        String lineStr;
        int lineNo = 0;
        try {
            // We try to submit two sets of jobs, one to preprocess each line and another to actually 
            // do the calculations
            LineMetadataProvider numIdxCalc = new LineMetadataProvider();
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
            while((lineStr = reader.readLine()) != null)
            {
                final char[] line = lineStr.toCharArray();
                final int inputLineNo = lineNo;
                completionService.submit(()->{
                    try{
                        numIdxCalc.addLine(line, inputLineNo);
                        return 0;
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                        return -1;
                    }
                });
                lineNo++;
            }

            for(int i = 0; i< lineNo;i++){
                completionService.take();
            }

            System.out.println("Pre Compute Completed");
            for(int i = 0; i< lineNo;i++){
                System.out.println("Line No" + lineNo);
                List<Integer> starIdx = numIdxCalc.getStarIdx(lineNo);
                if(starIdx != null){
                    for(int idx : starIdx){
                        System.out.println("Star Idx: " + idx);
                    }
                }else{
                    System.out.println("No Star Idx");
                }
            }

            AtomicLong result = new AtomicLong(0);
            for(int i = 0; i< lineNo; i++){
                final int lineIdx = i;
                executor.submit(() ->
                {
                    result.addAndGet(GearRatioCalculator.calcGearRatio(lineIdx, numIdxCalc));
                });
            }
            
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            System.out.println("Result: " + result);
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }
}
