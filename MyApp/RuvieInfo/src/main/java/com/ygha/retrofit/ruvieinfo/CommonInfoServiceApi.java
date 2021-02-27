package com.ygha.retrofit.ruvieinfo;


import com.ygha.retrofit.ruvieinfo.RevieInfo.EmsData;
import com.ygha.retrofit.ruvieinfo.RevieInfo.NoticeContent;
import com.ygha.retrofit.ruvieinfo.RevieInfo.Parcel;
import com.ygha.retrofit.ruvieinfo.RevieInfo.Parking;
import com.ygha.retrofit.ruvieinfo.RevieInfo.RuvieDevice;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommonInfoServiceApi {


    //전체 질의
    @GET("device")
    Observable<RuvieDevice> getCommonInfoDevice();

    //id별로 질의 후, 내림 차순 정렬
    @GET("ems")
    Observable<EmsData> getEmsByIdSortRx(@Query("emsDate") String emsDate, @Query("emsType") String emsType,
                                         @Query("emsUnit") String emsUnit, @Query("mDong") String mDong,
                                         @Query("mHo") String mHo);

    //id별로 질의 후, 내림 차순 정렬
    @GET("noticeContent")
    Observable<NoticeContent> getNoticeByIdSortRx(@Query("notiPageNum") String notiPageNum, @Query("notiListCount") String notiListCount,
                                                  @Query("mDong") String mDong, @Query("mHo") String mHo);

    //id별로 질의 후, 내림 차순 정렬
    @GET("parking")
    Observable<Parking> getParkingByIdSortRx(@Query("isHost") String isHost, @Query("mDong") String mDong, @Query("mHo") String mHo);


    //id별로 질의 후, 내림 차순 정렬
    @GET("parcel")
    Observable<Parcel> getParcelByIdSortRx(@Query("parcelPageNum") String parcelPageNum, @Query("parcelListCount") String parcelListCount,
                                           @Query("mDong") String mDong, @Query("mHo") String mHo);
}
