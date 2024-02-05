package com.example.proje.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "states")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class State {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;


    @ManyToOne()
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "state")
    @JsonIgnore
    private List<Person> people;


}
