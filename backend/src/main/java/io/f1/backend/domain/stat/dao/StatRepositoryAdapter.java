package io.f1.backend.domain.stat.dao;

import static io.f1.backend.domain.stat.mapper.StatMapper.toStatListPageResponse;

import io.f1.backend.domain.stat.dto.StatPageResponse;
import io.f1.backend.domain.stat.dto.StatWithNickname;
import io.f1.backend.domain.stat.dto.StatWithUserSummary;
import io.f1.backend.domain.user.dto.MyPageInfo;
import io.f1.backend.global.exception.CustomException;
import io.f1.backend.global.exception.errorcode.RoomErrorCode;
import io.f1.backend.global.exception.errorcode.UserErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StatRepositoryAdapter implements StatRepository {

    private final StatJpaRepository jpaRepository;

    @Override
    public StatPageResponse getRanks(Pageable pageable) {
        Page<StatWithNickname> stats = jpaRepository.findAllStatsWithUser(pageable);
        return toStatListPageResponse(stats);
    }

    @Override
    public StatPageResponse getRanksByNickname(String nickname, int pageSize) {
        Pageable pageable = getPageableFromNickname(nickname, pageSize);
        return getRanks(pageable);
    }

    @Override
    public void updateRank(long userId, boolean win, int deltaScore) {
        if (win) {
            jpaRepository.updateStatByUserIdCaseWin(deltaScore, userId);
        } else {
            jpaRepository.updateStatByUserIdCaseLose(deltaScore, userId);
        }
    }

    @Override
    public void addUser(long userId, String nickname) {
        // TODO: 서비스 분할 시 UserService로부터 받을 정보
    }

    @Override
    public void updateNickname(long userId, String nickname) {
        // TODO: 서비스 분할 시 UserService로부터 받을 정보
    }

    @Override
    public void removeUser(long userId) {
        // TODO: 서비스 분할 시 UserService로부터 받을 정보
    }

    private Pageable getPageableFromNickname(String nickname, int pageSize) {
        long score =
                jpaRepository
                        .findScoreByNickname(nickname)
                        .orElseThrow(() -> new CustomException(RoomErrorCode.PLAYER_NOT_FOUND));

        long rowNum = jpaRepository.countByScoreGreaterThan(score);

        int pageNumber = rowNum > 0 ? (int) (rowNum / pageSize) : 0;
        return PageRequest.of(pageNumber, pageSize, Sort.by(Direction.DESC, "score"));
    }

    @Override
    public MyPageInfo getMyPageByUserId(long userId) {
        StatWithUserSummary stat = findStatByUserId(userId);
        long rank = jpaRepository.countByScoreGreaterThan(stat.score()) + 1;

        return new MyPageInfo(
                stat.nickname(), rank, stat.score(), stat.totalGames(), stat.winningGames());
    }

    private StatWithUserSummary findStatByUserId(long userId) {
        return jpaRepository
                .findStatWithUserSummary(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
    }
}
