package com.gorf.csv;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class QuickbooksEntry {

    private LocalDate date;
    private BigDecimal amount;

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static QuickbooksEntry fromCsvRow(String row) {
        QuickbooksEntry entry = new QuickbooksEntry();
        try (Scanner rowScanner = new Scanner(row)) {
            rowScanner.useDelimiter(",");
            entry.date = LocalDate.parse(rowScanner.next());
            entry.amount = new BigDecimal(rowScanner.next());
        }
        return entry;
    }
}
