package com.exam.onlineexam.util;

import java.time.LocalDateTime;

public class ExamTimerUtil {

    public static boolean isTimeExceeded(LocalDateTime startTime, int durationMinutes) {
        LocalDateTime endTime = startTime.plusMinutes(durationMinutes);
        return LocalDateTime.now().isAfter(endTime);
    }
}

