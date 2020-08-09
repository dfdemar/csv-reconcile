package com.gorf.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReconcile {

    private static final String COMMA_DELIMITER = ",";

    public static void main(String[] args) throws FileNotFoundException {
        List<List<String>> qbTransactions = CsvReconcile.getCsv(new File("D:\\David\\qb_transactions_date_amount.csv"));
        List<List<String>> paypalTransactions = CsvReconcile.getCsv(new File("D:\\David\\paypal_transactions.csv"));

        // qbTransactions.forEach(System.out::println);
        // paypalTransactions.forEach(System.out::println);
    }

    private static List<List<String>> getCsv(File csvFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(csvFile)) {
            List<List<String>> rows = new ArrayList<>();
            while (scanner.hasNextLine()) {
                rows.add(CsvReconcile.getRecordFromLine(scanner.nextLine()));
            }
            return rows;
        }
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
