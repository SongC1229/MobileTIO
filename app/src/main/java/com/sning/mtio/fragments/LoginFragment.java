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
import android.widget.TextView;
import android.widget.Toast;

import com.sning.mtio.Activities.LoginActivity;
import com.sning.mtio.Database.Student;
import com.sning.mtio.Database.userDbhelper;
import com.sning.mtio.R;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private EditText etUserName;
    private EditText etUserPassword;
    private AppCompatButton  btnLogin;
    private TextView tvLinkSignUp;
    private TextView tvResetPwd;

    public CallBackValue callBackValue;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue = (CallBackValue) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        etUserName = view.findViewById(R.id.z_input_name_login);
        etUserPassword = view.findViewById(R.id.z_input_password_login);
        btnLogin = view.findViewById(R.id.z_btn_launch);
        btnLogin.setOnClickListener(this);
        tvLinkSignUp = view.findViewById(R.id.z_tv_link_signup);
        tvLinkSignUp.setOnClickListener(this);
        tvResetPwd = view.findViewById(R.id.z_tv_reset_password);
        tvResetPwd.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.z_btn_launch:{
                String id = etUserName.getText().toString();
                String pwd = etUserPassword.getText().toString();
                if (id.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(getContext(),"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                } else {
                    //从数据库匹配用户名密码
                    userDbhelper userDbhelper = new userDbhelper(getContext());
                    int result = userDbhelper.judgeUser(id,pwd);
                    switch (result) {
                        case -1:{ //用户不存在
                            Toast.makeText(getContext(),"用户不存在",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case 0:{ //密码错误
                            Toast.makeText(getContext(),"密码错误",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case 1:{ //登陆成功
                            Student student = userDbhelper.selectById(id);
                            LoginActivity.setCurrentUser(student);
                            String value = "LOGIN_SUCCEED";
                            callBackValue.SendMessageValue(value);
                            break;
                        }
                        default:
                            break;
                    }

                }
                break;
            }
            case R.id.z_tv_link_signup:{
                String value = "GOTO_SIGN_UP";
                callBackValue.SendMessageValue(value);
                break;
            }
            case R.id.z_tv_reset_password:{
                Toast.makeText(getContext(),"暂未开放",Toast.LENGTH_SHORT).show();
                break;
            }
            default:
                break;
        }

    }


    public interface CallBackValue {
        void SendMessageValue(String value);
    }
}
