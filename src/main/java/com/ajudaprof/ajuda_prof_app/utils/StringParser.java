package com.ajudaprof.ajuda_prof_app.utils;

import org.apache.tomcat.util.buf.StringUtils;

import java.util.*;


public class StringParser {

    public static String formatObjectsToString(List<Object> objectList) {
        String formattedString = "";
        for (Object obj : objectList) {
            formattedString += obj.toString() + "; ";
        }
        return formattedString;
    }
    public static String formatObjectsToString(Object[] objectList) {
        String formattedString = "";
        for (Object obj : objectList) {
            formattedString += obj.toString() + "; ";
        }
        return formattedString;
    }

    public static String stringArrayToString(String[] arr){
        String joinedString = String.join(", ", arr);
        return joinedString;
    }

}
