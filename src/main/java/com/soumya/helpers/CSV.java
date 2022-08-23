package com.soumya.helpers;

import java.util.List;

public abstract class CSV {

    public static int getHeaderLength(List<String> data) {
        return data.get(0).split(",").length;
    }

    public static String[] getAttributes(List<String> data) {
        return data.get(0).split(",");
    }
}
