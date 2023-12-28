package com.srinisudharsan.aoc2023.day2.part2;

public class MinCubeProductCalculator{
    public static int computeMinCubeProduct(String game){
        String games = game.substring(game.indexOf(':')+2);
        int red = 0;
        int blue = 0;
        int green = 0;
        for(String subGame : games.split(";")){
            for(String color : subGame.trim().split(",")){
                String[] vals = color.trim().split(" ");
                if(vals != null && vals.length == 2){
                    switch (vals[1].toLowerCase()){
                        case "red":
                            red = Math.max(red, Integer.parseInt(vals[0].trim()));
                            break;
                        case "green":
                            green = Math.max(green, Integer.parseInt(vals[0].trim()));
                            break;
                        case "blue":
                            blue = Math.max(blue, Integer.parseInt(vals[0].trim()));
                            break;
                    }
                }
            }
        }
        return red * green * blue;
    }
}
