package com.chat.auth.utils;



import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTimeUtil {
    public static LocalDateTime toLocalDateTime(Date date){
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static long differDaysBetween(LocalDateTime date1, LocalDateTime date2){
        Duration duration = Duration.between(date1, date2);
        return duration.toDays();
    }
}
