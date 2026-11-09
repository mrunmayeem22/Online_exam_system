package com.exam.onlineexam.util;

import java.util.Map;

public class ScoreCalculator {

    public static int calculateScore(Map<Long, Boolean> answers, int marksPerQuestion) {
        int score = 0;

        for (Boolean correct : answers.values()) {
            if (correct) {
                score += marksPerQuestion;
            }
        }
        return score;
    }
}
