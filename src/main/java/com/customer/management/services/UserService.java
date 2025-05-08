package com.customer.management.services;

import com.customer.management.dtos.MetricsDTO;
import com.customer.management.dtos.UserWithEstimatedDeathDateResponseDTO;
import com.customer.management.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    MetricsDTO getMetrics();
    List<UserWithEstimatedDeathDateResponseDTO> listUserWithEstimatedDeathDateResponseDTO(String lifeExpectancy);
}