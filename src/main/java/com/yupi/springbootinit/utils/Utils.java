package com.yupi.springbootinit.utils;

public class Utils {
    public static <T> T getOr(T obj, T otherObj) {
        if (obj == null) {
            return otherObj;
        }

        return obj;
    }

    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;
    }
}
