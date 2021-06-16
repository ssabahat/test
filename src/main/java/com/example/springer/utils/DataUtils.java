package com.example.springer.utils;

import java.util.Date;

public class DataUtils {
    public static double getTimeDiffFromCurrentTime(Date createdAt){
        Date now = new Date();
        return  (double)(now.getTime() - createdAt.getTime()) / (1000*60);
    }
}
