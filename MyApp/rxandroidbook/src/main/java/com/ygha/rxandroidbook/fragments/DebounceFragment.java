package com.ygha.rxandroidbook.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.ygha.rxandroidbook.R;
import com.ygha.rxandroidbook.databinding.FragmentDebounceBinding;
//import com.ygha.rxandroidbook.databinding.FragmentDebounceBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;


public class DebounceFragment extends Fragment {

    FragmentDebounceBinding mBinding;

    Disposable mDisposable;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_debounce, container, false);
        mBinding.setFragment(this);
        return mBinding.getRoot();
    }

    public void onClick(View v){
        mDisposable = getObservable()
                .debounce(5000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
                    String time = sdf.format(Calendar.getInstance().getTime());
                    mBinding.tvDisplay.setText("Clicked : " + time.toString());

                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDisposable.dispose();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
        mDisposable = getObservable()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
                    String time = sdf.format(Calendar.getInstance().getTime());
                    mBinding.tvDisplay.setText("Clicked : " + time.toString());

                });
         */


    }

    private Observable<View> getObservable() {
        return Observable.create(e ->  mBinding.btnDebounce.setOnClickListener(e::onNext));
    }
}
