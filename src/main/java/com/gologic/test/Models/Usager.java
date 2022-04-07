package com.gologic.test.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usagers")
@RequiredArgsConstructor
@Data
public class Usager {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String phone;

    private int credit;

    @OneToMany()
    @JsonProperty("address")
    private List<Address> addresses = new ArrayList<>();

    public Usager(String name, String email, String phone, int credit) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.credit = credit;
    }
    public Usager(String name, String email, String phone, int credit, int id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.credit = credit;
        this.id = id;
    }

    public Usager(String name, String email, String phone, int credit, int id, List<Address>addresses) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.credit = credit;
        this.id = id;
        this.addresses = addresses;
    }
    public Usager(String name, String email, String phone, int credit, List<Address>addresses) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.credit = credit;
        this.addresses = addresses;
    }


}
