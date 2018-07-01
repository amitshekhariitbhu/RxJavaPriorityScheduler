/*
 * Copyright (C) 2018 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mindorks.scheduler.internal;


import com.mindorks.scheduler.Priority;

import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ScheduledRunnable;

public final class PrioritySchedulerWorker extends Scheduler.Worker {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final PriorityBlockingQueue<InternalRunnable> priorityBlockingQueue;
    private final Priority priority;
    private final int sequenceNumber;

    PrioritySchedulerWorker(PriorityBlockingQueue<InternalRunnable> priorityBlockingQueue, Priority priority, int sequenceNumber) {
        this.priorityBlockingQueue = priorityBlockingQueue;
        this.priority = priority;
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public Disposable schedule(Runnable action) {
        return schedule(action, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public Disposable schedule(Runnable run, long delayTime, TimeUnit unit) {

        final InternalRunnable comparableRunnable = new InternalRunnable(run, priority, sequenceNumber);

        final ScheduledRunnable scheduledRunnable = new ScheduledRunnable(comparableRunnable, compositeDisposable);
        scheduledRunnable.setFuture(new Future<Object>() {
            @Override
            public boolean cancel(boolean b) {
                return priorityBlockingQueue.remove(comparableRunnable);
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() {
                return null;
            }

            @Override
            public Object get(long l, TimeUnit timeUnit) {
                return null;
            }
        });

        compositeDisposable.add(scheduledRunnable);

        priorityBlockingQueue.offer(comparableRunnable, delayTime, unit);

        return scheduledRunnable;
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }

    @Override
    public boolean isDisposed() {
        return compositeDisposable.isDisposed();
    }

}
