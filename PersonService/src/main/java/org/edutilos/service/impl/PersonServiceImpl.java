package org.edutilos.service.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.edutilos.model.Person;
import org.edutilos.service.definition.PersonService;
import org.edutilos.utils.PersonDocumentMapper;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nijat Aghayev on 13.03.20.
 */
public class PersonServiceImpl implements PersonService, BundleActivator {
//    private ServiceReference<PersonService> reference;
    private ServiceRegistration<PersonService> registration;
    private MongoClient mongoClient;
    private MongoDatabase personDB;
    private MongoCollection<Document> personCollection;
    private PersonDocumentMapper personDocumentMapper;


    public void connect() {
        mongoClient = MongoClients.create();
        personDB = mongoClient.getDatabase("osgi-karaf");
        personCollection = personDB.getCollection("person");
        personDocumentMapper = new PersonDocumentMapper();
    }

    public PersonServiceImpl() {
        connect();
    }

    public void disconnect() {
        // for testing
        personDB.drop();
        mongoClient.close();
    }
    public void create(Person person) {
        personCollection.insertOne(personDocumentMapper.mapPersonToDocument(person));
    }

    public void update(Person newPerson) {
        personCollection.replaceOne(Filters.eq("_id", newPerson.getId()),
                personDocumentMapper.mapPersonToDocument(newPerson));
    }

    public void delete(String id) {
        personCollection.deleteOne(Filters.eq("_id", id));
    }

    public Person findOne(String id) {
        List<Document> ret = personCollection.find(Filters.eq("_id", id)).into(new ArrayList<Document>());
        return ret.size() > 0? personDocumentMapper.mapDocumentToPerson(ret.get(0)):null;
    }

    public List<Person> findAll() {
        return personCollection.find().into(new ArrayList<Document>())
                .stream()
                .map(one-> personDocumentMapper.mapDocumentToPerson(one))
                .collect(Collectors.toList());
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Registering service.");
        registration = bundleContext.registerService(PersonService.class, new PersonServiceImpl(), new Hashtable<String, String>());
//        reference = registration.getReference();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Unregistering service.");
        disconnect();
        registration.unregister();
    }
}
