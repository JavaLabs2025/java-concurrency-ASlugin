package org.labs.model;

public class Spoon {

    private final long id;

    private Boolean isLocked = false;

    public Spoon(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void take() {
        while (isLocked) {}
        isLocked = true;

    }

    public void release() {
        isLocked = false;
    }

}
