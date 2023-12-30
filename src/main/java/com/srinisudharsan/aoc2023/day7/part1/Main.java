package com.srinisudharsan.aoc2023.day7.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException, Exception{
        List<Hand> hands = new ArrayList<Hand>();
        BufferedReader bf = new BufferedReader(new FileReader("resources/input7.txt"));
        HashSet<String> cardSet = new HashSet<>();
        String line;
        while((line = bf.readLine()) != null){
            line = line.trim();
            if(line.length() == 0){
                continue;
            }
            String[] lineParts = line.split(" +");
            if(lineParts.length != 2){
                continue;
            }
            cardSet.add(line);
            hands.add(new Hand(lineParts[0].trim(), Integer.parseInt(lineParts[1].trim())));
        }

        hands.sort(new Comparator<Hand>() {

            @Override
            public int compare(Hand arg0, Hand arg1) {
                if(arg0 == null && arg1 == null){
                    return 0;
                }
                if(arg0 == null){
                    return -1;
                }
                if(arg1 == null){
                    return 1;
                }
                return arg0.compareTo(arg1);
            }
            
        });
        long val = IntStream.range(0, hands.size())
        .parallel()
        .mapToLong((idx) -> (idx+1) * hands.get(idx).getBid())
        .sum();

        System.out.println("Output: " + val);
    }
}
