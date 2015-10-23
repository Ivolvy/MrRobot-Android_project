package com.example.mgenty.mrrobot_android_project.communication;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mgenty.mrrobot_android_project.HomeActivity;
import com.example.mgenty.mrrobot_android_project.R;
import com.example.mgenty.mrrobot_android_project.crypto.Secret;
import com.example.mgenty.mrrobot_android_project.user.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerViewAdapter;
import com.tozny.crypto.android.AesCbcWithIntegrity;

import static com.example.mgenty.mrrobot_android_project.crypto.Crypto.cipherMessage;
import static com.example.mgenty.mrrobot_android_project.crypto.Crypto.decipherMessage;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";
    private ListListener mListener;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ValueEventListener mConnectedListener;

    private Firebase mFirebaseRef;
    private String mUserName = "Default";
    private String mSecretPassword = "";

    private static String chatMode = "";

    private FirebaseRecyclerViewAdapter<Chat, ChatHolder> mAdapter;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ListListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + "must implement ListFragment.ListListener");
        }

    }

    @Override
    public void onDestroyView() {
        mListener = null;

        super.onDestroyView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //retrieve chatMode (chat, sent messages, received messages)
        chatMode = CommunicationActivity.chatMode;

        //retrieve the current User
        User user = HomeActivity.getUser();
        mUserName = user.getName();

        Secret secret = CommunicationActivity.getSecret();
        mSecretPassword = secret.getPassword();

        mFirebaseRef = new Firebase("https://androidmrrobot.firebaseio.com/").child("chat");

        final EditText inputText = (EditText) view.findViewById(R.id.messageInput);

        view.findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cipher the message
                AesCbcWithIntegrity.CipherTextIvMac cryptedMessage = cipherMessage(inputText.getText().toString(), mSecretPassword);
                Chat chat = new Chat(mUserName, cryptedMessage);

                mFirebaseRef.push().setValue(chat, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1); //set to bottom the scrollbar
                        if (firebaseError != null) {
                            Log.e("FirebaseUI.chat", firebaseError.toString());
                        }
                    }
                });

                inputText.setText("");
            }
        });

        // Finally, a little indication of connection status
        mConnectedListener = mFirebaseRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    Toast.makeText(getActivity(), "Connected to Firebase", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // No-op
            }
        });


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        setChangeTextsEvent();

        loadTexts();

        return view;
    }


    //set to bottom the scrollbar each time a value is added and on initialization
    public void setChangeTextsEvent() {
        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    /*Load all the texts in database*/
    public void loadTexts() {
        FirebaseRecyclerViewAdapter<Chat, ChatHolder> adapter = new FirebaseRecyclerViewAdapter<Chat, ChatHolder>(Chat.class, android.R.layout.two_line_list_item, ChatHolder.class, mFirebaseRef) {
            @Override
            public void populateViewHolder(ChatHolder chatView, Chat chat) {

                if(chatMode.equals("0")) {
                   writeText(chatView, chat);
                }
                else if(chat.getAuthor().equals(mUserName) && chatMode.equals("1")) {
                    writeText(chatView, chat);
                }
                else if(!chat.getAuthor().equals(mUserName) && chatMode.equals("2")) {
                    writeText(chatView, chat);
                }
            }
        };
        mAdapter = adapter;
        mRecyclerView.setAdapter(adapter);
    }

    //write text in the chat
    public void writeText(ChatHolder chatView, Chat chat){
        //retrieve the string message to decrypt
        AesCbcWithIntegrity.CipherTextIvMac ciphertext = new AesCbcWithIntegrity.CipherTextIvMac(chat.getMessage());
        String readableMessage = decipherMessage(ciphertext, mSecretPassword);

        chatView.textView.setText(readableMessage);
        chatView.textView.setPadding(10, 0, 10, 0);
        chatView.nameView.setText(chat.getAuthor());
        chatView.nameView.setPadding(10, 0, 10, 15);
        if (chat.getAuthor().equals(mUserName)) {
            chatView.textView.setGravity(Gravity.END);
            chatView.nameView.setGravity(Gravity.END);
            chatView.nameView.setTextColor(Color.parseColor("#8BC34A"));
        } else {
            chatView.nameView.setTextColor(Color.parseColor("#00BCD4"));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseRef.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
    }

    public interface ListListener {
    }

}
