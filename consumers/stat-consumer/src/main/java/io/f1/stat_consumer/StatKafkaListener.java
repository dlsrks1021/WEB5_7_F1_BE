package io.f1.stat_consumer;

import io.f1.events.StatChangeEvent;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatKafkaListener {

    private final StatRepository repository;

    @Transactional
    @KafkaListener(topics = "stat-changes")
    public void handleStatChanges(List<StatChangeEvent> events) {
        repository.updateStatChanges(events);
    }
}
