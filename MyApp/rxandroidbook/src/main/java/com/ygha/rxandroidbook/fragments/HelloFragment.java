package com.ygha.rxandroidbook.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.ygha.rxandroidbook.R;
import com.ygha.rxandroidbook.databinding.FragmentHelloBinding;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

public class HelloFragment extends Fragment {

    FragmentHelloBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_hello, container,false);
        mBinding.setFragment(this);
        return mBinding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void start(View view) {
        getObservable()
                .subscribe(getObserver());

        // case 1 : original
//        Observable.create(
//                new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<String> e) throws Exception {
//                        e.onNext("hello world!");
//                        e.onComplete();
//                    }
//                }).subscribe(getObserver());


        // case 2 : lambda
//        Observable.<String>create(s -> {
//            s.onNext("Hello, world!");
//            s.onComplete();
//        }).subscribe(o -> textView.setText(o));


        // case 3 : other Observable creator and reference method.
//        Observable.just("Hello, world!")
//                .subscribe(textView::setText);

    }

    private Observable<String> getObservable() {
        return Observable.just("Hello world!");
    }

    private Observer<? super String> getObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                mBinding.tvDisplay.setText(R.string.hello);
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
