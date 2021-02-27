package com.ygha.rxandroidbook.fragments;


import android.os.Bundle;import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.ygha.rxandroidbook.R;
import com.ygha.rxandroidbook.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getSimpleName();

    FragmentMainBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container,false);
        mBinding.setFragment(this);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void demoHello(View view) {
        startDemo(new HelloFragment());
    }

    public void demoOKHttp(View view) {
        startDemo(new OKHttpFragment());
    }
    public void demoDebounce(View view) {
        startDemo(new DebounceFragment());
    }

    private void startDemo(@NonNull Fragment fragment) {
        final String tag = fragment.getClass().getSimpleName();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }
}
