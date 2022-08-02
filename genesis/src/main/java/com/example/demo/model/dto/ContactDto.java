package com.example.demo.model.dto;

import com.example.demo.model.Address;
import com.example.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

    private Long id;
    private String firstname;
    private String lastname;
    private Address address;
    private Status status;
    private String vat;
}
