package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.example.listToJson.listToJson;

public class parseCSVF {
    public static List<Employee> parseCSV(String[] columnMapping, String fileName, String newfilename) {

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {

            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> CsvToBean = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();

            List<Employee> list = CsvToBean.parse();
            String json = listToJson(list, newfilename);

            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
