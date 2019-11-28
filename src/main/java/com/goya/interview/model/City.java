package com.goya.interview.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = {"id","name","country"})
public class City {
    private Integer id;
    private String name;
    private Country country;
}
