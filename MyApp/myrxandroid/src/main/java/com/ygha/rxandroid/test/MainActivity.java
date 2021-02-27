package com.ygha.rxandroid.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ygha.rxandroid.test.databinding.ActivityMainBinding;
import com.ygha.rxandroid.test.util.Constants;
import com.ygha.rxandroid.test.util.User;
import com.ygha.rxandroid.test.util.UserUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    ActivityMainBinding binding;
    CompositeDisposable compositeDisposable;

    private ObservableClass mObservableClass;


    MainPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
        presenter = new MainPresenter(this);
        mObservableClass = new ObservableClass();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }



    @Override
    public void callMaybe() {
        mObservableClass.getMayble()
                .subscribe(s->System.out.println(s));
    }

    @SuppressLint("CheckResult")
    public void onButtonFlowable(View v){
    presenter.onButtonFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .scan(0,(x,y)->x+y)
                .doOnSubscribe(
                        s->{
                            binding.message.append("onSubscribe");
                            binding.message.append(Constants.LINE_SEPARATOR);
                            //disposable = s;
                        }
                )
                .subscribe(
                        s->{
                            System.out.println("subscriber :"  +s);
                            binding.message.append("onSuccess");
                            binding.message.append(Constants.LINE_SEPARATOR);
                            binding.message.append(String.valueOf(s));
                            binding.message.append(Constants.LINE_SEPARATOR);
                        }
                        );

    }

    public void onButtonMaybe(View v){
        compositeDisposable.add(presenter.onButtonMaybe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(
                        x->{
                            binding.message.append("onSubscribe");
                            binding.message.append(Constants.LINE_SEPARATOR);
                        }
                )
                .subscribe(
                        x->{
                            System.out.println("onSuccess");
                            binding.message.append("onsuccess");
                            binding.message.append(Constants.LINE_SEPARATOR);
                            binding.message.append(x.getName());
                            binding.message.append(Constants.LINE_SEPARATOR);
                        },
                        x->System.out.println("onError"),
                        ()->System.out.println("onComplete")
                )
        );

    }

    public void onButtonSingle(View v){
        compositeDisposable.add(presenter.onButtonSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(s->{
                    binding.message.append("onSubscribe");
                    binding.message.append(Constants.LINE_SEPARATOR);
                })
                .subscribe(
                        s->{
                            binding.message.append("onSuccess");
                            binding.message.append(Constants.LINE_SEPARATOR);
                            binding.message.append(s.getName());
                            binding.message.append(Constants.LINE_SEPARATOR);
                        },
                        s->{
                            binding.message.append("onError : " + s.getMessage());
                            binding.message.append(Constants.LINE_SEPARATOR);
                        }
                )
        );
    }


    public void onButtonFlatMap(View v){
        presenter.onButtonObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(
                        s->getUserAddressObservable(s)
                ).subscribe(
                      s->{
                          binding.message.append(s.getName() + ", " + s.getAddress());
                          binding.message.append(Constants.LINE_SEPARATOR);
                      },
                      s->{
                          binding.message.append("onError");
                          binding.message.append(Constants.LINE_SEPARATOR);
                      },
                      ()->{
                          binding.message.append("onComplete");
                          binding.message.append(Constants.LINE_SEPARATOR);
                      }

                );
    }

    private Observable<User> getUserAddressObservable(final User user) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    user.setAddress(UserUtil.getUserAddress().get(user.getId()));
                    emitter.onNext(user);
                    emitter.onComplete();
                }
            }
        });
    }


}
