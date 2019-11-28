package com.goya.interview.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = {"id","name","dateOfBirth","city"})
public class User {
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    private City city;
}
