package com.ygha.rxandroidbook.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.ygha.rxandroidbook.R;
import com.ygha.rxandroidbook.databinding.FragmentMainBinding;
import com.ygha.rxandroidbook.databinding.FragmentOkhttpBinding;
import com.ygha.rxandroidbook.logs.LogAdapter;
import com.ygha.rxandroidbook.square.Contributor;
import com.ygha.rxandroidbook.square.GitHubServiceApi;
import com.ygha.rxandroidbook.square.RestfulAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OKHttpFragment extends Fragment {

    private static final String sName = "jungjoonpark-pandora";
    private static final String sRepo = "rxAndroid";

    FragmentOkhttpBinding mBinding;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    // Log
    private LogAdapter mLogAdapter;
    private List<String> mLogs;

    private void log(String log) {
        mLogs.add(log);
        mLogAdapter.clear();
        mLogAdapter.addAll(mLogs);
    }

    private void setupLogger() {
        mLogs = new ArrayList<>();
        mLogAdapter = new LogAdapter(getActivity(), new ArrayList<>());
        mBinding.ohfLvLog.setAdapter(mLogAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_okhttp, container,false);
        mBinding.setFragment(this);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupLogger();
    }

    public void btnRetrofit(View v){
        startRetrofit();
    }

    public void btnOkHttp(View v){
        startOkHttp();
    }

    public void btnRx(View v){
        startRx();
    }

    /**
     * retrofit + okHttp( Call의 내부 )
     */
    private void startRetrofit() {
        GitHubServiceApi service = RestfulAdapter.getInstance().getSimpleApi();
        Call<List<Contributor>> call = service.getCallContributors(sName, sRepo);
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful()) {
                    List<Contributor> contributors = response.body();
                    for (Contributor c : contributors) {
                        log(c.toString());
                    }
                } else {
                    log("not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                log(t.getMessage());
            }
        });
    }

    /**
     * retrofit + okHttp
     */
    private void startOkHttp() {
        GitHubServiceApi service = RestfulAdapter.getInstance().getServiceApi();
        Call<List<Contributor>> call = service.getCallContributors(sName, sRepo);

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful()) {
                    List<Contributor> contributors = response.body();
                    for (Contributor c : contributors) {
                        log(c.toString());
                    }
                } else {
                    log("not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                log(t.getMessage());
            }
        });
    }


    /**
     * retrofit + okHttp + rxJava
     */
    private void startRx() {
        GitHubServiceApi service = RestfulAdapter.getInstance().getServiceApi();
        Observable<List<Contributor>> observable = service.getObContributors(sName, sRepo);

        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Contributor>>() {
                    @Override
                    public void onNext(List<Contributor> contributors) {
                        for (Contributor c : contributors) {
                            log(c.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        log(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        log("complete");
                    }
                })


        );
    }


}
