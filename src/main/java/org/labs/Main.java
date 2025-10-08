package org.labs;


import org.labs.model.Programmer;
import org.labs.model.Spoon;
import org.labs.model.Waiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("---------- Dinner of programmers ----------");

        int numberOfProgrammers = 2;
        int numberOfWaiters = 2;

        List<Programmer> programmers = createProgrammers(numberOfProgrammers);
        programmers.forEach(Thread::start);

        List<Waiter> waiters = createWaiters(numberOfWaiters);
        waiters.forEach(Thread::start);

    }

    private static List<Programmer> createProgrammers(int numberOfProgrammers) {
        List<Programmer> programmers = new ArrayList<>();

        Spoon firstSpoon = new Spoon(0);

        Spoon leftSpoon = firstSpoon;
        Spoon rightSpoon;
        for (int i = 0; i < numberOfProgrammers - 1; i++) {
            rightSpoon = new Spoon(i + 1);
            programmers.add(new Programmer(
                    "Programmer-" + i,
                    leftSpoon,
                    rightSpoon
            ));

            leftSpoon = rightSpoon;
        }
        programmers.add(new Programmer(
                "Programmer-" + (numberOfProgrammers - 1),
                leftSpoon,
                firstSpoon
        ));

        return programmers;
    }

    private static List<Waiter> createWaiters(int numberOfWaiters) {
        List<Waiter> waiters = new ArrayList<>();
        for (int i = 0; i < numberOfWaiters; i++) {
            waiters.add(new Waiter("Waiter-" + i));
        }
        return waiters;
    }
}