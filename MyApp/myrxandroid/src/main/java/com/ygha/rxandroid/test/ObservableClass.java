package com.ygha.rxandroid.test;

import com.ygha.rxandroid.test.util.User;
import com.ygha.rxandroid.test.util.UserUtil;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;


public class ObservableClass {

    public ObservableClass() {
    }

    public Flowable<Integer> getFlowableData() {
        return getFlowable();
    }

    private Flowable<Integer> getFlowable() {
        //return Flowable.range(0, new Random().nextInt(500));
        return Flowable.range(0, 9);
    }

    public Maybe<User> getMayble() {
        //return Flowable.range(0, new Random().nextInt(500));
        return Maybe.create(
                emitter -> {
                    emitter.onSuccess(UserUtil.getUser());
                }
        );
    }

    public Single<User> getSingle(){
        return Single.create(
                e->{
                    try {
                        e.onSuccess(UserUtil.getUser());
                    }catch (Exception f){
                        f.printStackTrace();
                    }
                }
        );
    }

    public Observable<User> getObservableFlatmap(){
        return Observable.create(
                emitter->{
                    if (!emitter.isDisposed()) {
                        for (User user : UserUtil.getUserList()) {
                            emitter.onNext(user);
                        }
                        emitter.onComplete();
                    }
                });
    }
}
