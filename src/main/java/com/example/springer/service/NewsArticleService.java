package com.example.springer.service;

import com.example.springer.model.NewsArticle;
import com.example.springer.model.NewsArticleWithRelevance;

import java.util.List;

public interface NewsArticleService {

    List<NewsArticleWithRelevance> getAllNewsArticle();

    NewsArticleWithRelevance getNewsArticleById(Long id);

    NewsArticle createNewsArticle(NewsArticle newsArticles);

    NewsArticle updateNewsArticle(NewsArticle newsArticle, Long ids);

}
