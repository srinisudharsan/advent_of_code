package com.srinisudharsan.aoc2023.day10.part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("resources/input10.txt"));
        // read file as 2d char array
        String line;
        List<char[]> areaList = new ArrayList<char[]>();
        while((line = bf.readLine()) != null){
            areaList.add(line.trim().toCharArray());
        }

        char[][] areaArray = areaList.toArray(new char[areaList.size()][areaList.get(0).length]);
        for(int i = 0; i< areaList.size(); i++){
            char[] row = areaList.get(i);
            for(int j = 0; j < areaArray[i].length; j++){
                areaArray[i][j] = row[j];
            }
        }

        PipeArea pipeArea = new PipeArea(areaArray);
        System.out.println(pipeArea.getFarthestLocationDistance());
    }
}