package com.example.springer.utils;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterCalculatorTest {


    @Test
    @Description("Count the target character in string")
    void getCharacterCountMap() {
        assertEquals(2,CharacterCalculator.getCharacterCountMap("AA,,BB",','));
        assertEquals(2,CharacterCalculator.getCharacterCountMap("!!BB",'!'));
        assertEquals(2,CharacterCalculator.getCharacterCountMap("AA..",'.'));
        assertEquals(0,CharacterCalculator.getCharacterCountMap(null,'.'));
    }
}