package com.customer.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWithEstimatedDeathDateResponseDTO {

    private String idIdent;
    private String fullName;
    private Integer age;
    private String birthdate;
    private String estimatedDeathDate;
}