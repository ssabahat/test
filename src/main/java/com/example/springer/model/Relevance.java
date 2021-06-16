package com.example.springer.model;

public enum Relevance {
    STANDARD("standard"),
    HOT("hot"),
    BORING("boring");

    private final String strVal;
    Relevance(String relevanceType) {
        strVal = relevanceType;
    }
}
