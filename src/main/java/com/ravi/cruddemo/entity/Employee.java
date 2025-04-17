package com.ravi.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")

@NamedQueries({
        @NamedQuery(name = "findByFirstName", query = "from Employee e where e.firstName = :firstName"),
        @NamedQuery(name = "findByLastName", query = "from Employee e where e.lastName = :lastName")
})

@NamedNativeQueries({
        @NamedNativeQuery(name = "findByEmailNative", query = "select * from employee e where e.email = :email", resultClass = Employee.class),
        @NamedNativeQuery(name = "findByFirstNameAndEmailNative", query = "select * from employee e where e.first_name = :firstName and e.email = :email", resultClass = Employee.class),
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Employee(){

    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
