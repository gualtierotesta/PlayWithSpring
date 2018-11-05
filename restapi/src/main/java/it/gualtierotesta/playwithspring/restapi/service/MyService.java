package it.gualtierotesta.playwithspring.restapi.service;

import it.gualtierotesta.playwithspring.restapi.dto.MyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyService {

    public ServiceResult<String> doGet() {
        return ServiceResult.success("ok");
    }

    public ServiceResult<MyData> doPost(MyData myData) {
        // persist
        log.info("data={}", myData);
        return ServiceResult.success(myData);
    }
}
