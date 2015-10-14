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
public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";
    private RegisterListener mListener;


    @Bind(R.id.loginRegister) TextView mRegisterName;
    @Bind(R.id.passwordRegister) TextView mRegisterPassword;
    @Bind(R.id.emailRegister) TextView mRegisterEmail;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (RegisterListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + "must implement LoginFragment.LoginListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView(){
        ButterKnife.unbind(this);
        mListener = null;

        super.onDestroyView();
    }

    @OnClick(R.id.sendRegisterButton)
    void onClick(View v){
        Log.d(TAG, "onClickRegisterLoginListener");
        mListener.onRegisterClicked(mRegisterName.getText(), mRegisterPassword.getText(), mRegisterEmail.getText());
    }


    public interface RegisterListener {
        void onRegisterClicked(CharSequence registerName, CharSequence registerPassword, CharSequence registerEmail);
    }

}
