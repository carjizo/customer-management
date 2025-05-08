package com.customer.management.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String idIdent;
    private String fullName;
    private Integer age;
    private String birthdate;
    private String role;
    private boolean status;
}
