package com.gorf.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReconcile {

    private static final String COMMA_DELIMITER = ",";

    public static void main(String[] args) throws FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("D:\\David\\qb_transactions.csv"))) {
            while (scanner.hasNextLine()) {
                records.add(CsvReconcile.getRecordFromLine(scanner.nextLine()));
            }
        }

        records.forEach(System.out::println);
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(CsvReconcile.COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

}
