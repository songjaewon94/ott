package com.dopamine.ott.user.svc;


import com.dopamine.ott.user.dto.req.UserSignUpReq;
import com.dopamine.ott.user.entity.User;
import com.dopamine.ott.user.repository.UserRepository;
import com.dopamine.ott.user.repository.VerifiedUserRepository;

import com.dopamine.ott.user.dto.req.UserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final VerifiedUserRepository verifiedUserRepository;


    public boolean validateUser(String name, String regNo) {
        return verifiedUserRepository.existsByNameAndRegNo(name,regNo);
    }

    public boolean validateUser(UserReq userReq) {
        return userRepository.existsByUserIdAndPassword(userReq.getUserId(),userReq.getPassword());
    }
    public void insertUser(UserSignUpReq userSignUpReq) {
        User user = new User();
        user.setUserId(userSignUpReq.getUserId());
        user.setName(userSignUpReq.getName());
        user.setPassword(userSignUpReq.getPassword());
        user.setRegNo(userSignUpReq.getRegNo());
        userRepository.save(user);
    }

    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
