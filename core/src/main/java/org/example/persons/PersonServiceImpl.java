package org.example.persons;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.util.RegexCreator.createWordStartsWithRegex;

public class PersonServiceImpl implements PersonService {
    private final List<Person> persons;

    public PersonServiceImpl(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean hasCopy(Person person) {
        return persons.contains(person);
    }

    @Override
    public List<Person> selectByFilters(int age, String... letters) {
        String regex = createWordStartsWithRegex(letters);
        return persons.stream()
                .filter(
                        person ->
                                person.getAge() <= age
                                        && checkPersonsSurname(person, regex)
                )
                .collect(Collectors.toList());
    }

    @Override
    public String getDuplicates() {
        Map<Person, Integer> personsDataInfo = getAllUniqueOccurs();
        countAllOccursInMap(personsDataInfo);
        personsDataInfo = findDuplicates(personsDataInfo);
        personsDataInfo = sortPersonsInfoByAge(personsDataInfo);
        return formatPersonsDataInfo(personsDataInfo);
    }

    private Map<Person, Integer> findDuplicates(Map<Person, Integer> personsDataInfo) {
        return personsDataInfo.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        )
                );
    }

    private String formatPersonsDataInfo(Map<Person, Integer> personsDataInfo) {
        StringBuilder result = new StringBuilder();
        personsDataInfo.forEach(
                (key, value) -> result.append("{")
                        .append(value)
                        .append(", ")
                        .append(key)
                        .append("}\n")
        );
        return result.toString();
    }

    private Map<Person, Integer> sortPersonsInfoByAge(Map<Person, Integer> personsDataInfo) {
        return personsDataInfo.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> -entry.getKey().getAge()))
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (u, v) -> {
                                    throw new IllegalStateException(
                                            String.format("Duplicate key %s", u)
                                    );
                                },
                                LinkedHashMap::new
                        )
                );
    }

    private void countAllOccursInMap(Map<Person, Integer> personsDataInfo) {
        persons.forEach(
                person -> {
                    if (personsDataInfo.containsKey(person)) {
                        int numberOfDuplicates = personsDataInfo.get(person);
                        numberOfDuplicates++;
                        personsDataInfo.put(
                                person,
                                numberOfDuplicates
                        );
                    }
                }
        );
    }

    private Map<Person, Integer> getAllUniqueOccurs() {
        Map<Person, Integer> result = new HashMap<>();
        persons.forEach(
                person -> {
                    if (!result.containsKey(person)) {
                        result.put(person, 0);
                    }
                }
        );
        return result;
    }

    private boolean checkPersonsSurname(Person person, String regex) {
        return person.getSurname().matches(regex);
    }
}
