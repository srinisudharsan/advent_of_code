package com.srinisudharsan.aoc2023.day1.part2;

import java.util.HashMap;
import java.util.Map;

import com.srinisudharsan.aoc2023.AhoCorasick.AhoCorasick;
import com.srinisudharsan.aoc2023.AhoCorasick.AhoCorasickOutput;

public class NumberCalculator {
    private static String[] substrings = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static AhoCorasick ahoCorasick = new AhoCorasick(substrings);
    private static Map<String, Integer> substringToNumber = new HashMap<>();
    static{
        for(int i = 0; i < substrings.length; i++){
            substringToNumber.put(substrings[i], i);
        }
    }
    public static int CalculateNumber(String mainString){
        AhoCorasickOutput output = NumberCalculator.ahoCorasick.findFirstAndLastSubstrings(mainString);
        char[] chars = mainString.toCharArray();
        int firstDigit = -1;
        int lastDigit = -1;
        int firstDigitIndex = -1;
        int lastDigitIndex = -1;
        for(int i = 0; i < chars.length; i++){
            // we know it is only two digits
            if(chars[i] >= '0' && chars[i] <= '9'){
                firstDigit = chars[i] - '0';
                firstDigitIndex = i;
                break;
            }
        }

        for(int i = chars.length - 1; i >=0 ; i--){
            // we know it is only two digits
            if(chars[i] >= '0' && chars[i] <= '9'){
                lastDigit = chars[i] - '0';
                lastDigitIndex = i;
                break;
            }
        }
        
        if (output != null){
            if(firstDigitIndex == -1 || (output.getFirstString() != null && output.getFirstStringIndex() < firstDigitIndex)){
                firstDigit = substringToNumber.get(output.getFirstString());
            }
            if(lastDigitIndex == -1 || (output.getLastStringIndex() > lastDigitIndex)){
                lastDigit = substringToNumber.get(output.getLastString());
            }
        }

        return firstDigit * 10 + lastDigit;
    }
}
