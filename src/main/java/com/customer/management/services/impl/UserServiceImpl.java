package com.customer.management.services.impl;

import com.customer.management.dtos.MetricsDTO;
import com.customer.management.dtos.UserWithEstimatedDeathDateResponseDTO;
import com.customer.management.entities.User;
import com.customer.management.repositories.UserRepository;
import com.customer.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public MetricsDTO getMetrics() {
        return userRepository.getMetrics();
    }

    @Override
    public List<UserWithEstimatedDeathDateResponseDTO> listUserWithEstimatedDeathDateResponseDTO(String lifeExpectancy) {
        return userRepository.listUserWithEstimatedDeathDate(lifeExpectancy);
    }
}
