package com.gorf.csv;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PayPalEntry {

    @JsonProperty
    private LocalDate date;
    @JsonProperty
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            entry.date = LocalDate.parse(rowScanner.next(), formatter);

            entry.amount = rowScanner.nextBigDecimal();
        } catch (Exception e) {
            return new PayPalEntry();
        }
        return entry;
    }

    public String toString() {
        return String.format("Date: %s | Amount %s", date, amount);
    }
}
