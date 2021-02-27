package com.ygha.retrofit.ruvieinfo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ygha.retrofit.ruvieinfo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements MainContract.MainView{



    MainPresenter mainPresenter;

    Context context;

    private View decorView;
    private int	uiOption;

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doFullScreen();
        context= this;
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setDisplay();
        mainPresenter = new MainPresenter(context);
        mainPresenter.attachView(this);

        long startTime = System.currentTimeMillis();

        mainPresenter.startRxGetEmsDataBySort();
        mainPresenter.startRxGetParkingBySort();
        mainPresenter.startRxGetParcelBySort();
        mainPresenter.startRxGetNoticeContentBySort();

        mainBinding.setEmsdata(mainPresenter.getEmsObservable());
        mainBinding.setParking(mainPresenter.getParkingObservable());
        mainBinding.setParcel(mainPresenter.getParcelObservable());
        mainBinding.setNotice(mainPresenter.getNoticeObservable());

        long endTime = System.currentTimeMillis();
        Log.e("Ruvie","elapsedtime is: " + (endTime - startTime)/1000.0+"초");
    }

    private void doFullScreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE|
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void setDisplay(){
        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );
    }

    @Override
    public void append(String append) {
        //mainBinding.txtEms.append(append);
    }

    @Override
    public void resultText(String text) {
        //mainBinding.txtEms.setText(text);
    }
}