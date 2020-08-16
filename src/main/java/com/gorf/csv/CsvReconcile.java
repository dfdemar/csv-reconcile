package com.gorf.csv;

import com.gorf.csv.data.PayPalEntry;
import com.gorf.csv.data.QuickBooksEntry;

import java.util.List;

public class CsvReconcile {

    public static void main(String[] args) {
        List<QuickBooksEntry> qbEntries = QuickBooksEntry.fromCsv("D:\\David\\qb_transactions_date_amount.csv");
        List<PayPalEntry> ppEntries = PayPalEntry.fromCsv("D:\\David\\paypal_transactions.csv");

        qbEntries.forEach(System.out::println);
        ppEntries.forEach(System.out::println);
    }
}
