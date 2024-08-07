package com.dopamine.ott.contents.connector;

public interface ContentWebClientFactory {


    String getContentList();

    String getContentDetailInfo(String movieCd);
}
