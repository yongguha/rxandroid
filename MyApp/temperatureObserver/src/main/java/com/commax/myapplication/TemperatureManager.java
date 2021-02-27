package com.commax.myapplication;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class TemperatureManager {

    private PublishSubject<Temperature> subject = PublishSubject.create();

    public static class Temperature{
        private int currentTemperature;

        public Temperature(int currentTemperature) {
            this.currentTemperature = currentTemperature;
        }

        int getDegree(){
            return this.currentTemperature;
        }
    }



    void setTemperature(Temperature temperature){
        subject.onNext(temperature);
    }

    Observable<Temperature> updateEvent(){
        return subject.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
