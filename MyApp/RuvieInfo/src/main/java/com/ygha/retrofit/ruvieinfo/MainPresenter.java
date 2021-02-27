package com.ygha.retrofit.ruvieinfo;

import android.content.Context;


import androidx.databinding.ObservableArrayMap;

import com.ygha.retrofit.ruvieinfo.RevieInfo.EmsData;
import com.ygha.retrofit.ruvieinfo.RevieInfo.NoticeContent;
import com.ygha.retrofit.ruvieinfo.RevieInfo.NotiResultList;
import com.ygha.retrofit.ruvieinfo.RevieInfo.Parcel;
import com.ygha.retrofit.ruvieinfo.RevieInfo.Parking;
import com.ygha.retrofit.ruvieinfo.RevieInfo.RuvieDevice;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.MainPresenter{

    Context context;
    MainContract.MainView mainView;

    public MainPresenter(Context context) {
        this.context = context;
    }



    @Override
    public void attachView(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void detachView() {
        mCompositeDisposable.clear();
        mCompositeDisposable=null;
        mainView=null;
    }

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void startRxGetCommonInfoDevice(){

        CommonInfoServiceApi commonInfoServiceApi = RestfulAdapter.getInstance().setGetRxOkHttpRetrofit();
        Observable<RuvieDevice> observable = commonInfoServiceApi.
                getCommonInfoDevice();

        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<RuvieDevice>() {
                    @Override
                    public void onNext(RuvieDevice deviceData) {
                        deviceData.getResultList().stream().forEach(s->{
                                    mainView.append("\r"+s.getDeviceName() + "\n");
                                }
                        );


                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.resultText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mainView.append("complete");
                    }
                })


        );
    }

    public ObservableArrayMap<String, String> getEmsObservable(){
        return EmsObservableMap;
    }
    ObservableArrayMap<String, String> EmsObservableMap = new ObservableArrayMap<>();
    public void startRxGetEmsDataBySort(){


        CommonInfoServiceApi commonInfoServiceApi = RestfulAdapter.getInstance().setGetRxOkHttpRetrofit();
        Observable<EmsData> observable = commonInfoServiceApi.
                getEmsByIdSortRx("202012", "all", "month", "1234", "2351" );

        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<EmsData>() {
                    @Override
                    public void onNext(EmsData emsData) {

                       emsData.getMonthlyList().stream().filter(s->s.getDate().equalsIgnoreCase("202012")).forEach(s->{
                           if(s.getGas() != null) {
                               EmsObservableMap.put("gas_usage", s.getGas().getValue());
                               EmsObservableMap.put("gas_average", s.getGas().getAverageOfBuilding());
                           }
                           if(s.getWater()!=null){
                               EmsObservableMap.put("water_usage", s.getWater().getValue());
                               EmsObservableMap.put("water_average", s.getWater().getAverageOfBuilding());
                           }
                           if(s.getElectricity()!=null){
                               EmsObservableMap.put("electric_usage", s.getElectricity().getValue());
                               EmsObservableMap.put("electric_average", s.getElectricity().getAverageOfBuilding());
                           }
                           if(s.getHeating()!=null){
                               EmsObservableMap.put("heating_usage", s.getHeating().getValue());
                               EmsObservableMap.put("heating_average", s.getHeating().getAverageOfBuilding());
                           }
                           if(s.getHotwater()!=null){
                               EmsObservableMap.put("hotwater_usage", s.getHotwater().getValue());
                               EmsObservableMap.put("hotwater_average", s.getHotwater().getAverageOfBuilding());
                           }


                       });




                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.resultText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // mainView.resultText("complete");
                    }
                })


        );
    }


    public ObservableArrayMap<String, String> getNoticeObservable(){
        return NoticeObservableMap;
    }
    ObservableArrayMap<String, String> NoticeObservableMap = new ObservableArrayMap<>();

    public void startRxGetNoticeContentBySort(){
        CommonInfoServiceApi commonInfoServiceApi = RestfulAdapter.getInstance().setGetRxOkHttpRetrofit();
        Observable<NoticeContent> observable = commonInfoServiceApi.
                getNoticeByIdSortRx("1", "10","1234", "2351" );

        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<NoticeContent>() {
                    @Override
                    public void onNext(NoticeContent notice) {

                        List<NotiResultList> noticeList = notice.getNotiResultList();
                        for(int i=0;i<noticeList.size();i++) {
                            if(i==0) {
                                NoticeObservableMap.put("index", noticeList.get(i).getNotiindex());
                                NoticeObservableMap.put("subject", noticeList.get(i).getNotisubject());
                                NoticeObservableMap.put("writer", noticeList.get(i).getNotiwriter());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.resultText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // mainView.resultText("complete");
                    }
                })


        );
    }



    public ObservableArrayMap<String, String> getParkingObservable(){
        return ParkingObservableMap;
    }
    ObservableArrayMap<String, String> ParkingObservableMap = new ObservableArrayMap<>();
    public void startRxGetParkingBySort(){

        CommonInfoServiceApi commonInfoServiceApi = RestfulAdapter.getInstance().setGetRxOkHttpRetrofit();
        Observable<Parking> observable = commonInfoServiceApi.
                getParkingByIdSortRx("T", "1234", "2351" );

        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Parking>() {
                    @Override
                    public void onNext(Parking parking) {
                        for(int i=0;i<parking.getParkingResultList().size();i++) {
                            if (i == 0) {
                                ParkingObservableMap.put("carno", parking.getParkingResultList().get(i).getCarno());
                                ParkingObservableMap.put("parkingfloor", parking.getParkingResultList().get(i).getParkingFloor());
                                ParkingObservableMap.put("parkingnum", parking.getParkingResultList().get(i).getParkingNum());
                                ParkingObservableMap.put("parkingtime", (String) parking.getParkingResultList().get(i).getParkingTime());
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.resultText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // mainView.resultText("complete");
                    }
                })


        );
    }


    public ObservableArrayMap<String, String> getParcelObservable(){
        return ParcelObservableMap;
    }
    ObservableArrayMap<String, String> ParcelObservableMap = new ObservableArrayMap<>();
    public void startRxGetParcelBySort(){

        CommonInfoServiceApi commonInfoServiceApi = RestfulAdapter.getInstance().setGetRxOkHttpRetrofit();
        Observable<Parcel> observable = commonInfoServiceApi.
                getParcelByIdSortRx("1","10",  "1234", "2351" );

        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Parcel>() {
                    @Override
                    public void onNext(Parcel parcel) {

                        for(int i=0;i<parcel.getParcelResult().size();i++) {
                            if (i == 0) {
                                ParcelObservableMap.put("boxno", parcel.getParcelResult().get(i).getBoxNo());
                                ParcelObservableMap.put("invoicnum", parcel.getParcelResult().get(i).getInvoiceNumber());
                                ParcelObservableMap.put("company", parcel.getParcelResult().get(i).getCompany());
                                ParcelObservableMap.put("date", parcel.getParcelResult().get(i).getDate());
                                ParcelObservableMap.put("status", parcel.getParcelResult().get(i).getStatus());
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.resultText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // mainView.resultText("complete");
                    }
                })


        );
    }





}
