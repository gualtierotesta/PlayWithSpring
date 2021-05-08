package it.gualtierotesta.playwithspring.restdb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @RequestMapping(name = "/api")
    public String get() {
        System.out.println("Request");
        return "{\"result\" : \"ciao\"}";
    }

}
