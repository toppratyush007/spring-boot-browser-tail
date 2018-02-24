package com.pratyush.learning.sselogs.repo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pratyush.k on 24/02/18.
 */
@Data
@Repository
@PropertySource("classpath:application.properties")
public class SseRepository {
    @Value("${timeout.interval}")
    Long timeoutInterval;
    private List<SseEmitter> sseRepo = new ArrayList<>();

    public SseEmitter getSseEmitter(){
        SseEmitter sseEmitter = sseEmitter();
        sseRepo.add(sseEmitter);
        sseEmitter.onCompletion(()->sseRepo.remove(sseEmitter));
        sseEmitter.onTimeout(()->sseRepo.remove(sseEmitter));
        return sseEmitter;
    }

    private SseEmitter sseEmitter(){
        return new SseEmitter(timeoutInterval);
    }
}
