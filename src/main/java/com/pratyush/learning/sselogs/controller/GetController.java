package com.pratyush.learning.sselogs.controller;

import com.pratyush.learning.sselogs.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pratyush.k on 24/02/18.
 */
@RestController
public class GetController {

    @Autowired
    RegistrationService registrationService;


    @RequestMapping("/logs")
    SseEmitter getLogs(){
        return registrationService.registerNodeAndReturnEmitter();
    }
}
