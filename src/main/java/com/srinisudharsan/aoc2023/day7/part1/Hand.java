package com.srinisudharsan.aoc2023.day7.part1;

import java.util.HashMap;
import java.util.Map;

public class Hand implements Comparable<Hand>{
    private char[] cards;
    private HandStrength strength;
    private int bid;
    public Hand(String cards, int bid) throws Exception{
        if(cards == null || cards.length() != 5){
            throw new Exception("Invalid hand ");
        }
        char[] cardChars = cards.toCharArray(); 
        for(char c: cardChars){
            if (!Deck.isValidCard(c)){
                throw new Exception("Invalid card in hand "+ c);
            }
        }
        this.cards = cardChars;
        strength = null;
        this.bid = bid;
    }

    public HandStrength getHandStrength(){
        if(this.strength != null){
            return this.strength;
        }
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();
        for(char c: this.cards){
            Integer val = freqMap.get(c);
            freqMap.put(c, val == null ? 1 : val + 1);
        }
        switch(freqMap.keySet().size()){
            case 1:
                this.strength = HandStrength.FIVE_OF_A_KIND;
                break;
            case 2:
                int firstVal = freqMap.get(this.cards[0]);
                // it has to be 2,3 or 1,4
                if(firstVal == 1 || firstVal == 4){
                    this.strength = HandStrength.FOUR_OF_A_KIND;
                }else{
                    this.strength = HandStrength.FULL_HOUSE;
                }
                break;
            case 3:
                // 1,1,3 or 1,2,2
                for(char c: this.cards){
                    int val = freqMap.get(c);
                    if(val == 3){
                        this.strength = HandStrength.THREE_OF_A_KIND;
                        break;
                    }else if(val == 2){
                        this.strength = HandStrength.TWO_PAIR;
                        break;
                    }
                }
                break;
            case 4:
                this.strength = HandStrength.ONE_PAIR;
                break;
            default:
                this.strength = HandStrength.HIGHCARD; 
        }
        return strength;
    }

    public char[] getCards(){
        return this.cards;
    }

    public int getBid(){
        return this.bid;
    }

    @Override
    public int compareTo(Hand other) {
        HandStrength thiStrength = this.getHandStrength();
        HandStrength otherStrength = other.getHandStrength();
        if(thiStrength != otherStrength){
            return thiStrength.compareTo(otherStrength);
        }

        char[] otherCards = other.getCards();
        for(int i = 0;i < 5 ;i++){
            int thisCardStrength = Deck.getStrength(this.cards[i]);
            int otherCardStrength = Deck.getStrength(otherCards[i]);
            if( thisCardStrength != otherCardStrength){
                return thisCardStrength > otherCardStrength ? 1 : -1;
            }
        }
        return 0;
    }
}
