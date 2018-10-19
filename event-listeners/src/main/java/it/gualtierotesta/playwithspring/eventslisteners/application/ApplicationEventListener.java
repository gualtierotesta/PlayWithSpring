package it.gualtierotesta.playwithspring.eventslisteners.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ApplicationEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationEventListener.class);

    @EventListener
    public void onContextStart(final ContextStartedEvent pEvent) {
        LOGGER.info("context started at {}: {}", LocalDateTime.now(), pEvent);
    }

    @EventListener
    public void onContextRefresh(final ContextRefreshedEvent pEvent) {
        LOGGER.info("context refreshed at {}: {}", LocalDateTime.now(), pEvent);
    }

    @EventListener
    public void onContextClose(final ContextClosedEvent pEvent) {
        LOGGER.info("context closed at {}: {}", LocalDateTime.now(), pEvent);
    }

    @EventListener
    public void onContextStop(final ContextStoppedEvent pEvent) {
        LOGGER.info("context stopped at {}: {}", LocalDateTime.now(), pEvent);
    }

}
