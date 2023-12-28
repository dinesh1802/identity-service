package com.ekart.identityservice.repository;

import com.ekart.identityservice.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail,Integer> {
    public Optional<UserDetail> findByUserName(String userName);
}
