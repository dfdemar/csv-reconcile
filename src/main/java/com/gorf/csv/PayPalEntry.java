package com.gorf.csv;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class PayPalEntry {

    private LocalDate date;
    private BigDecimal amount;

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static PayPalEntry fromCsvRow(String row) {
        PayPalEntry entry = new PayPalEntry();
        try (Scanner rowScanner = new Scanner(row)) {
            rowScanner.useDelimiter(",");
            entry.date = LocalDate.parse(rowScanner.next());
            entry.amount = new BigDecimal(rowScanner.next());
        }
        return entry;
    }
}
