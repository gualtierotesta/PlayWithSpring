package it.gualtierotesta.playwithspring.logging1.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ApplicationEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationEventListener.class);

    @EventListener
    public void onContextRefresh(ContextRefreshedEvent pEvent) {
        LOGGER.info("info: Context refreshed at {}", new Date(pEvent.getTimestamp()));
        LOGGER.debug("debug: Context refreshed");
        LOGGER.trace("trace: Context refreshed");
    }

    @EventListener
    public void onContextClose(ContextClosedEvent pEvent) {
        LOGGER.info("info: Context closed at {}", new Date(pEvent.getTimestamp()));
        LOGGER.debug("debug: Context closed");
        LOGGER.trace("trace: Context closed");
    }

}
