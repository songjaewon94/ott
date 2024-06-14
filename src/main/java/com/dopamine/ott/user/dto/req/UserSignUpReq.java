package com.dopamine.ott.user.dto.req;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpReq extends UserReq {
    @NotNull
    private String name;
    @NotNull
    private String regNo;
}
