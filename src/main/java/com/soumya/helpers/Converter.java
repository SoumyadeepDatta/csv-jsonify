package com.soumya.helpers;

import java.util.List;

public class Converter {

    /**
     * ", "+ // match a comma
     * "(?= "+ // start positive look ahead
     * " (?: "+ // start non-capturing group 1
     * " %s* "+ // match 'otherThanQuote' zero or more times
     * " %s "+ // match 'quotedString'
     * " )* "+ // end group 1 and repeat it zero or more times
     * " %s* "+ // match 'otherThanQuote'
     * " $ "+ // match the end of the string
     * ") ", // stop positive look ahead
     * 
     * @param s
     * @return
     */
    public static String[] stringToCSVArray(String s) {
        return s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    public static String numericValueHandler(String s) {
        try {

            Double d = Double.parseDouble(s);
            String res = Double.toString(d);

            String[] srr = res.split("\\.");

            return (Long.parseLong(srr[1]) == 0) ? srr[0] : srr[0] + "." + srr[1];

        } catch (NumberFormatException e) {
            return "\"" + s + "\"";
        }
    }

    public static String toJSONString(List<String> data)

    {
        String json = "";

        String[] attr = CSV.getAttributes(data);

        data.remove(0);

        for (int i = 0; i < data.size(); i++) {
            int k = 0;
            String[] arr = stringToCSVArray(data.get(i));
            json += "{";
            for (int j = 0; j < arr.length; j++) {

                json += "\"" + attr[k] + "\"" + ":";

                if (arr[j].length() == 0) {
                    json += "null";
                } else {
                    json += numericValueHandler(arr[j]);
                }

                json += (j == arr.length - 1) ? "" : ",";
                k++;
            }
            json += "}";
            json += (i == data.size() - 1) ? "" : ",";
        }

        return "[" + json + "]";
    }

}
