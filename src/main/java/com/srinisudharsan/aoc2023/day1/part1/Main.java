package com.srinisudharsan.aoc2023.day1.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("resources/input1.txt"));
        long sum = 0;
        String line;
        try {
            line = reader.readLine();
            while(line != null){
                System.out.println("Line: " + line);
                char[] chars = line.toCharArray();
                int currVal = 0;
                for(int i = 0; i < chars.length; i++){
                    // we know it is only two digits
                    if(chars[i] >= '0' && chars[i] <= '9'){
                        currVal = chars[i] - '0';
                        break;
                    }
                }
                currVal *= 10;

                for(int i = chars.length - 1; i >=0 ; i--){
                    // we know it is only two digits
                    if(chars[i] >= '0' && chars[i] <= '9'){
                        currVal += (chars[i] - '0');
                        break;
                    }
                }
                sum += currVal;
                System.out.println("CurrVal: " + currVal);
                System.out.println("Sum: " + sum);
                line = reader.readLine();
            }
            System.out.println("Result: " + sum);
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }
}
