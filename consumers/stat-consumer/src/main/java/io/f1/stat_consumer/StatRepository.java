package io.f1.stat_consumer;

import io.f1.events.StatChangeEvent;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatRepository {

    private final NamedParameterJdbcTemplate template;

    public void updateStatChanges(List<StatChangeEvent> events) {
        String sql =
                """
                UPDATE stat
                SET score = score + :deltaScore,
                    total_games = total_games + 1,
                    winning_games = winning_games + (CASE WHEN :win THEN 1 ELSE 0 END)
                WHERE userId = :userId
                """;

        template.batchUpdate(sql, SqlParameterSourceUtils.createBatch(events));
    }
}
