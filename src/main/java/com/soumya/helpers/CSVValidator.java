package com.soumya.helpers;

import java.util.List;

class CSVIntegrityViolationException extends Exception {

    @Override
    public String toString() {
        return "CSV format violated";
    }

}

public class CSVValidator {

    public static void fileIntegrityChecker(List<String> data) {

        int n = CSV.getHeaderLength(data);

        if (data.size() > 1) {
            for (int i = 1; i < data.size(); i++) {
                if (data.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)").length > n) {
                    try {
                        throw new CSVIntegrityViolationException();
                    } catch (CSVIntegrityViolationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
