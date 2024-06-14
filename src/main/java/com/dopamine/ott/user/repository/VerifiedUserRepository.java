package com.dopamine.ott.user.repository;

import com.dopamine.ott.user.entity.VerifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifiedUserRepository extends JpaRepository<VerifiedUser, Long> {
    boolean existsByNameAndRegNo(String name, String regNo);

}
