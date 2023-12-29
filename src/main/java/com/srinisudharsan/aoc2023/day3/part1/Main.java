package com.srinisudharsan.aoc2023.day3.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("resources/input3.txt"));
        AtomicLong sum = new AtomicLong(0);
        try {
            char[] line = null;
            char[] prevLine = null;
            char[] nextLine = null;
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            String lineStr = reader.readLine();
            if(lineStr != null){
                line = lineStr.toCharArray();
            }
            String nextLineStr = reader.readLine();
            if(nextLineStr != null){
                nextLine = nextLineStr.toCharArray();
            }
            while (line != null) {
                final char[] inputLine = line;
                final char[] inputNextLine = nextLine;
                final char[] inputPrevLine = prevLine;
                executor.submit(()->{
                    long sumVal = ValidNumberFinder.findValidNumber(inputPrevLine, inputLine, inputNextLine);
                    sum.addAndGet(sumVal);
                });
                prevLine = line;
                line = nextLine;
                if(nextLine != null){
                    nextLineStr = reader.readLine();
                    if(nextLineStr != null){
                        nextLine = nextLineStr.toCharArray();
                    }else{
                        nextLine = null;
                    }
                }
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
