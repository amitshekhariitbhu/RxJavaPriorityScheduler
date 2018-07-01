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

package com.mindorks.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mindorks.scheduler.RxPS;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // simulate so many long tasks
        for (int i = 0; i <= 10; i++) {
            getObservable("low-priority-task : " + i)
                    .subscribeOn(RxPS.low())
                    .subscribe(getObserver());

            getObservable("medium-priority-task : " + i)
                    .subscribeOn(RxPS.medium())
                    .subscribe(getObserver());

            getObservable("high-priority-task : " + i)
                    .subscribeOn(RxPS.high())
                    .subscribe(getObserver());

            getObservable("immediate-priority-task : " + i)
                    .subscribeOn(RxPS.immediate())
                    .subscribe(getObserver());
        }
    }

    private Observable<String> getObservable(final String value) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                // simulate long task
                Thread.sleep(3000);
                emitter.onNext(value);
                emitter.onComplete();
            }
        });
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext : The value is " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
