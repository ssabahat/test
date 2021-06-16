package com.example.springer.service.impl;

import com.example.springer.exceptions.WrongFieldError;
import com.example.springer.model.NewsArticle;
import com.example.springer.model.NewsArticleWithRelevance;
import com.example.springer.model.Relevance;
import com.example.springer.repository.NewsArticleRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultNewsArticleServiceTest {

    @BeforeEach
    void setUp() {
        newsArticleService = new DefaultNewsArticleService(newsArticleRepository);
        newsArticle = new NewsArticle();
    }

    @Mock
    private NewsArticleRepository newsArticleRepository;
    private DefaultNewsArticleService newsArticleService;
    private NewsArticle newsArticle;

    @Test
    @Description("Check if repository find all method is called")
    void getAllNewsArticle() {
        newsArticleService.getAllNewsArticle();
        verify(newsArticleRepository).findAll();
    }

    @Test
    @Description("Article is hot when within 1 minute and number of exclamation is greater than full stop")
    void getNewsArticleByIdForHotArticle() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() - 59 * 1000),0,2);
        when(newsArticleRepository.findById(1L)).thenReturn(Optional.of(newsArticle));
        NewsArticleWithRelevance newsArticleWithRelevance = newsArticleService.getNewsArticleById(1L);
        assertEquals(newsArticleWithRelevance.getRelevance(), Relevance.HOT);
    }

    @Test
    @Description("Article is standard when not within 1 minute and number of exclamation is greater than full stop")
    void getNewsArticleByIdForStandardArticle() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() - 61 * 1000),0,2);
        when(newsArticleRepository.findById(1L)).thenReturn(Optional.of(newsArticle));
        NewsArticleWithRelevance newsArticleWithRelevance = newsArticleService.getNewsArticleById(1L);
        assertEquals(newsArticleWithRelevance.getRelevance(), Relevance.STANDARD);
    }

    @Test
    @Description("Article is boring when  time difference 5 minute and number of comma is greater than full stop")
    void getNewsArticleByIdForBoringArticle() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() - 61 * 1000),2,0);
        when(newsArticleRepository.findById(1L)).thenReturn(Optional.of(newsArticle));
        assertEquals(Relevance.BORING,newsArticleService.getNewsArticleById(1L).getRelevance());
    }

    @Test
    @Description("Article is standard when created before 5 minute and number of comma is greater than full stop")
    void getNewsArticleByIdForStandardWhenCommandIsGreaterThanArticle() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() - 370000),2,0);
        when(newsArticleRepository.findById(1L)).thenReturn(Optional.of(newsArticle));
        assertEquals( Relevance.STANDARD,newsArticleService.getNewsArticleById(1L).getRelevance());
    }

    @Test
    @Description("Verify repository save method is called when creating an article")
    void createNewsArticle() {
        newsArticleService.createNewsArticle(newsArticle);
        verify(newsArticleRepository).save(newsArticle);

    }

    @Test
    @Description("Verify repository save method is called with wrong creating an article")
    void createNewsArticle_Throws_Error() {
        setTimeAndCharacterCount(new Date(System.currentTimeMillis() - 370000),2,0);
        when(newsArticleRepository.save(newsArticle)).thenThrow(new DataIntegrityViolationException("test"));
        assertThrows(WrongFieldError.class, ()->newsArticleService.createNewsArticle(newsArticle));

    }

    @Test
    @Description("Verify repository save method is called when updating an article")
    void updateNewsArticle() {
        newsArticleService.updateNewsArticle(newsArticle,1L);
        verify(newsArticleRepository).save(newsArticle);
    }

    private void setTimeAndCharacterCount(Date createdAt, int commaCount, int exclamation){
        newsArticle.setCreatedAt(createdAt);
        newsArticle.setNumberOfCommas(commaCount);
        newsArticle.setNumberOfFullStop(1);
        newsArticle.setNumberOfExclamation(exclamation);
    }
}