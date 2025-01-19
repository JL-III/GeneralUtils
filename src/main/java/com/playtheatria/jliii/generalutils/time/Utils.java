package com.playtheatria.jliii.generalutils.time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

public class Utils {
    public static final ZoneId timeZone = ZoneId.of("America/New_York");
    public static boolean isNewDay(LocalDateTime timestamp, LocalDateTime now) {
        ZonedDateTime zonedTimestamp = timestamp.atZone(timeZone);
        ZonedDateTime zonedNow = now.atZone(timeZone);
        return !zonedTimestamp.toLocalDate().isEqual(zonedNow.toLocalDate());
    }

    public static boolean isNewWeek(LocalDateTime timestamp, LocalDateTime now) {
        ZonedDateTime zonedTimestamp = timestamp.atZone(timeZone);
        ZonedDateTime zonedNow = now.atZone(timeZone);
        WeekFields weekFields = WeekFields.of(DayOfWeek.SUNDAY, 1);
        int weekOfYearTimestamp = zonedTimestamp.get(weekFields.weekOfWeekBasedYear());
        int weekOfYearNow = zonedNow.get(weekFields.weekOfWeekBasedYear());
        return zonedTimestamp.getYear() != zonedNow.getYear() || weekOfYearTimestamp != weekOfYearNow;
    }

    public static boolean isNewMonth(LocalDateTime timestamp, LocalDateTime now) {
        ZonedDateTime zonedTimestamp = timestamp.atZone(timeZone);
        ZonedDateTime zonedNow = now.atZone(timeZone);
        return zonedTimestamp.getYear() != zonedNow.getYear() || zonedTimestamp.getMonth() != zonedNow.getMonth();
    }

    public static boolean isNewYear(LocalDateTime timestamp, LocalDateTime now) {
        ZonedDateTime zonedTimestamp = timestamp.atZone(timeZone);
        ZonedDateTime zonedNow = now.atZone(timeZone);
        return zonedTimestamp.getYear() != zonedNow.getYear();
    }

    public static DateTimeFormatter getFormat() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm:ss a");
    }
}
