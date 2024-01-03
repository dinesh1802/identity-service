package com.ekart.identityservice.service;

import com.ekart.identityservice.entity.UserDetail;
import com.ekart.identityservice.exception.IdentityServiceException;
import com.ekart.identityservice.repository.UserDetailRepository;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ekart.identityservice.util.Constants.SUCCESS;
import static com.ekart.identityservice.util.Constants.UPDATE_SUCCESS;

@Service
public class IdentityService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserDetail userDetail) throws IdentityServiceException {
        userDetail.setPassword(passwordEncoder.encode(userDetail.getPassword()));
        try {
            userDetailRepository.save(userDetail);
        }catch (DataIntegrityViolationException ex){
            throw new IdentityServiceException("User Name Already exists");
        }
        return SUCCESS;
    }



    public String generateToken(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

    public void validateToken(String token) throws IdentityServiceException {
        try {
            jwtService.validateToken(token);
        }catch (Exception ex){
            throw new IdentityServiceException("Token is Invalid");
        }
    }


    public String updateUser(UserDetail userDetail) throws IdentityServiceException {
        userDetail.setPassword(passwordEncoder.encode(userDetail.getPassword()));
        try {
            userDetailRepository.save(userDetail);
        }catch (DataIntegrityViolationException ex){
            throw new IdentityServiceException("User Name Already exists");
        }
        return UPDATE_SUCCESS;
    }
}
