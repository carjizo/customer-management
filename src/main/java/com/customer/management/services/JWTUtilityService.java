package com.customer.management.services;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

public interface JWTUtilityService {

    public String generateJWT(Long subject) throws JOSEException, ParseException, IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    public String generateRefreshJWT(Long subject) throws JOSEException, ParseException, IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    public JWTClaimsSet parseJWT(String jwt) throws ParseException, JOSEException, IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    public boolean verifyJWT(String jwt) throws JOSEException, IOException, org.springframework.expression.ParseException, NoSuchAlgorithmException, InvalidKeySpecException, ParseException;
}
