package com.commax.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.commax.myapplication.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final TemperatureManager manager = new TemperatureManager();
    private ActivityMainBinding binding;
    private Disposable disposable, disposable1;
    TextView asyncTextView;
    Button startBtn,stopBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setTemp("No Data");
        asyncTextView = findViewById(R.id.async);
        startBtn = (Button)findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPolling();
            }
        });
        stopBtn = (Button)findViewById(R.id.stop);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(disposable1.isDisposed())
                    disposable1.dispose();
            }
        });
        initRxAsync();
        //startPolling();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            int nextTemperature = (new Random()).nextInt(15) + 10;
            manager.setTemperature(new TemperatureManager.Temperature(nextTemperature));
        }, 0L, 3, TimeUnit.SECONDS);

        disposable = manager.updateEvent().subscribe(this::updateView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        disposable1.dispose();
    }

    private void updateView(TemperatureManager.Temperature temperature){
        binding.setTemp("현재 온도는"+ temperature.getDegree()+"입니다.");

        //disposable.dispose();
    }

    private void initRxAsync(){
        Observable.just("Hello", "rx", "world")
                .reduce((x,y)->x+" "+y)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s->asyncTextView.setText(s));
    }

    private void startPolling(){
        Observable<String> observable = Observable.just("Polling")
                .repeatWhen(x->x.delay(20, TimeUnit.SECONDS));
                //.repeat();

        disposable1 = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s-> Log.e("test","startPolling"));
        //disposable.dispose();

    }

}