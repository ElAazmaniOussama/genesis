package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Firstname may not be empty")
    @Size(min = 2, max = 32, message = "Firstname must be at least 2 characters long")
    private String firstname;
    @NotEmpty(message = "Lastname may not be empty")
    @Size(min = 2, max = 32, message = "Lastname must be at least 2 characters long")
    private String lastname;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String vat;
    private boolean archived;


}
