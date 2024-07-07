package com.dopamine.ott.user.controller;


import com.dopamine.ott.common.util.JwtUtil;
import com.dopamine.ott.user.dto.req.UserReq;
import com.dopamine.ott.user.dto.req.UserSignUpReq;
import com.dopamine.ott.user.dto.res.LoginRes;
import com.dopamine.ott.user.svc.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserSignUpReq user){
        boolean isValid = userService.validateUser(user.getName(), user.getRegNo());

        if (isValid) {
            userService.insertUser(user);
            return ResponseEntity.ok("Sign up successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserReq userReq){

        try {
            boolean isValid = userService.validateUser(userReq);
            if (isValid) {
                String token = jwtUtil.generateToken(userReq.getUserId());
                return ResponseEntity.ok(new LoginRes(token));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

    }


}
