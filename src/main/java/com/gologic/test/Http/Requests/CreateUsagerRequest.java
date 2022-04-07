package com.gologic.test.Http.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateUsagerRequest {

    private String name;
    private String email;
    private String phone;
    private int credit;

}
