package com.example.mgenty.mrrobot_android_project.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mgenty.mrrobot_android_project.R;

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
        mListener = null;

        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public interface HomeListener {
    }
}
