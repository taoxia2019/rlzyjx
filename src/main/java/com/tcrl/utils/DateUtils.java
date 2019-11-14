package com.tcrl.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getMonth(){

            return LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"));

    }
}
