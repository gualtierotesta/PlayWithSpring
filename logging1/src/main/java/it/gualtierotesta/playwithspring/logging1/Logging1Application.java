package it.gualtierotesta.playwithspring.logging1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Logging1Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Logging1Application.class);

    public static void main(final String[] args) {
        SpringApplication.run(Logging1Application.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        LOGGER.info("info: **** Application start");
        LOGGER.debug("debug: **** Application start");
        LOGGER.trace("trace: **** Application start");
    }
}
