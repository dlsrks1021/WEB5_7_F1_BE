package io.f1.backend.domain.stat.dao;

import io.f1.backend.domain.stat.dto.StatPageResponse;
import io.f1.backend.domain.user.dto.MyPageInfo;

import org.springframework.data.domain.Pageable;

public interface StatRepository {

    StatPageResponse getRanks(Pageable pageable);

    StatPageResponse getRanksByNickname(String nickname, int pageSize);

    MyPageInfo getMyPageByUserId(long userId);
}
