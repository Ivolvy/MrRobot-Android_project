package com.example.mgenty.mrrobot_android_project.chat;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mgenty.mrrobot_android_project.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedMessageFragment extends Fragment {
    private static final String TAG = "ReceiveFragment";
    private ReceivedListener mListener;


    public ReceivedMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ReceivedListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + "must implement ReceivedMessageFragment.ReceiveListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_received_message, container, false);

        return view;
    }

    public interface ReceivedListener {
        //void onReceivedMessageClicked(String message);
    }


}
