package com.pratyush.learning.sselogs.controller;

import com.pratyush.learning.sselogs.exception.APIException;
import com.pratyush.learning.sselogs.helper.SseHelper;
import com.pratyush.learning.sselogs.model.APIResponse;
import com.pratyush.learning.sselogs.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pratyush.k on 24/02/18.
 */
@Slf4j
@RestController
@PropertySource("classpath:application.properties")
public class GetController {

    @Value("${timeout.interval}")
    Long timeoutInterval;

    @Autowired
    RegistrationService registrationService;


    @RequestMapping("/logs")
    SseEmitter getLogs(@RequestParam("name") String fileName){

        log.info("file requested for is {}", fileName);
        try {
            return registrationService.registerNodeAndReturnEmitter(fileName);
        }catch (APIException apiEx){
            SseEmitter sseEmitter = SseHelper.sseEmitter(timeoutInterval);
            try {
                sseEmitter.send(new APIResponse(apiEx));
                sseEmitter.complete();
            } catch (IOException e) {
                log.error("could not send logs over network {}",e);
            }
            return sseEmitter;
        }catch (Exception ex){
            SseEmitter sseEmitter = SseHelper.sseEmitter(timeoutInterval);
            log.error("error pushing to client.");
            try {
                sseEmitter.send(new APIResponse("Error pushing response", HttpStatus.INTERNAL_SERVER_ERROR.value()));
            } catch (IOException e) {
                log.error("could not send logs over network {}",e);
            }
            sseEmitter.complete();
            return sseEmitter;
        }
    }
}
