package it.gualtierotesta.playwithspring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "Ciao mondo";
    }
}
