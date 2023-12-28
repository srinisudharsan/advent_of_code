package com.srinisudharsan.aoc2023.day2.part1;

public class ValidGameFinder{
    public static int gameNoIfValidGame(String game, GameRule rule){
        int gameNo = Integer.parseInt(game.substring(5, game.indexOf(':')));
        String games = game.substring(game.indexOf(':')+2);
        int red = -1;
        int blue = -1;
        int green = -1;
        for(String subGame : games.split(";")){
            for(String color : subGame.trim().split(",")){
                String[] vals = color.trim().split(" ");
                if(vals != null && vals.length == 2){
                    switch (vals[1].toLowerCase()){
                        case "red":
                            red = Integer.parseInt(vals[0].trim());
                            break;
                        case "green":
                            green = Integer.parseInt(vals[0].trim());
                            break;
                        case "blue":
                            blue = Integer.parseInt(vals[0].trim());
                            break;
                    }
                }
                if(red > rule.getMaxRed() || green > rule.getMaxGreen() || blue > rule.getMaxBlue()){
                    return 0;
                }
            }
            System.out.println("SubGame "+subGame+" Red: "+red+" Green: "+green+" Blue: "+blue);
        }
        return gameNo;
    }
}
