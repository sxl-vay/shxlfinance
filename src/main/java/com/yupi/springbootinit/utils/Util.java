package com.yupi.springbootinit.utils;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
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

    public Util() {
    }

    public static String null2s(String s, String def) {
        return s != null && !s.equals("") ? s : (def == null ? "" : def);
    }

    public static String null2String(Object obj, String def) {
        return obj == null ? def : obj.toString();
    }

    public static String null2String(Object obj) {
        return null2String(obj, "");
    }

    public static int getIntValue(Object obj, int def) {
        try {
            return Integer.parseInt(null2String(obj));
        } catch (Exception var3) {
            return def;
        }
    }

    public static int getIntValue(Object obj) {
        return getIntValue(obj, -1);
    }

    public static Long getLongValue(Object obj) {
        return getLongValue(null2String(obj), -1L);
    }

    public static Long getLongValue(Object obj, long def) {
        try {
            return Long.parseLong(null2String(obj));
        } catch (Exception var4) {
            return def;
        }
    }

    public static List<String> splitString2List(String input, String delim) {
        return splitString2List(input, delim, -1);
    }


    public static double getDoubleValue(String v) {
        return getDoubleValue(v, -1.0);
    }

    public static double getDoubleValue(String v, double def) {
        try {
            return Double.parseDouble(v);
        } catch (Exception var4) {
            return def;
        }
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj.getClass().isArray()) {
            return ((Object[])((Object[])obj)).length == 0;
        } else if (obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        } else {
            return obj instanceof Map ? ((Map)obj).isEmpty() : obj.toString().equals("");
        }
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }


    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getUUIDStartsWithLetter() {
        String uuid;
        for(uuid = getUUID(); Character.isDigit(uuid.charAt(0)); uuid = getUUID()) {
        }

        return uuid;
    }

    public static String getExceptionMsg(Throwable ex) {
        String msg = "";
        if (ex instanceof BindException) {
            FieldError fieldError = ((BindException)ex).getFieldError();
            msg = fieldError == null ? ex.getMessage() : fieldError.getField() + fieldError.getDefaultMessage();
        } else {
            Throwable exCause = ex.getCause();
            msg = exCause == null ? ex.getMessage() : getExceptionMsg(exCause);
        }

        return msg;
    }

    public static boolean isPrimitive(Class<?> cl) {
        return cl == Boolean.class || cl == Character.class || cl == Byte.class || cl == Short.class || cl == Integer.class || cl == Long.class || cl == Float.class || cl == Double.class || cl == String.class;
    }


    public static ArrayList TokenizerString(String str, String dim) {
        return TokenizerString(str, dim, false);
    }

    public static ArrayList TokenizerString(String str, String dim, boolean returndim) {
        str = null2String(str);
        dim = null2String(dim);
        ArrayList strlist = new ArrayList();
        StringTokenizer strtoken = new StringTokenizer(str, dim, returndim);

        while(strtoken.hasMoreTokens()) {
            strlist.add(strtoken.nextToken());
        }

        return strlist;
    }

    public static String toHtmlForSplitPage(String s) {
        char[] c = s.toCharArray();
        int i = 0;
        StringBuffer buf = new StringBuffer();

        while(i < c.length) {
            char ch = c[i++];
            if (ch == '\'') {
                buf.append("\\'");
            } else if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else if (ch == '&') {
                buf.append("&amp;");
            } else {
                buf.append(ch);
            }
        }

        return buf.toString();
    }

    public static boolean isExcuteFile(String fileName) {
        if (fileName == null) {
            return false;
        } else {
            boolean returnValue = false;
            fileName = fileName.replaceAll("(\u0000|::).*$", "");
            int extNamePos = fileName.lastIndexOf(".");
            if (extNamePos != -1) {
                String extName = null2String(fileName.substring(extNamePos + 1));
                if (extName.equalsIgnoreCase("jsp") || extName.equalsIgnoreCase("php") || extName.equalsIgnoreCase("jspx")) {
                    returnValue = true;
                }
            }

            return returnValue;
        }
    }

    public static <V> List<Map<String, V>> getCaseInsensitiveMapList(List<Map<String, V>> list) {
        List<Map<String, V>> resultList = new ArrayList();
        list.forEach((e) -> {
            resultList.add(getCaseInsensitiveMap(e));
        });
        return resultList;
    }

    public static <V> Map<String, V> getCaseInsensitiveMap(Map<String, V> map) {
        TreeMap<String, V> result = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        result.putAll(map);
        return result;
    }
}
