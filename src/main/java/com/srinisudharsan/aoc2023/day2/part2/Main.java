package com.srinisudharsan.aoc2023.day2.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("resources/input2.txt"));
        AtomicLong sum = new AtomicLong(0);
        try {
            String line;
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            GameRule gameRule = new GameRule(12, 13, 14);
            while ((line = reader.readLine()) != null) {
                final String inputLine = line;
                executor.submit(()->{
                    System.out.println("Line: " + inputLine);
                    int sumVal = MinCubeProductCalculator.computeMinCubeProduct(inputLine, gameRule);
                    sum.addAndGet(sumVal);
                    System.out.println("Val: " + sumVal);
                    System.out.println("Sum: " + sum);
                });
            }
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            System.out.println("Result: " + sum);
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }
}
