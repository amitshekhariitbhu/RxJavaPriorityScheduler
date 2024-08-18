

package com.mindorks.scheduler.internal;

import com.mindorks.scheduler.Priority;

public final class InternalRunnable implements Runnable, Comparable<InternalRunnable> {

    private final Runnable runnable;
    private final Priority priority;
    private final int sequenceNumber;

    InternalRunnable(Runnable runnable, Priority priority, int sequenceNumber) {
        this.runnable = runnable;
        this.priority = priority;
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public void run() {
        runnable.run();
    }

    @Override
    public int compareTo(InternalRunnable other) {
        return (priority == other.priority ? sequenceNumber - other.sequenceNumber
                : other.priority.ordinal() - priority.ordinal());
    }

}
