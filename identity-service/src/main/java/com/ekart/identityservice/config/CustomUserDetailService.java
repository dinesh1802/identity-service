package com.ekart.identityservice.config;

import com.ekart.identityservice.entity.UserDetail;
import com.ekart.identityservice.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService { //extends spring user detail class

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override //spring userDetail service override method
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserDetail> userDetail=userDetailRepository.findByUserName(userName);
        return userDetail.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + userName));
        //need to return UserDetail(spring userDetail ) , hence created new custom class
    }
}
