package com.example.springer.model;

public class NewsArticleWithRelevance extends NewsArticle {
    private final Relevance relevance;

    public NewsArticleWithRelevance(NewsArticle newsArticle, Relevance relevance) {
        super(newsArticle);
        this.relevance = relevance;
    }

    public Relevance getRelevance() {
        return relevance;
    }
}
