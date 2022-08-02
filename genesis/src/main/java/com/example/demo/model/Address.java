package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Country may not be empty")
    @Size(min = 2, max = 255, message = "Country must be at least 2 characters long")
    private String country;
    @NotEmpty(message = "city may not be empty")
    @Size(min = 2, max = 255, message = "city must be at least 2 characters long")
    private String city;
    @NotEmpty(message = "street may not be empty")
    @Size(min = 2, max = 255, message = "street must be at least 2 characters long")
    private String street;
    @NotEmpty(message = "postalCode may not be empty")
    @Size(min = 2, max = 32, message = "postalCode must be at least 2 characters long")
    private String postalCode;
    @NotEmpty(message = "House number may not be empty")
    @Size(min = 1, message = "House number must be at least 1 character long")
    private String houseNumber;

}
