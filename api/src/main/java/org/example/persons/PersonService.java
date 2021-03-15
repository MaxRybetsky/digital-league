package org.example.persons;

import java.util.List;

public interface PersonService {
    boolean hasCopy(Person person);

    List<Person> selectByFilters(int age, String... letters);

    String getDuplicates();
}
