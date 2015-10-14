package com.example.mgenty.mrrobot_android_project.chat;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mgenty.mrrobot_android_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendMessageFragment extends Fragment {
    private static final String TAG = "SendFragment";
    private SendListener mListener;

    @Bind(R.id.messageToSend) TextView mMessageToSend;

    public SendMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (SendListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + "must implement SendMessageFragment.SendListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_message, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.buttonSendMessage)
    void onClick(View v){
        Log.d(TAG, "onClickSendMessageListener");
        mListener.onSendMessageClicked(mMessageToSend.getText().toString());
    }

    public interface SendListener {
        void onSendMessageClicked(String message);
    }

}
