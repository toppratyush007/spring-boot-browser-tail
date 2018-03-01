package com.pratyush.learning.sselogs.service;

import com.pratyush.learning.sselogs.repo.SseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Created by pratyush.k on 24/02/18.
 */
@Service
public class RegistrationService {
    @Autowired
    SseRepository sseRepository;

    public SseEmitter registerNodeAndReturnEmitter(String fileName) {
        return sseRepository.getSseEmitter(fileName);
    }
}
