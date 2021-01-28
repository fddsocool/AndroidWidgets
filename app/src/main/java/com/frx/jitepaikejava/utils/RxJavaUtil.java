package com.frx.jitepaikejava.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtil {

    /**
     * 异步执行
     *
     * @param <T>
     */
    public interface OnRxRunListener<T> {
        //在子线程执行
        T doInBackground() throws Throwable;

        void onSubscribe(@NonNull Disposable d);

        //事件执行成功, 在主线程回调
        void onFinish(T result);

        //事件执行失败, 在主线程回调
        void onError(Throwable e);

        //事件完成, 在主线程回调
        void onDone();
    }

    public static <T> void run(final OnRxRunListener<T> onRxRunListener) {
        if (onRxRunListener == null) {
            return;
        }
        Observable.create(
                (ObservableOnSubscribe<T>) emitter -> {
                    try {
                        T t = onRxRunListener.doInBackground();
                        if (t != null) {
                            emitter.onNext(t);
                        } else {
                            emitter.onError(new NullPointerException("异步操作结果为空"));
                        }
                    } catch (Throwable throwable) {
                        emitter.onError(throwable);
                    } finally {
                        emitter.onComplete();
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .safeSubscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        onRxRunListener.onSubscribe(disposable);
                    }

                    @Override
                    public void onNext(@NonNull T t) {
                        onRxRunListener.onFinish(t);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        onRxRunListener.onError(e);
                        onRxRunListener.onDone();
                    }

                    @Override
                    public void onComplete() {
                        onRxRunListener.onDone();
                    }
                });
    }

    /**
     * 异步间隔执行
     */
    public interface OnRxRunIntervalListener {
        //是否循环
        Boolean takeWhile() throws Exception;

        //执行事件, 在主线程回调
        void onExecute(Long l);

        //循环结束
        void onFinish();

        //事件执行失败, 在主线程回调
        void onError(Throwable e);
    }

    public static Disposable runInterval(long period, final OnRxRunIntervalListener onRxRunIntervalListener) {
        if (onRxRunIntervalListener == null) {
            return null;
        }

        return Observable.interval(period, TimeUnit.MILLISECONDS)
                .takeWhile(aLong -> onRxRunIntervalListener.takeWhile())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(@NonNull Long l) {
                        onRxRunIntervalListener.onExecute(l);
                    }

                    @Override
                    public void onComplete() {
                        onRxRunIntervalListener.onFinish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        onRxRunIntervalListener.onError(e);
                    }
                });
    }

    /**
     * 异步间隔执行
     */
    public interface OnRxRunIntervalDelayListener {

        void onStart();

        //是否循环
        Boolean takeWhile() throws Exception;

        //执行事件, 在主线程回调
        void onExecute(Long l);

        //循环结束
        void onFinish();

        //事件执行失败, 在主线程回调
        void onError(Throwable e);
    }

    public static Disposable runIntervalDelay(long delay, long period, final OnRxRunIntervalDelayListener onRxRunIntervalDelayListener) {
        if (onRxRunIntervalDelayListener == null) {
            return null;
        }

        return Observable.interval(delay, period, TimeUnit.MILLISECONDS)
                .takeWhile(aLong -> onRxRunIntervalDelayListener.takeWhile())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {

                    @Override
                    protected void onStart() {
                        super.onStart();
                        onRxRunIntervalDelayListener.onStart();
                    }

                    @Override
                    public void onNext(@NonNull Long l) {
                        onRxRunIntervalDelayListener.onExecute(l);
                    }

                    @Override
                    public void onComplete() {
                        onRxRunIntervalDelayListener.onFinish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        onRxRunIntervalDelayListener.onError(e);
                    }
                });
    }

    /**
     * 异步执行
     */
    public interface OnRxRunTimerListener {
        //执行事件, 在主线程回调
        void onExecute(Long l);

        //循环结束
        void onFinish();

        //事件执行失败, 在主线程回调
        void onError(Throwable e);
    }

    public static Disposable runTimer(long delay, OnRxRunTimerListener onRxRunTimerListener) {
        return Observable.timer(delay, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(@NonNull Long l) {
                        onRxRunTimerListener.onExecute(l);
                    }

                    @Override
                    public void onComplete() {
                        onRxRunTimerListener.onFinish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        onRxRunTimerListener.onError(e);
                    }
                });
    }
}
