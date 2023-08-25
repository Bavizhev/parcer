package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class readJSONTest {

    @Test
    void readString() {     // Без Hamcrest

        String read = "data2.json";

        String readtest = readJSON.readString(read);

        Assertions.assertNotNull(readtest);
    }

    @Test
    void jsonToList() {     // С использованием библиотеки Hamcrest

        String json = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}," +
                " {\"id\":2,\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";

        List<Employee> list = null;
        list = readJSON.jsonToList(json);

        Employee test = new Employee(1, "John", "Smith", "USA", 25);

        assertThat(list.get(0).id, equalTo(test.id)); // По отдельности все работает.
        assertThat(list.get(0).firstName, equalTo(test.firstName));
        assertThat(list.get(0).lastName, equalTo(test.lastName));
        assertThat(list.get(0).country, equalTo(test.country));
        assertThat(list.get(0).age, equalTo(test.age));

        // assertThat(list.get(0), equalTo(test)); // < -- Не знаю почему проверка не работает, хотя в выводе 'Expected:' и 'but: was' одинаковы.
    }
}