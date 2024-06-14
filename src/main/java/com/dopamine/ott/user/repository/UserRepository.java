package com.dopamine.ott.user.repository;

import com.dopamine.ott.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    boolean existsByUserIdAndPassword(String userId, String password);
    User findByUserId(String userId);
}