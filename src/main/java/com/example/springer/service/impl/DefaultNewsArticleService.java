package com.example.springer.service.impl;

import com.example.springer.exceptions.BadRequestException;
import com.example.springer.exceptions.NewsArticleNotFoundException;
import com.example.springer.exceptions.WrongFieldError;
import com.example.springer.model.NewsArticle;
import com.example.springer.model.NewsArticleWithRelevance;
import com.example.springer.model.Relevance;
import com.example.springer.repository.NewsArticleRepository;
import com.example.springer.service.NewsArticleService;
import com.example.springer.utils.CharacterCalculator;
import com.example.springer.utils.RelevanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultNewsArticleService implements NewsArticleService {

    private final NewsArticleRepository newsArticleRepository;

    @Autowired
    DefaultNewsArticleService(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }

    @Override
    public List<NewsArticleWithRelevance> getAllNewsArticle() {
        List<NewsArticle> newsArticleList = newsArticleRepository.findAll();
        List<NewsArticleWithRelevance> result = new ArrayList<>();
        for(NewsArticle newsArticle : newsArticleList){
            Relevance relevance = RelevanceCalculator.getRelevance(newsArticle);
            result.add(new NewsArticleWithRelevance(newsArticle,relevance));
        }
        return result;
    }

    @Override
    public NewsArticleWithRelevance getNewsArticleById(Long id) {
        NewsArticle newsArticle = newsArticleRepository.findById(id)
                                                       .orElseThrow(() -> new NewsArticleNotFoundException("News Article with " + id + " does not " +
                                                                                                                   "exist"));
        Relevance relevance = RelevanceCalculator.getRelevance(newsArticle);
        return new NewsArticleWithRelevance(newsArticle, relevance);
    }

    @Override
    public NewsArticle createNewsArticle(NewsArticle newsArticle) {

        if (newsArticle.getId() != null) {
            throw new BadRequestException("The ID must not be provided when creating a new Hotel");
        }
        try {
            transformArticle(newsArticle);
            return newsArticleRepository.save(newsArticle);
        } catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw new WrongFieldError("Wrong field setting");
        }
    }

    @Override
    public NewsArticle updateNewsArticle(NewsArticle updateNewsArticle, Long id) {
        transformArticle(updateNewsArticle);
        return newsArticleRepository.findById(id)
                                    .map(article -> {
                                        article.setTitle(updateNewsArticle.getTitle());
                                        article.setText(updateNewsArticle.getText());
                                        article.setNumberOfExclamation(updateNewsArticle.getNumberOfExclamation());
                                        article.setNumberOfCommas(updateNewsArticle.getNumberOfCommas());
                                        article.setNumberOfFullStop(updateNewsArticle.getNumberOfFullStop());
                                        return newsArticleRepository.save(article);
                                    })
                                    .orElseGet(() -> newsArticleRepository.save(updateNewsArticle));
    }

    private void transformArticle(NewsArticle newsArticle) {
        int exclamationCount = CharacterCalculator.getCharacterCountMap(newsArticle.getText(), '!');
        int commaCount = CharacterCalculator.getCharacterCountMap(newsArticle.getText(),',');
        int fullStopCount = CharacterCalculator.getCharacterCountMap(newsArticle.getText(),'.');
        newsArticle.setNumberOfCommas(commaCount);
        newsArticle.setNumberOfExclamation(exclamationCount);
        newsArticle.setNumberOfFullStop(fullStopCount);
    }
}
