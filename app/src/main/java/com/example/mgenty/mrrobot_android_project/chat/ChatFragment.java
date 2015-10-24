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
public class ChatFragment extends Fragment {
    private static final String TAG = "ChatFragment";
    private ChatListener mListener;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ChatListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + "must implement ChatFragment.ChatListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);

        //tell to ChatActivity that chatFragment is created
        mListener.onCreatedChat();

        return view;
    }

    @Override
    public void onDestroyView(){
        ButterKnife.unbind(this);
        mListener = null;

        super.onDestroyView();
    }

    @OnClick(R.id.accessChatMessageButton)
    void onClickChat(View v){
        Log.d(TAG, "onClickAccessMessageListener");
        mListener.onAccessChatMessageClicked("0");
    }

    @OnClick(R.id.accessSendMessageButton)
    public void onClickSend(){
        Log.d(TAG, "onClickAccessMessageListener");
        mListener.onAccessChatMessageClicked("1");
    }

    @OnClick(R.id.accessReceiveMessageButton)
    public void onClickReceive(){
        Log.d(TAG, "onClickAccessMessageListener");
        mListener.onAccessChatMessageClicked("2");
    }

    public interface ChatListener {
        void onAccessChatMessageClicked(String mode);
        void onCreatedChat();
    }

}
