package org.edutilos.service.definition;

import junit.framework.TestCase;
import org.edutilos.model.Person;
import org.edutilos.service.impl.PersonServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nijat Aghayev on 13.03.20.
 */
public class PersonServiceTest extends TestCase {
    private PersonService personService;
    @BeforeClass
    public void setUp() {
        personService = new PersonServiceImpl();
    }
    @AfterClass
    public void tearDown() {
        personService.disconnect();
    }

    @Test
    public void test1() {
        List<Person> personList = Arrays.asList(
                new Person("1", "foo", "bar", 10, 100.0, true),
                new Person("2", "foo 2", "bar 2", 20, 200.0, false),
                new Person("3", "foo 3", "bar 3", 30, 300.0, true)
        );

        personList.forEach(one-> {
            personService.create(one);
        });

        assertEquals("Size must be 3", 3, personList.size());

        Person one = personService.findOne("1");
        assertEquals("Fname should be foo","foo", one.getFname());
        assertEquals("single should be true", true, one.isSingle());
        assertEquals("Age should be 10", 10, one.getAge());

        personService.update(one.withFname("new foo").withAge(66).withSalary(666.6));
        one = personService.findOne("1");
        assertEquals("Fname should be new foo","new foo", one.getFname());
        assertEquals("single should be true", true, one.isSingle());
        assertEquals("Age should be 66", 66, one.getAge());
        assertEquals("Salary should be around 666.6", 666.6, one.getSalary(), 0.1);

        personService.delete("1");
        personService.delete("2");
        personService.delete("3");

        personList = personService.findAll();
        assertEquals("personList should be empty", true, personList.isEmpty());
    }
}
