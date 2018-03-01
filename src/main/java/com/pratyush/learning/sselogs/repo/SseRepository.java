package com.pratyush.learning.sselogs.repo;

import com.pratyush.learning.sselogs.helper.FileReadHelper;
import com.pratyush.learning.sselogs.helper.SseHelper;
import com.pratyush.learning.sselogs.service.FileReader;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pratyush.k on 24/02/18.
 */
@Data
@Repository
@PropertySource("classpath:application.properties")
public class SseRepository {
    @Value("${timeout.interval}")
    Long timeoutInterval;

    private Map<String, List<SseEmitter> > sseFileRepo = new HashMap<>();


    public SseEmitter getSseEmitter(String fileName){
        FileReadHelper.checkFile(fileName);
        if(!sseFileRepo.containsKey(fileName))
        {
            sseFileRepo.put(fileName, new ArrayList<>());
            new FileReader(fileName, this);
        }
        SseEmitter sseEmitter = SseHelper.sseEmitter(timeoutInterval);
        sseFileRepo.get(fileName).add(sseEmitter);
        sseEmitter.onCompletion(()->sseFileRepo.get(fileName).remove(sseEmitter));
        sseEmitter.onTimeout(()->sseFileRepo.get(fileName).remove(sseEmitter));
        return sseEmitter;
    }

}
