package com.gorf.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class CsvReconcile {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> qbEntryRows = CsvReconcile.getCsv(new File("D:\\David\\qb_transactions_date_amount.csv"));
        List<QuickbooksEntry> qbEntries = qbEntryRows.stream().map(QuickbooksEntry::fromCsvRow).collect(toList());

        List<String> paypalEntryRows = CsvReconcile.getCsv(new File("D:\\David\\paypal_transactions.csv"));
        List<QuickbooksEntry> paypalEntries = qbEntryRows.stream().map(QuickbooksEntry::fromCsvRow).collect(toList());

        qbEntries.forEach(System.out::println);
        paypalEntries.forEach(System.out::println);
    }

    private static List<String> getCsv(File csvFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(csvFile)) {
            List<String> rows = new ArrayList<>();
            while (scanner.hasNextLine()) {
                rows.add(scanner.nextLine());
            }
            return rows;
        }
    }
}
