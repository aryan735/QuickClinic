package com.quickclinic.userauth.repository;

import com.quickclinic.userauth.dto.UserResponseDto;
import com.quickclinic.userauth.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    @Query("SELECT new com.quickclinic.userauth.dto.UserResponseDto(u.id,u.username,u.email,u.age) FROM UserModel u WHERE u.username = :username")
    UserResponseDto getUserDetailsByUsername(@Param("username") String username);

    boolean existsByUsername(String username);
    @Query("SELECT u FROM UserModel u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    UserModel findByUsername(@Param("username") String username);
}
