package com.ygha.rxandroid.backbutton;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;


import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {

    private Disposable backPressDisposable;

    private BehaviorSubject<Long> backPressedSubject = BehaviorSubject.createDefault(10L);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressDisposable = backPressedSubject
                .buffer(2, 3)
                .map(s->new Pair<>(s.get(0), s.get(1)))
                .map(pair->pair.second -pair.first < TimeUnit.SECONDS.toMillis(2))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(willFinish->{
                        if(willFinish){
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), "다시 누르면 종료함", Toast.LENGTH_SHORT).show();
                        }
                });
    }

    @Override
    public void onBackPressed() {
        backPressedSubject.onNext(System.currentTimeMillis());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            backPressDisposable.dispose();
        }catch (Exception e){}
    }
}
