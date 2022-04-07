package com.gologic.test.Http.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gologic.test.Models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateUsagerRequest implements Serializable {

    private String name;
    private String email;
    private String phone;
    private int credit;
    @JsonProperty("address")
    private List<Address> addresses = new ArrayList<>();

    public CreateUsagerRequest(String name, String email, String phone, int credit) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.credit = credit;
    }
}
