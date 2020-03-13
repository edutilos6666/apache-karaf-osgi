package org.edutilos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Nijat Aghayev on 13.03.20.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String id;
    private String fname;
    private String lname;
    private int age;
    private double salary;
    private boolean single;


    public Person withId(String id) {
        this.id = id;
        return this;
    }
    public Person withFname(String fname) {
        this.fname = fname;
        return this;
    }
    public Person withLname(String lname) {
        this.lname = lname;
        return this;
    }
    public Person withAge(int age) {
        this.age = age;
        return this;
    }
    public Person withSalary(double salary) {
        this.salary = salary;
        return this;
    }
    public Person withSingle(boolean single) {
        this.single = single;
        return this;
    }
}
