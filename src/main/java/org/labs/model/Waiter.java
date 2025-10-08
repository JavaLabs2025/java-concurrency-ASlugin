package org.labs.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Waiter extends Thread {

    private static final Logger log = LoggerFactory.getLogger(Waiter.class);

    private Boolean isFinished = false;

    public Waiter(String name) {
        super(name);
    }

    @Override
    public void run() {
        log.debug("Waiter started");
        while (!isFinished) {

        }
        log.debug("Waiter is finished");
    }
}
