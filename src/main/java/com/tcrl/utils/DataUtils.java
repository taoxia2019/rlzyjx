package com.tcrl.utils;

import java.time.LocalDate;

public class DataUtils {
    public static String getMonth(){
        if(LocalDate.now().getDayOfMonth()>15) {
            return LocalDate.now().getMonthValue()+"月";
        }else {
            return LocalDate.now().getMonthValue()-1+"月";
        }
    }
}
