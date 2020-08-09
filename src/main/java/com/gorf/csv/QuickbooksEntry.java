package com.gorf.csv;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class QuickbooksEntry {

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

    public static QuickbooksEntry fromCsvRow(String row) {
        QuickbooksEntry entry = new QuickbooksEntry();
        try (Scanner rowScanner = new Scanner(row)) {
            rowScanner.useDelimiter(",");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            entry.date = LocalDate.parse(rowScanner.next(), formatter);

            entry.amount = rowScanner.nextBigDecimal();
        } catch (Exception e) {
            return new QuickbooksEntry();
        }
        return entry;
    }

    public String toString() {
        return String.format("Date: %s | Amount %s", date, amount);
    }
}
