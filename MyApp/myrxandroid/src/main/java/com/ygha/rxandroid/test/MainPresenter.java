package com.ygha.rxandroid.test;

import android.view.View;

import com.ygha.rxandroid.test.util.User;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class MainPresenter implements MainContract.Presenter {
    ObservableClass observableClass;

    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
        observableClass = new ObservableClass();
    }

    public void BtnMaybe(View view){
        mView.callMaybe();
    }

    public Flowable<Integer> onButtonFlowable(){
        return observableClass.getFlowableData();
    }

    public Maybe<User> onButtonMaybe(){
        return observableClass.getMayble();
    }

    public Single<User> onButtonSingle(){
        return observableClass.getSingle();
    }

    public Observable<User> onButtonObservable(){
        return observableClass.getObservableFlatmap();
    }

}
