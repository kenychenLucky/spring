package com.kenychen.test.test;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Size(min = 3, max = 20)
    private String name;

    // standard getters and setters, constructors
}
