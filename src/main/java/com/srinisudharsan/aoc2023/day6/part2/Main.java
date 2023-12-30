package com.srinisudharsan.aoc2023.day6.part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/input6.txt"));
        long time = Long.parseLong(bufferedReader.readLine().substring(5).trim().replaceAll(" ", ""));
        long distance = Long.parseLong(bufferedReader.readLine().substring(9).trim().replaceAll(" ", ""));
        bufferedReader.close();
            
        long start = 1, end = time-1;
        long minTime = 1;
        long maxTime = time - 1;
        // min time
        while(start < end){
            long mid = (start+end)/2;
            long midMinusOneDistance = (mid - 1) * (time - (mid - 1));
            long midDistance = mid * (time - mid);
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
            long mid = (start+end)/2;
            long midPlusOneDistance = (mid + 1) * (time - (mid + 1));
            long midDistance = mid * (time-mid);
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
        long numWays = maxTime - minTime + 1;
        System.out.println("Output" + numWays);
    }
    
}
