package com.example.springer.controllers;

import com.example.springer.model.NewsArticle;
import com.example.springer.model.NewsArticleWithRelevance;
import com.example.springer.service.NewsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsArticleController {
    private final NewsArticleService newsArticleService;

    @Autowired
    public NewsArticleController(NewsArticleService newsArticleService) {
        this.newsArticleService = newsArticleService;
    }

    @GetMapping("/articles")
    @ResponseStatus(HttpStatus.OK)
    public List<NewsArticleWithRelevance> getAllHotels() {
        return newsArticleService.getAllNewsArticle();
    }

    @PostMapping(path = "/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public NewsArticle createNewsArticle(@RequestBody NewsArticle newsArticle) {
        return newsArticleService.createNewsArticle(newsArticle);
    }

    @GetMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsArticle getNewsArticleById(@PathVariable(value = "id") Long id) {
        return newsArticleService.getNewsArticleById(id);
    }

    @PutMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsArticle updateNewsArticle(@RequestBody NewsArticle newsArticle, @PathVariable long id) {
        return newsArticleService.updateNewsArticle(newsArticle, id);
    }
}
