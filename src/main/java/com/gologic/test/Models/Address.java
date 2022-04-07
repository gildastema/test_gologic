package com.gologic.test.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String street;
    private String city;
    private String country;
    private String province;
    @Column(name = "postal_code")
    private String postalCode;

    public Address(String street, String city, String country, String province, String postalCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.province = province;
        this.postalCode = postalCode;
    }
}


