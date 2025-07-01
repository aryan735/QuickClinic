package com.quickclinic.userauth.repository;

import com.quickclinic.userauth.dto.UserResponseDto;
import com.quickclinic.userauth.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    @Query("SELECT new com.quickclinic.userauth.dto.UserResponseDto(u.id,u.username,u.email,u.age) FROM UserModel u WHERE u.username = :username")
    UserResponseDto getUserDetailsByUsername(@Param("username") String username);

    boolean existsByUsername(String username);
    @Query("SELECT u FROM UserModel u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    UserModel findByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.username=:username,u.email=:email,u.age=:age WHERE u.id=:id")
    int updateUserDetails(@Param("username") String username,
                           @Param("email") String email,
                           @Param("age") int age,
                           @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserModel u WHERE u.username=:username")
    int deleteByUsername(@Param("username") String username);

}
