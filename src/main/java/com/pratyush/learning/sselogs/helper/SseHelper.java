package com.pratyush.learning.sselogs.helper;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Created by pratyush.k on 02/03/18.
 */
public class SseHelper {
    public static SseEmitter sseEmitter(long timeoutInterval){
        return new SseEmitter(timeoutInterval);
    }
}
