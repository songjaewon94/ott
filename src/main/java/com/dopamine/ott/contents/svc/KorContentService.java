package com.dopamine.ott.contents.svc;

import com.dopamine.ott.contents.connector.KorContentApiClientContent;
import com.dopamine.ott.user.connector.KaKaoApiClientContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KorContentService implements ContentsService{

    private final KorContentApiClientContent koreanContentWebclientFactory;

    @Override
    public String getContentServiceList() {
        return koreanContentWebclientFactory.getContentList();
    }

    @Override
    public String getContentDetail(String movieCd) {
        return koreanContentWebclientFactory.getContentDetailInfo(movieCd);
    }
}
