package com.dataquadinc.repository;


import com.dataquadinc.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDao extends JpaRepository<UserDetails, Long> {
    UserDetails findByEmail(String email) ;

}
