package org.example;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.example.parseCSVF.parseCSV;
import static org.example.parseXMLF.parseXML;
import static org.example.readJSON.jsonToList;
import static org.example.readJSON.readString;

public class Main {
    public static void main(String[] args) {

        List<Employee> staff = new ArrayList<>();
        staff.add(new Employee(1, "John", "Smith", "USA", 25));
        staff.add(new Employee(2, "Inav", "Petrov", "RU", 23));

        ColumnPositionMappingStrategy<Employee> strategy =
                new ColumnPositionMappingStrategy<>();
        strategy.setType(Employee.class);

        strategy.setColumnMapping("id", "firstName", "lastName", "country", "age");
        try (Writer writer = new FileWriter("data.csv")) {
            StatefulBeanToCsv<Employee> sbc =
                    new StatefulBeanToCsvBuilder<Employee>(writer)
                            .withMappingStrategy(strategy)
                            .build();
            sbc.write(staff);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String fileNameCSV = "data.csv"; // 1
        String newfileNameCSV = "data.json";
        List<Employee> listCSV = parseCSV(columnMapping, fileNameCSV, newfileNameCSV);

        String fileNameXML = "data.xml"; // 2
        String newfileNameXML = "data2.json";
        List<Employee> listXML = parseXML(fileNameXML, newfileNameXML, columnMapping);

        String json = readString("data2.json"); // 3
        System.out.println(json);
        List<Employee> list = jsonToList(json);
        //System.out.println(list);

    }
}