package com.ravi.cruddemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findByLastNameNative",
                query = "select * from employee where last_name = :lastName",
                resultClass = Employee.class
        )
})

@NamedQueries({
        @NamedQuery(
                name = "findByEmail",
                query = "select e from Employee e where e.email = :email"
        )
})
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
