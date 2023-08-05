package com.yupi.springbootinit.utils;

import java.text.SimpleDateFormat;

public class TimeUtils {
    public static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
