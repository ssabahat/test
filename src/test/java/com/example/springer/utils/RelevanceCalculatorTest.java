package com.example.springer.utils;

import com.example.springer.model.NewsArticle;
import com.example.springer.model.Relevance;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RelevanceCalculatorTest {

    @BeforeEach
    void setUp() {
        newsArticle = new NewsArticle();
    }

    private NewsArticle newsArticle;

    @Test
    @Description("Comma count = 0 , Exclamation 2, fullStop = 1 TimeDiff = 59seconds")
    void getRelevance_For_Hot_Exclamation_2_FullStop_1_TimeDiff_Less_Than_1_Minute() {
        //Comma count = 0 , Exclamation 2, fullStop = 1 TimeDiff = 59seconds
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() - 59 * 1000), 0, 2, 1);
        assertEquals(Relevance.HOT, RelevanceCalculator.getRelevance(newsArticle));
    }

    @Test
    @Description("Comma count = 0 , Exclamation 2, fullStop = 1 TimeDiff = 60seconds")
    void getRelevance_For_Hot_Exclamation_2_FullStop_1_TimeDiff_Equal_To_1_Minute() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() - 60 * 1000), 0, 2, 1);
        assertEquals(Relevance.HOT, RelevanceCalculator.getRelevance(newsArticle));
    }

    @Test
    @Description("Comma count = 0 , Exclamation 2, fullStop = 1 TimeDiff = 61seconds")
    void getRelevance_For_Hot_Exclamation_2_FullStop_1_TimeDiff_Greater_Than_1_Minute() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() - 61 * 1000), 0, 2, 1);
        assertEquals(Relevance.STANDARD, RelevanceCalculator.getRelevance(newsArticle));
    }

    @Test
    @Description("Comma count = 3 , Exclamation 2, fullStop = 1 TimeDiff = 59seconds")
    void getRelevance_For_Hot_Exclamation_2_FullStop_1_Comma_3_TimeDiff_Less_Than_1_Minute() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() -59*1000),3,2,1);
        assertEquals(Relevance.HOT, RelevanceCalculator.getRelevance(newsArticle));
    }

    @Test
    @Description("Comma count = 2 , Exclamation 2, fullStop = 1 TimeDiff = 60seconds")
    void getRelevance_For_Hot_Exclamation_2_FullStop_1_Comma_3_TimeDiff_Equals_To_1_Minute() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() -60*1000),2,2,1);
        assertEquals(Relevance.HOT, RelevanceCalculator.getRelevance(newsArticle));
    }

    @Test
    @Description("Comma count = 2 , Exclamation 2, fullStop = 1 TimeDiff = 61seconds")
    void getRelevance_For_Hot_Exclamation_2_FullStop_1_Comma_3_TimeDiff_Greater_Than_1_Minute() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() -61*1000),2,2,1);

        assertEquals(Relevance.BORING, RelevanceCalculator.getRelevance(newsArticle));
    }

    @Test
    @Description("Comma count = 2 , Exclamation 2, fullStop = 1 TimeDiff = 61seconds")
    void getRelevance_For_Hot_Exclamation_0_FullStop_0_Comma_0_TimeDiff_Less_Than_1_Minute() {
        //Comma count = 0 , Exclamation 0, fullStop = 0 TimeDiff = 59seconds
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() -59*1000),0,0,0);
        assertEquals(Relevance.STANDARD, RelevanceCalculator.getRelevance(newsArticle));
    }



    @Test
    @Description("Comma count = 2 , Exclamation 0, fullStop = 0 TimeDiff = 5minutes")
    void getRelevance_For_Hot_Exclamation_0_FullStop_0_Comma_2_TimeDiff_Less_Than_5_Minute() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() -5*60*1000),2,0,0);
        assertEquals(Relevance.BORING, RelevanceCalculator.getRelevance(newsArticle));
    }


    @Test
    @Description("Comma count = 2 , Exclamation 0, fullStop = 0 TimeDiff > 5minutes")
    void getRelevance_For_Hot_Exclamation_0_FullStop_0_Comma_2_TimeDiff_Less_Than_1_Minute() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() -51*60*1000),2,0,0);
        assertEquals(Relevance.STANDARD, RelevanceCalculator.getRelevance(newsArticle));
    }


    @Test
    void getRelevanceForNull() {
        assertNull(RelevanceCalculator.getRelevance(null));
    }

    private void setTimeAndCharacterCount(Date createdAt, int commaCount, int exclamation, int fullStop) {
        newsArticle.setCreatedAt(createdAt);
        newsArticle.setNumberOfCommas(commaCount);
        newsArticle.setNumberOfFullStop(fullStop);
        newsArticle.setNumberOfExclamation(exclamation);
    }
}