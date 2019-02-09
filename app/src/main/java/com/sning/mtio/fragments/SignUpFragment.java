package com.sning.mtio.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sning.mtio.Activities.LoginActivity;
import com.sning.mtio.Database.Student;
import com.sning.mtio.Database.userDbhelper;
import com.sning.mtio.R;

public class SignUpFragment extends Fragment implements View.OnClickListener{
    private EditText etUserId,etUserName,etUserPassword,etUserConfirmPassword;
    private AppCompatButton btnSignup;
    private ImageView ivBack;

    public LoginFragment.CallBackValue callBackValue;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue = (LoginFragment.CallBackValue) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup,container,false);

        etUserId = view.findViewById(R.id.z_input_id_signup);
        etUserName = view.findViewById(R.id.z_input_name_signup);
        etUserPassword = view.findViewById(R.id.z_input_password_signup);
        etUserConfirmPassword = view.findViewById(R.id.z_confirm_password_signup);
        btnSignup = view.findViewById(R.id.z_btn_signup);
        btnSignup.setOnClickListener(this);
        ivBack = view.findViewById(R.id.z_iv_back_sign_up);
        ivBack.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.z_btn_signup) {

            String id = etUserId.getText().toString();
            String name = etUserName.getText().toString();
            String pwd = etUserPassword.getText().toString();
            String pwdC = etUserConfirmPassword.getText().toString();

            if (id.isEmpty() || name.isEmpty() || pwd.isEmpty() || pwdC.isEmpty()) {
                Toast.makeText(getContext(),"内容不能为空！",Toast.LENGTH_SHORT).show();
            } else if (!pwd.equals(pwdC)) {
                Toast.makeText(getContext(),"两次输入的密码不同",Toast.LENGTH_SHORT).show();
            } else {

                userDbhelper userDbhelper = new userDbhelper(getContext());
                int result = userDbhelper.insertUser(id,name,pwd);

                switch (result) {
                    case -1:{
                        Toast.makeText(getContext(),"学号不存在",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 0:{
                        Toast.makeText(getContext(),"学号与姓名不匹配",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1:{
                        callBackValue.SendMessageValue("SIGN_UP_SUCCEED");
                        Student student = userDbhelper.selectById(id);
                        LoginActivity.setCurrentUser(student);
                        break;
                    }
                    default:
                        break;
                }
            }

        } else if (v.getId() == R.id.z_iv_back_sign_up) {
            callBackValue.SendMessageValue("CANCEL_SIGN_UP");
        }
    }


}
