package com.gorf.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.gorf.csv.data.QuickBooksEntry;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvFile {

    private final File file;

    public CsvFile(File file) {
        this.file = file;
    }

    public List<QuickBooksEntry> toList() {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader objectReader = csvMapper.reader(QuickBooksEntry.class).with(schema);
        List<QuickBooksEntry> entries = new ArrayList<>();

        try (Reader reader = new FileReader(file)) {
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
}
