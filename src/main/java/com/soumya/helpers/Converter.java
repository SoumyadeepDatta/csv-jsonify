package com.soumya.helpers;

import java.util.ArrayList;
import java.util.List;

public class Converter {

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

    public static String toJSONString(List<String> data, boolean beautify) throws CSVFormatViolationException

    {
        String json = "";

        String[] attr = CSV.getHeader(data);
        int attrLen = CSV.getHeaderLength(data);

        data.remove(0);

        for (int i = 0; i < data.size(); i++) {
            int k = 0;
            ArrayList<String> arr = CSV.parseCSVString(data.get(i));

            if (arr.size() != attrLen) {
                throw new CSVFormatViolationException();
            }

            if(beautify){
                json+= "\n\t";
            }
            json += "{";

            for (int j = 0; j < arr.size(); j++) {

                if (arr.get(j).length() != 0) {
                    if(beautify){
                        json+= "\n\t\t";
                    }
                    json += "\"" + attr[k] + "\"" + ":";
                    if(beautify){
                        json+=" ";
                    }
                    json += numericValueHandler(arr.get(j));
                    json += (j == arr.size() - 1) ? "" : ",";
                }

                k++;
            }
            if(beautify){
                json+= "\n\t";
            }
            json += "}";
            json += (i == data.size() - 1) ? "" : ",";
        }

        return "[\n" + json + "\n]";
    }

}
