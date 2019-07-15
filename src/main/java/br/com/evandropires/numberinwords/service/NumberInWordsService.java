package br.com.evandropires.numberinwords.service;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by evandro on 04/06/18.
 */
@ApplicationScoped
public class NumberInWordsService {

    private static final HashMap<Integer, String> UNIQUE_NUMBERS = new HashMap<>();

    static {
        UNIQUE_NUMBERS.put(1, "one");
        UNIQUE_NUMBERS.put(2, "two");
        UNIQUE_NUMBERS.put(3, "three");
        UNIQUE_NUMBERS.put(4, "four");
        UNIQUE_NUMBERS.put(5, "five");
        UNIQUE_NUMBERS.put(6, "six");
        UNIQUE_NUMBERS.put(7, "seven");
        UNIQUE_NUMBERS.put(8, "eight");
        UNIQUE_NUMBERS.put(9, "nine");
        UNIQUE_NUMBERS.put(10, "ten");
        UNIQUE_NUMBERS.put(11, "eleven");
        UNIQUE_NUMBERS.put(12, "twelve");
        UNIQUE_NUMBERS.put(13, "thirteen");
        UNIQUE_NUMBERS.put(14, "fourteen");
        UNIQUE_NUMBERS.put(15, "fifteen");
        UNIQUE_NUMBERS.put(16, "sixteen");
        UNIQUE_NUMBERS.put(17, "seventeen");
        UNIQUE_NUMBERS.put(18, "eighteen");
        UNIQUE_NUMBERS.put(19, "nineteen");
        UNIQUE_NUMBERS.put(20, "twenty");
        UNIQUE_NUMBERS.put(30, "thirty");
        UNIQUE_NUMBERS.put(40, "forty");
        UNIQUE_NUMBERS.put(50, "fifty");
        UNIQUE_NUMBERS.put(60, "sixty");
        UNIQUE_NUMBERS.put(70, "seventy");
        UNIQUE_NUMBERS.put(80, "eighty");
        UNIQUE_NUMBERS.put(90, "ninety");
    }

    public String numberInWords(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException("Number is mandatory");
        }
        if (UNIQUE_NUMBERS.containsKey(number)) {
            return UNIQUE_NUMBERS.get(number);
        }
        Stack<String> inWordsQueue = new Stack<String>();

        int numberDecreasing = number;
        int divisor = 10;

        while (numberDecreasing > 0) {
            int mod = numberDecreasing % divisor;
            if (mod > 0) {
                inWordsQueue.add(UNIQUE_NUMBERS.get(mod));
            }
            numberDecreasing -= mod;
            divisor *= 10;
        }

        StringBuilder numberInWords = new StringBuilder();
        while (!inWordsQueue.isEmpty()) {
            if (numberInWords.length() > 0) {
                numberInWords.append("-");
            }
            numberInWords.append(inWordsQueue.pop());
        }

        return numberInWords.toString();
    }

}
