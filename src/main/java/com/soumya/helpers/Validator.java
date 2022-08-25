package com.soumya.helpers;

import java.util.List;

class CSVFormatViolationException extends Exception {

    @Override
    public String toString() {
        return "CSV format violated";
    }

}

public class Validator {

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
     * @param data
     */
    public static void fileIntegrityChecker(List<String> data) {

        int n = CSV.getHeaderLength(data);

        if (data.size() > 1) {
            for (int i = 1; i < data.size(); i++) {
                if (data.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)").length > n) {
                    try {
                        throw new CSVFormatViolationException();
                    } catch (CSVFormatViolationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
