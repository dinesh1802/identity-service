package com.ekart.identityservice.controller;

import com.ekart.identityservice.dto.AuthRequest;
import com.ekart.identityservice.entity.UserDetail;
import com.ekart.identityservice.exception.IdentityServiceException;
import com.ekart.identityservice.exception.InvalidAccessException;
import com.ekart.identityservice.service.IdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.ekart.identityservice.util.Constants.VALID_TOKEN;

@RestController
@RequestMapping("/auth")
public class IdentityController {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * method to add new user
     * @param userDetail
     * @return Success message
     */
    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid UserDetail userDetail) throws IdentityServiceException {
        return identityService.saveUser(userDetail);
    }




    /**
     * method to get the jwt token
     * @param authRequest
     * @return token
     */
    @PostMapping("/getToken")
    public String getToken(@RequestBody AuthRequest authRequest) throws InvalidAccessException {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                return identityService.generateToken(authenticate);
            } else {
                throw new InvalidAccessException("Access Denied, You do not have proper access to get Token");
            }
        }catch (Exception ex){
            throw new InvalidAccessException("Access Denied, You do not have proper access to get Token");
        }
    }


    /**
     * method to validate the token
     * @param token
     * @return validation message
     */
    @GetMapping("/validateToken")
    public String validateToken(@RequestParam("token") String token) throws IdentityServiceException {
        identityService.validateToken(token);
        return VALID_TOKEN;
    }


    /**
     * method to add new user
     * @param userDetail
     * @return Success message
     */
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody @Valid UserDetail userDetail) throws IdentityServiceException {
        return identityService.updateUser(userDetail);
    }
}
