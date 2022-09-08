package com.soumya.helpers;

public class CSVFormatViolationException extends Exception {

    @Override
    public String toString() {
        return "CSV format violated";
    }

}
