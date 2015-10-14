package com.example.mgenty.mrrobot_android_project.user;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


import com.example.mgenty.mrrobot_android_project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";
    private LoginListener mListener;


    @Bind(R.id.loginLogin) TextView mLoginName;
    @Bind(R.id.passwordLogin) TextView mLoginPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (LoginListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + "must implement LoginFragment.LoginListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDestroyView(){
        ButterKnife.unbind(this);
        mListener = null;

        super.onDestroyView();
    }

    @OnClick(R.id.sendLoginButton)
    void onClick(View v){
        Log.d(TAG, "onClickSendLoginListener");
        mListener.onLoginClicked(mLoginName.getText(), mLoginPassword.getText());
    }

    public interface LoginListener {
        void onLoginClicked(CharSequence loginName, CharSequence loginPassword);
    }
}
