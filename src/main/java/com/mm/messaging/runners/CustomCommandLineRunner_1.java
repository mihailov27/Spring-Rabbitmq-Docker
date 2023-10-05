package com.mm.messaging.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class CustomCommandLineRunner_1 implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomCommandLineRunner_1.class);

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Starting the command line runner #1.");
        LOGGER.info("Completing the command line runner #1.");
    }
}
