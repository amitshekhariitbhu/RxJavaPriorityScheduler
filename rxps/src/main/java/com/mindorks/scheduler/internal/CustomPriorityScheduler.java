

package com.mindorks.scheduler.internal;


import com.mindorks.scheduler.Priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Scheduler;

public final class CustomPriorityScheduler {

    private static final int DEFAULT_MAX_NUM_THREADS = 2 * Runtime.getRuntime().availableProcessors() + 1;
    private final AtomicInteger sequenceGenerator;
    private final AtomicInteger workerCount;
    private final ExecutorService executorService;
    private final PriorityBlockingQueue<InternalRunnable> priorityBlockingQueue;

    private CustomPriorityScheduler() {
        this.priorityBlockingQueue = new PriorityBlockingQueue<>();
        this.workerCount = new AtomicInteger();
        this.sequenceGenerator = new AtomicInteger();
        this.executorService = Executors.newFixedThreadPool(DEFAULT_MAX_NUM_THREADS);
    }

    public static CustomPriorityScheduler create() {
        return new CustomPriorityScheduler();
    }

    public Scheduler get(Priority priority) {
        return new CustomScheduler(priority, sequenceGenerator.incrementAndGet());
    }

    private final class CustomScheduler extends Scheduler {

        private final Priority priority;
        private final int sequenceNumber;

        private CustomScheduler(Priority priority, int sequenceNumber) {
            this.priority = priority;
            this.sequenceNumber = sequenceNumber;
        }

        @Override
        public Worker createWorker() {
            synchronized (workerCount) {
                if (workerCount.get() < DEFAULT_MAX_NUM_THREADS) {
                    workerCount.incrementAndGet();
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    InternalRunnable runnable = priorityBlockingQueue.take();
                                    runnable.run();
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            return new PrioritySchedulerWorker(priorityBlockingQueue, priority, sequenceNumber);
        }
    }

}
