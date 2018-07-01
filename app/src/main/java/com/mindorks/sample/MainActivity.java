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
