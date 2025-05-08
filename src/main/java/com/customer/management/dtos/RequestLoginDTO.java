package com.customer.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginDTO {

    private String idIdent;
    private String password;
    private String fullName;
    private Integer age;
    private String birthdate;
    private String role;
}