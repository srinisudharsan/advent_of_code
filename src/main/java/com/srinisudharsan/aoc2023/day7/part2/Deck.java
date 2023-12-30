package com.srinisudharsan.aoc2023.day7.part2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Deck {
    private static Set<Character> cards = new HashSet<Character>();
    private static Map<Character, Integer> cardStrength = new HashMap<Character, Integer>();
    private static char[] cardChars = {'J','2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'};

    public static boolean isValidCard(char c){
        if(cards.size() == 0){
            for(int i = 0; i < cardChars.length;i++){
                cards.add(cardChars[i]);
                cardStrength.put(cardChars[i], i+2);
            }
        }
        return cards.contains(c);
    }

    public static int getStrength(char c){
        Integer val = cardStrength.get(c);
        return val == null ? -1 : val;
    }
}
