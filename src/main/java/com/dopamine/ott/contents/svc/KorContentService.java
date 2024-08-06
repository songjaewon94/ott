package com.dopamine.ott.contents.svc;

import com.dopamine.ott.user.connector.KaKaoApiClientContent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KorContentService implements ContentsService{

    private final KaKaoApiClientContent koreanContentWebclientFactory;

    @Override
    public String getContentServiceList() {

        return "";
    }
}
