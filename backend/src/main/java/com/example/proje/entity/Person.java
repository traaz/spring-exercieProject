package com.example.proje.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "phone_Number")
    private String phoneNumber;


    @ManyToOne()
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne()
    @JoinColumn(name = "state_id")
    private State state;
}
