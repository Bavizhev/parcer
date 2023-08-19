package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readJSON {

    public static String readString(String read) {
        String s = null;
        try (BufferedReader br = new BufferedReader(new FileReader(read))) {
            while ((s = br.readLine()) != null) {
                return s;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    public static List<Employee> jsonToList(String json) {

        List<Employee> list = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JSONParser jsonParser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
            for (int i = 0; i < jsonArray.size(); i++) {
                Employee employee = gson.fromJson(jsonArray.get(i).toString(), Employee.class);
                list.add(employee);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
