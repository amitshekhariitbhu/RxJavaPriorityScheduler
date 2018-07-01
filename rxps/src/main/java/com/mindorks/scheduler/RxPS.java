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

package com.mindorks.scheduler;

import com.mindorks.scheduler.internal.CustomPriorityScheduler;

import io.reactivex.Scheduler;

public final class RxPS {

    private static RxPS instance;
    private CustomPriorityScheduler priorityScheduler;

    private RxPS() {
        this.priorityScheduler = CustomPriorityScheduler.create();
    }

    private static RxPS getInstance() {
        if (instance == null) {
            synchronized (RxPS.class) {
                if (instance == null) {
                    instance = new RxPS();
                }
            }
        }
        return instance;
    }

    private CustomPriorityScheduler getPriorityScheduler() {
        return priorityScheduler;
    }

    public static Scheduler get(Priority priority) {
        return getInstance().getPriorityScheduler().get(priority);
    }

    public static Scheduler low() {
        return get(Priority.LOW);
    }

    public static Scheduler medium() {
        return get(Priority.MEDIUM);
    }

    public static Scheduler high() {
        return get(Priority.HIGH);
    }

    public static Scheduler immediate() {
        return get(Priority.IMMEDIATE);
    }

}
