package com.security.repository;

import com.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
     UserEntity findByUsername(String username);

     UserEntity findByEmail(String email);
     // find by username or email
     @Query("SELECT u FROM UserEntity u WHERE u.username = :username OR u.email = :username")
     UserEntity findByUsernameOrEmail(String username); // custom query to find user by username or email
}
