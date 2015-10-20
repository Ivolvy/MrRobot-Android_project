package com.example.mgenty.mrrobot_android_project.home;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mgenty.mrrobot_android_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private HomeListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (HomeListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + "must implement HomeFragment.HomeListener");
        }

    }

    @Override
    public void onDestroyView(){
        ButterKnife.unbind(this);
        mListener = null;

        super.onDestroyView();
    }

    @OnClick(R.id.homeLoginButton)
    void onClicklogin(View v){
        Log.d(TAG, "onClickLoginListener");
        mListener.onPageLoginButtonClicked();
    }

    @OnClick(R.id.homeRegisterButton)
    void onClickRegister(View v){
        Log.d(TAG, "onClickRegisterListener");
        mListener.onPageRegisterButtonClicked();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public interface HomeListener {
        void onPageLoginButtonClicked();
        void onPageRegisterButtonClicked();
    }
}
