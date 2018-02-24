package com.pratyush.learning.sselogs.service;

import com.pratyush.learning.sselogs.repo.SseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.integration.file.tail.ApacheCommonsFileTailingMessageProducer;
import org.springframework.integration.file.tail.FileTailingMessageProducerSupport;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.io.IOException;

/**
 * Created by pratyush.k on 24/02/18.
 */
@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class FileReader {

    @Value("${file.name}")
    String fileName;

    @Autowired
    SseRepository sseRepository;

    ApacheCommonsFileTailingMessageProducer apacheCommonsFileTailingMessageProducer;

    public FileReader(){
        apacheCommonsFileTailingMessageProducer = new ApacheCommonsFileTailingMessageProducer();
        apacheCommonsFileTailingMessageProducer.setFile(new File("./files/test.txt"));
        apacheCommonsFileTailingMessageProducer.setOutputChannel(new MessageChannel() {
            @Override
            public boolean send(Message<?> message) {
                log.info("sent message {}",message.toString());
                try {
                    log.info("clients to be notified {}",sseRepository.getSseRepo().size());
                    for(SseEmitter sseEmitter: sseRepository.getSseRepo())
                    {
                        String payload = message.getPayload().toString();
                        sseEmitter.send(payload);
                    }
                } catch (IOException e) {
                    log.error("could not send the message with exception {}",e);
                }
                return true;
            }

            @Override
            public boolean send(Message<?> message, long l) {
                return false;
            }
        });

        apacheCommonsFileTailingMessageProducer.afterPropertiesSet();
        apacheCommonsFileTailingMessageProducer.start();

    }

}
