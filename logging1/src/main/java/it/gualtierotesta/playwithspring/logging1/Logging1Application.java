package it.gualtierotesta.playwithspring.logging1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Logging1Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Logging1Application.class);

    public static void main(String[] args) {

        LOGGER.info("info: **** Application start");
        LOGGER.debug("debug: **** Application start");
        LOGGER.trace("trace: **** Application start");
        SpringApplication.run(Logging1Application.class, args);
    }
}
