package org.example.persons;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PersonServiceImplTest {
    private PersonServiceImpl personService;

    @Before
    public void doBefore() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Max", "Ryb", "Al", 25, false, LocalDate.of(1996, Month.MARCH, 4)));
        personList.add(new Person("Ivan", "Ivanov", "Al", 19, false, LocalDate.of(2002, Month.JANUARY, 1)));
        personList.add(new Person("Michael", "Ryb", "Al", 15, false, LocalDate.of(2005, Month.APRIL, 3)));
        personList.add(new Person("Max", "Igorkin", "Al", 30, false, LocalDate.of(1986, Month.MARCH, 4)));
        personList.add(new Person("Иван", "Иванов", "Владимирович", 10, false, LocalDate.of(2011, Month.MARCH, 4)));
        personList.add(new Person("Ольга", "Малахова", "Игоревна", 15, true, LocalDate.of(2006, Month.MARCH, 4)));
        personService = new PersonServiceImpl(personList);
    }

    @Test
    public void whenFoundPersonThenTrue() {
        Person person = new Person("Ivan", "Ivanov", "Al", 19, false, LocalDate.of(2002, Month.JANUARY, 1));
        assertTrue(personService.hasCopy(person));
    }

    @Test
    public void whenNotFoundPersonThenFalse() {
        Person person = new Person("Ivan", "Malachov", "Al", 19, false, LocalDate.of(1, Month.JANUARY, 1));
        assertFalse(personService.hasCopy(person));
    }

    @Test
    public void whenSearchByFilters() {
        List<Person> result = personService.selectByFilters(20, "i");
        List<Person> expected = new ArrayList<>();
        expected.add(new Person("Ivan", "Ivanov", "Al", 19, false, LocalDate.of(2002, Month.JANUARY, 1)));
        assertEquals(expected, result);
    }

    @Test
    public void whenSearchByFiltersAndManyLetters() {
        List<Person> result = personService.selectByFilters(20, "i", "R");
        List<Person> expected = new ArrayList<>();
        expected.add(new Person("Ivan", "Ivanov", "Al", 19, false, LocalDate.of(2002, Month.JANUARY, 1)));
        expected.add(new Person("Michael", "Ryb", "Al", 15, false, LocalDate.of(2005, Month.APRIL, 3)));
        assertEquals(expected, result);
    }

    @Test
    public void whenSearchByFiltersAndManyRussianLetters() {
        List<Person> result = personService.selectByFilters(20, "м", "и");
        List<Person> expected = new ArrayList<>();
        expected.add(new Person("Иван", "Иванов", "Владимирович", 10, false, LocalDate.of(2011, Month.MARCH, 4)));
        expected.add(new Person("Ольга", "Малахова", "Игоревна", 15, true, LocalDate.of(2006, Month.MARCH, 4)));
        assertEquals(2, result.size());
        assertEquals(expected, result);
    }

    @Test
    public void whenDidNotFindFilters() {
        List<Person> result = personService.selectByFilters(10, "I", "D");
        List expected = Collections.EMPTY_LIST;
        assertEquals(expected, result);
    }

    @Test
    public void whenGetDuplicates() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Max", "Ryb", "Al", 25, false, LocalDate.of(1996, Month.MARCH, 4)));
        personList.add(new Person("Max", "Ryb", "Al", 25, false, LocalDate.of(1996, Month.MARCH, 4)));
        personList.add(new Person("Ivan", "Ivanov", "Al", 19, false, LocalDate.of(2002, Month.JANUARY, 1)));
        personList.add(new Person("Michael", "Ryb", "Al", 15, false, LocalDate.of(2005, Month.APRIL, 3)));
        personList.add(new Person("Michael", "Ryb", "Al", 15, false, LocalDate.of(2005, Month.APRIL, 3)));
        personList.add(new Person("Michael", "Ryb", "Al", 15, false, LocalDate.of(2005, Month.APRIL, 3)));
        personList.add(new Person("Max", "Igorkin", "Al", 30, false, LocalDate.of(1986, Month.MARCH, 4)));
        personList.add(new Person("Иван", "Иванов", "Владимирович", 10, false, LocalDate.of(2011, Month.MARCH, 4)));
        personList.add(new Person("John", "Smith", "", 40, false, LocalDate.of(1981, Month.MARCH, 4)));
        personList.add(new Person("Ольга", "Малахова", "Игоревна", 10, true, LocalDate.of(2006, Month.MARCH, 4)));
        personList.add(new Person("Ольга", "Малахова", "Игоревна", 10, true, LocalDate.of(2006, Month.MARCH, 4)));
        personService = new PersonServiceImpl(personList);
        String expected = "{2, Person{name='Max', surname='Ryb', age=25}}\n" +
                "{3, Person{name='Michael', surname='Ryb', age=15}}\n" +
                "{2, Person{name='Ольга', surname='Малахова', age=10}}\n";
        String result = personService.getDuplicates();
        assertEquals(expected, result);
    }

    @Test
    public void whenNoDuplicates() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Max", "Ryb", "Al", 25, false, LocalDate.of(1996, Month.MARCH, 4)));
        personList.add(new Person("Ivan", "Ivanov", "Al", 19, false, LocalDate.of(2002, Month.JANUARY, 1)));
        personList.add(new Person("Michael", "Ryb", "Al", 15, false, LocalDate.of(2005, Month.APRIL, 3)));
        personList.add(new Person("Max", "Igorkin", "Al", 30, false, LocalDate.of(1986, Month.MARCH, 4)));
        personList.add(new Person("Иван", "Иванов", "Владимирович", 10, false, LocalDate.of(2011, Month.MARCH, 4)));
        personList.add(new Person("John", "Smith", "", 40, false, LocalDate.of(1981, Month.MARCH, 4)));
        personList.add(new Person("Ольга", "Малахова", "Игоревна", 10, true, LocalDate.of(2006, Month.MARCH, 4)));
        personService = new PersonServiceImpl(personList);
        String expected = "";
        String result = personService.getDuplicates();
        assertEquals(expected, result);
    }
}