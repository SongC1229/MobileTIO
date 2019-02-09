package com.sning.mtio.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sning.mtio.R;


public class TitleFragmentMoreAndUser extends Fragment implements View.OnClickListener{

    private TextView tvTitle;
    private ImageView ivSetting;
    private int type = -1;
    private LoginFragment.CallBackValue callBackValue;


    public static TitleFragmentMoreAndUser newInstance(int type) {
        TitleFragmentMoreAndUser fragment = new TitleFragmentMoreAndUser();
        Bundle args = new Bundle();
        args.putString("type",String.valueOf(type));
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue = (LoginFragment.CallBackValue)getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_user_title_layout,container,false);
        tvTitle = view.findViewById(R.id.z_tv_title_more_user);
        ivSetting = view.findViewById(R.id.z_iv_setting);
        ivSetting.setOnClickListener(this);

        Bundle bundle = getArguments();
        type = Integer.valueOf(bundle.getString("type"));

        if (type == 0){
            tvTitle.setText("首页");
            ivSetting.setImageResource(R.drawable.menu);
        } else if (type == 2){
            tvTitle.setText("个人主页");
            ivSetting.setImageResource(R.drawable.settings);
        } else if (type == 3){
            tvTitle.setText("新闻列表");
        } else if (type == 4) {
            tvTitle.setText("公告列表");
        } else {
            tvTitle.setText("--");
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.z_iv_setting) {
            callBackValue.SendMessageValue(String.valueOf(6));
        }
    }
}
