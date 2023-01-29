package com.soumya.helpers;

import java.util.ArrayList;
import java.util.List;

public abstract class CSV {

    public static ArrayList<String> parseCSVString(String s) {
        String value = "";
        int delim = 0;
        ArrayList<String> csvString = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (ch == ',') {
                if (delim % 2 != 0) {
                    value += ch;
                } else {
                    csvString.add(value);
                    value = "";
                }
            } else {
                if (ch == '"') {
                    delim++;
                }
                value += ch;
            }
        }
        csvString.add(value);
        return csvString;
    }

    public static int getHeaderLength(List<String> data) {
        return data.get(0).split(",").length;
    }

    public static String[] getHeader(List<String> data) {
        return data.get(0).split(",");
    }
}
