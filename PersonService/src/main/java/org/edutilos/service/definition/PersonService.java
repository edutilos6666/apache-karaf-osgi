package org.edutilos.service.definition;

import org.edutilos.model.Person;

import java.util.List;

/**
 * Created by Nijat Aghayev on 13.03.20.
 */
public interface PersonService {
    void connect();
    void disconnect();
    void create(Person person);
    void update(Person newPerson);
    void delete(String id);
    Person findOne(String id);
    List<Person> findAll();
}
