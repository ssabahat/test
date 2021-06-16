package com.example.springer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class NewsArticle implements Serializable {
    private static final long serialVersionUID = 3252591505029724236L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column()
    private String text;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // initialize created date

    @JsonIgnore
    private int numberOfCommas;

    @JsonIgnore
    private int numberOfFullStop;

    @JsonIgnore
    private int numberOfExclamation;

    public NewsArticle(NewsArticle newsArticle) {
        this.id = newsArticle.id;
        this.title = newsArticle.title;
        this.text = newsArticle.text;
        this.createdAt = newsArticle.createdAt;
        this.numberOfCommas = newsArticle.numberOfCommas;
        this.numberOfExclamation = newsArticle.numberOfExclamation;
        this.numberOfFullStop = newsArticle.numberOfFullStop;
    }

    public NewsArticle() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getNumberOfCommas() {
        return numberOfCommas;
    }

    public void setNumberOfCommas(int numberOfCommas) {
        this.numberOfCommas = numberOfCommas;
    }

    public int getNumberOfFullStop() {
        return numberOfFullStop;
    }

    public void setNumberOfFullStop(int numberOfFullStop) {
        this.numberOfFullStop = numberOfFullStop;
    }

    public int getNumberOfExclamation() {
        return numberOfExclamation;
    }

    public void setNumberOfExclamation(int numberOfExclamation) {
        this.numberOfExclamation = numberOfExclamation;
    }
}