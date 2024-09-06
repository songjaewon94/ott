package com.dopamine.ott.user.connector;

import com.dopamine.ott.user.dto.UserInfo;

public interface SnsLoginWebClientFactory {


    String getUserInfo(String code);
    String getAccessToken(String code);
}
