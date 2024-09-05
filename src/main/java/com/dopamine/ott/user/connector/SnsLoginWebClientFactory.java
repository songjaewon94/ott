package com.dopamine.ott.user.connector;

import com.dopamine.ott.user.dto.UserInfo;

public interface SnsLoginWebClientFactory {


    UserInfo getUserInfo(String code);
    String getAccessToken(String code);
}
