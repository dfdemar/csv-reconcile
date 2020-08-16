package com.gorf.csv.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuickBooksEntry {

    @JsonProperty
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate date;
    @JsonProperty
    private Double amount;

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public static List<QuickBooksEntry> fromCsv(String path) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader objectReader = csvMapper.reader(QuickBooksEntry.class).with(schema);
        List<QuickBooksEntry> entries = new ArrayList<>();

        try (Reader reader = new FileReader(path)) {
            MappingIterator<QuickBooksEntry> iterator = objectReader.readValues(reader);
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

    public static class DateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            return LocalDate.parse(parser.getText(), formatter);
        }
    }
}
