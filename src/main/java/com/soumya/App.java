package com.soumya;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import com.soumya.helpers.CSVValidator;
import com.soumya.helpers.Converter;

/**
 * @author Soumyadeep Datta
 * @version 1.0.0
 * covid.csv
 */
public class App {

    public static List<String> readFile(String path) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return br.lines().collect(Collectors.toList());
    }

    public static void writeToFile(String data) {

        try {
            FileWriter writer = new FileWriter("output.txt", false);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println(" -----------------------------------------------------------------------");
        System.out.println("|\t\t\tCONVERTER [Version 1.0.0]\t\t\t|");
        System.out.println(" -----------------------------------------------------------------------");

        System.out.print(" File path : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long startTime = System.currentTimeMillis();

        List<String> data = readFile(br.readLine());

        if (data.size() > 1) {

            CSVValidator.fileIntegrityChecker(data);

            writeToFile(Converter.toJSONString(data));

            long endTime = System.currentTimeMillis();

            System.out.println(" Write successful !!!" + " (" + (endTime - startTime) + " ms)" );

        }

        System.out.println(" -----------------------------------------------------------------------");
        System.out.println("|\t\t      THANK YOU FOR USING CONVERTER\t\t\t|");
        System.out.println(" -----------------------------------------------------------------------");

    }
}
