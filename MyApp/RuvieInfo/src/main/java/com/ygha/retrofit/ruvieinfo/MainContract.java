package com.ygha.retrofit.ruvieinfo;

public interface MainContract {

    interface MainView{

        void resultText(String text);
        void append(String append);
    }

    interface MainPresenter{
        void attachView(MainView mainView);
        void detachView();
    }

}
