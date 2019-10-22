package com.tcrl.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getMonth(){
        if(LocalDate.now().getDayOfMonth()>15) {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        }else {
            return LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"));
        }
    }
}
