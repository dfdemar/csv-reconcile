package com.gorf.csv.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PayPalEntry {

    @JsonProperty("Date")
    private LocalDate date;
    @JsonProperty("Net")
    private Double amount;

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public static List<PayPalEntry> fromCsv(String path) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader objectReader = csvMapper.reader(PayPalEntry.class).with(schema);
        List<PayPalEntry> entries = new ArrayList<>();

        try (Reader reader = new FileReader(path)) {
            MappingIterator<PayPalEntry> iterator = objectReader.readValues(reader);
            while (iterator.hasNext()) {
                try {
                    entries.add(iterator.next());
                } catch (RuntimeJsonMappingException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }

        return entries;
    }

    public String toString() {
        return String.format("Date: %s | Amount %s", date, amount);
    }
}
