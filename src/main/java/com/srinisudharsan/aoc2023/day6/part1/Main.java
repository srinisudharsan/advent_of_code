package com.srinisudharsan.aoc2023.day6.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/input6.txt"));
        String[] times = bufferedReader.readLine().substring(5).trim().split(" +");
        String[] distances = bufferedReader.readLine().substring(9).trim().split(" +");
        bufferedReader.close();
        int retVal = IntStream.range(0, times.length)
        .map((idx) -> {
            int time = Integer.parseInt(times[idx]);
            int distance = Integer.parseInt(distances[idx]);
            int start = 1, end = time-1;
            int minTime = 1;
            int maxTime = time - 1;
            // min time
            while(start < end){
                int mid = (start+end)/2;
                int midMinusOneDistance = (mid - 1) * (time - (mid - 1));
                int midDistance = mid * (time - mid);
                if((midDistance > distance) && (midMinusOneDistance  <= distance )){
                    minTime = mid;
                    break;
                }
                if(midDistance > distance){
                    end = mid;
                }else{
                    start = mid;
                }
            }
            start = 1;
            end = time-1;
            // max time
            while(start < end){
                int mid = (start+end)/2;
                int midPlusOneDistance = (mid + 1) * (time - (mid + 1));
                int midDistance = mid * (time-mid);
                if((midDistance > distance) && (midPlusOneDistance <= distance )){
                    maxTime = mid;
                    break;
                }
                if(midDistance > distance){
                    start = mid;
                }else{
                    end = mid;
                }
            }
            int numWays = maxTime - minTime + 1;
            System.out.println("Time: "+time + ", Distance: " + distance + ", NumWays: "+ numWays + ", MinTime: " + minTime + ", maxTime: " + maxTime);
            return numWays;
        }).filter(num -> num != 0).reduce(1,(a,b) -> a*b);
        System.out.println("Output" + retVal);
    }
    
}
