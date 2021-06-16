package com.example.springer.utils;

public class CharacterCalculator {

    public static int getCharacterCountMap(String text, char targetChar) {
        int charCount = 0;
        if (text != null) {
            for (char c : text.toCharArray()) {
                if (c == targetChar) {
                    charCount++;
                }
            }
        }
        return charCount;
    }
}
