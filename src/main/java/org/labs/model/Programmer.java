package org.labs.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Programmer extends Thread {

    private static final Logger log = LoggerFactory.getLogger(Programmer.class);
    private static final long talkingTimeInMilliseconds = 1000;
    private static final long eatingTimeInMilliseconds = 5000;

    private ProgrammerState state = ProgrammerState.TALK;

    private final Spoon leftSpoon;
    private final Spoon rightSpoon;

    private Boolean isFinished = false;

    public Programmer(
            String name,
            Spoon leftSpoon,
            Spoon rightSpoon
    ) {
        super(name);
        this.leftSpoon = leftSpoon;
        this.rightSpoon = rightSpoon;

        log.debug("Init {} with {} left spoon and {} right spoon", name, leftSpoon.getId(), rightSpoon.getId());
    }

    @Override
    public void run() {
        log.debug("{} started", getName());

        while (!isFinished) {
            switch (state) {
                case TALK -> talk();
                case HUNGRY -> hungry();
                case EAT -> eat();
                default -> throw new IllegalStateException();
            }
        }

        log.debug("{} is finished", getName());
    }



    private void talk() {
        try {
            log.debug("{} is talking", getName());
            Thread.sleep(talkingTimeInMilliseconds);

            state = ProgrammerState.HUNGRY;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void hungry() {
        log.debug("{} is hungry", getName());

        // Ждать свою порцию


        log.debug("{} trying to take left spoon {}", getName(), leftSpoon.getId());
        leftSpoon.take();
        log.debug("{} took left spoon {}", getName(), leftSpoon.getId());

        log.debug("{} trying to take right spoon {}", getName(), rightSpoon.getId());
        rightSpoon.take();
        log.debug("{} took right spoon {}", getName(), rightSpoon.getId());

        state = ProgrammerState.EAT;
    }


    private void eat() {
        try {
            log.debug("{} is eating", getName());
            Thread.sleep(eatingTimeInMilliseconds);

            leftSpoon.release();
            rightSpoon.release();
            state = ProgrammerState.TALK;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
