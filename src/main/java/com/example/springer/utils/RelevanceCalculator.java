package com.example.springer.utils;

import com.example.springer.model.NewsArticle;
import com.example.springer.model.Relevance;

public class RelevanceCalculator {
    private static final int ONE_MINUTE = 1;
    private static final int FIVE_MINUTE = 5;

    public static Relevance getRelevance(NewsArticle newsArticle) {

        if (newsArticle == null) {
            return null;
        }
        double timeDiffInMinutes = DataUtils.getTimeDiffFromCurrentTime(newsArticle.getCreatedAt());

        if (isHot(timeDiffInMinutes, newsArticle)) {
            return Relevance.HOT;
        }
        else if (isBoring(timeDiffInMinutes, newsArticle)) {
            return Relevance.BORING;
        }

        return Relevance.STANDARD;
    }

    private static boolean isHot(double timeDiffInMinutes, NewsArticle newsArticle) {
        return timeDiffInMinutes <= ONE_MINUTE && newsArticle.getNumberOfExclamation() > newsArticle.getNumberOfFullStop();
    }

    private static boolean isBoring(double timeDiffInMinutes, NewsArticle newsArticle) {
        return timeDiffInMinutes <= FIVE_MINUTE && newsArticle.getNumberOfCommas() > newsArticle.getNumberOfFullStop();
    }
}
