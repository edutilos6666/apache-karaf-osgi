package org.edutilos.utils;

import org.bson.Document;
import org.edutilos.model.Person;

/**
 * Created by Nijat Aghayev on 13.03.20.
 */
public class PersonDocumentMapper {
    public Document mapPersonToDocument(Person person) {
        return new Document()
                .append("_id", person.getId())
                .append("fname", person.getFname())
                .append("lname", person.getLname())
                .append("age", person.getAge())
                .append("salary", person.getSalary())
                .append("single", person.isSingle());
    }

    public Person mapDocumentToPerson(Document document) {
        return new Person()
                .withId(document.get("_id", String.class))
                .withFname(document.get("fname", String.class))
                .withLname(document.get("lname", String.class))
                .withAge(document.get("age", Integer.class))
                .withSalary(document.get("salary", Double.class))
                .withSingle(document.get("single", Boolean.class));
    }
}
