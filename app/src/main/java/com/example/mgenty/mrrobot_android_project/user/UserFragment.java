package com.example.mgenty.mrrobot_android_project.user;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    private static final String TAG = "UserFragment";
    private UserListener mListener;
    private View mView;

    private String userNameValue;
    private String userEmailValue;

    @Bind(R.id.userName) TextView mUserName;
    @Bind(R.id.userEmail) TextView mUserEmail;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (UserListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + "must implement UserFragment.UserListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, mView);

        if(userNameValue != null && userEmailValue!= null){
            mUserName.setText(userNameValue);
            mUserEmail.setText(userEmailValue);
        }

        return mView;
    }

    @Override
    public void onDestroyView(){
        ButterKnife.unbind(this);
        mListener = null;

        super.onDestroyView();
    }

    public void updateUserInformation(String userName, String userEmail) {

        if(mView != null){
            mUserName.setText(userName);
            mUserEmail.setText(userEmail);
        }
        else{
            userNameValue = userName;
            userEmailValue = userEmail;
        }
    }

    public interface UserListener {
    }

}
