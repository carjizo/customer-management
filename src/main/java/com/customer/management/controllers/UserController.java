package com.customer.management.controllers;

import com.customer.management.dtos.MetricsDTO;
import com.customer.management.dtos.UserResponseDTO;
import com.customer.management.dtos.UserWithEstimatedDeathDateResponseDTO;
import com.customer.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    private ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> userDTOList = userService.findAll()
                .stream()
                .map(user -> UserResponseDTO.builder()
                        .idIdent(user.getIdIdent())
                        .fullName(user.getFullName())
                        .age(user.getAge())
                        .birthdate(user.getBirthdate())
                        .role(user.getRole())
                        .status(user.isStatus())
                        .build())
                .toList();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/metrics")
    private ResponseEntity<MetricsDTO> getMetrics(){
        MetricsDTO metricsDTO = userService.getMetrics();
        return new ResponseEntity<>(metricsDTO, HttpStatus.OK);
    }

    @GetMapping("/with-estimated-death-date/{lifeExpectancy}")
    private ResponseEntity<List<UserWithEstimatedDeathDateResponseDTO>> listUserWithEstimatedDeathDate(@PathVariable String lifeExpectancy){
        List<UserWithEstimatedDeathDateResponseDTO> userWithEstimatedDeathDateResponseDTO = userService.listUserWithEstimatedDeathDateResponseDTO(lifeExpectancy);
        return new ResponseEntity<>(userWithEstimatedDeathDateResponseDTO, HttpStatus.OK);
    }
}
