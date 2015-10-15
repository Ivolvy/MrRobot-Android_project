package com.example.mgenty.mrrobot_android_project.chat;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mgenty.mrrobot_android_project.ChatActivity;
import com.example.mgenty.mrrobot_android_project.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatCommunicationFragment extends Fragment {
    private static final String TAG = "ChatCommunicationFragment";
    private ChatCommunicationListener mListener;


    public ChatCommunicationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ChatCommunicationListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + "must implement ChatCommunicationFragment.ChatCommunicationListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_communication, container, false);
        mListener.launchChatCommunication();

        return view;
    }


    public interface ChatCommunicationListener {
        void launchChatCommunication();
    }

}
