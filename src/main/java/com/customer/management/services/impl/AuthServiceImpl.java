package com.customer.management.services.impl;

import com.nimbusds.jwt.JWTClaimsSet;
import com.customer.management.dtos.RequestLoginDTO;
import com.customer.management.entities.User;
import com.customer.management.repositories.UserRepository;
import com.customer.management.services.AuthService;
import com.customer.management.services.JWTUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtilityService jwtUtilityService;

    @Override
    public HashMap<String, Object> login(RequestLoginDTO requestLoginDTO) throws Exception {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Optional<User> user = userRepository.findByIdIdent(requestLoginDTO.getIdIdent());
            if (user.isEmpty()) {
                response.put("message", "User not registered");
                response.put("isSucces", false);
                return response;
            }
            if (verifyPassword(requestLoginDTO.getPassword(), user.get().getPassword())) {
                response.put("message", "Authentication succes");
                response.put("isSucces", true);
                response.put("token", jwtUtilityService.generateJWT(user.get().getIdUser()));
                response.put("refreshToken", jwtUtilityService.generateRefreshJWT(user.get().getIdUser()));
            } else {
                response.put("message", "Authentication failed");
                response.put("isSucces", false);
            }
            return response;
        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
//            throw new Exception("Error generating JWT", e);
            response.put("message", "Error generating JWT");
            response.put("isSucces", false);
            return response;
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
//            throw new Exception("Unknown error", e);
            response.put("message", "Error generating JWT");
            response.put("isSucces", false);
            return response;
        }
    }

    @Override
    public HashMap<String, Object> register(RequestLoginDTO requestLoginDTO) throws Exception{
        User user = User.builder()
                .idIdent(requestLoginDTO.getIdIdent())
                .password(requestLoginDTO.getPassword())
                .fullName(requestLoginDTO.getFullName())
                .age(requestLoginDTO.getAge())
                .birthdate(requestLoginDTO.getBirthdate())
                .role(requestLoginDTO.getRole())
                .status(true)
                .build();

        HashMap<String, Object> response = new HashMap<>();

        try {
            List<User> getAllUsers = (List<User>) userRepository.findAll();

            for (User existingUser : getAllUsers) {
                if (existingUser.getIdIdent().equals(user.getIdIdent())) {
                    response.put("message", "Identity already exists");
                    response.put("isSucces", false);
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.put("message", "User created successfully");
            response.put("isSucces", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Error Exception");
            response.put("isSucces", false);
            System.out.println("Error Exception: " + e.getMessage());
//            throw new Exception(e.getMessage());
            return response;
        }
    }

    @Override
    public HashMap<String, Object> verifyToken(String jwt) throws Exception {
        HashMap<String, Object> response = new HashMap<>();
        try {
            response.put("message", "Authentication succes");
            response.put("isSucces", jwtUtilityService.verifyJWT(jwt));
            return response;
        } catch (Exception e) {
            response.put("message", "Authentication failed");
            response.put("isSucces", false);
            return response;
        }
    }

    @Override
    public HashMap<String, Object> refreshToken(String jwt) throws Exception {
        HashMap<String, Object> response = new HashMap<>();
        try {
            boolean verifyJWT = jwtUtilityService.verifyJWT(jwt);
            if (!verifyJWT) {
                response.put("message", "Authentication failed");
                response.put("isSucces", false);
                return response;
            }
            JWTClaimsSet parseJWT = jwtUtilityService.parseJWT(jwt);
            String idUser = parseJWT.getSubject();
            response.put("isSucces", true);
            response.put("message", "Authentication succes");
            response.put("token", jwtUtilityService.generateJWT(Long.valueOf(idUser)));
            return response;
        } catch (Exception e) {
            response.put("message", "Authentication failed");
            response.put("isSucces", false);
            return response;
        }
    }


    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
