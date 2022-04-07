package com.gologic.test.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

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
}
