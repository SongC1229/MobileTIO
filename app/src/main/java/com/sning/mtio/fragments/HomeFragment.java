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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sning.mtio.Activities.LoginActivity;
import com.sning.mtio.Database.newsDbHelper;
import com.sning.mtio.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class HomeFragment extends Fragment implements View.OnClickListener{

    private TextView tvWelcomeName;
    private TextView tvWelcomeWeather;
    private ImageView ivWelcomeWeather;
    private int[] imageList = new int[] {R.drawable.viewpic1,R.drawable.pic2,R.drawable.pic3};
    private CarouselView cvBanner;
    private ImageListener imageListener;
    private ImageView ivGrade;
    private RelativeLayout rlNewsModuleTitle;
    private TextView tvNewsTitle;
    private RelativeLayout rlInfoModuleTitle;
    private TextView tvInfoTitle;
    private newsDbHelper newsDbHelper;
    private LoginFragment.CallBackValue callBackValue;


    public static Fragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue = (LoginFragment.CallBackValue)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        newsDbHelper = new newsDbHelper(getContext());
        initView(view);
        return view;
    }


    private void initView(View view) {

        //获取用户姓名，天气
        String name = LoginActivity.getCurrentUser().getName();
        String weather = "阴";
        String degree = "7";
        int imageID = 0;
        //根据天气选择天气图片ID
        if (weather.equals("晴"))
            imageID = R.mipmap.sunny;
        else if (weather.equals("多云"))
            imageID = R.mipmap.cloudy;
        else if (weather.equals("阴"))
            imageID = R.mipmap.cloud;
        else if (weather.equals("雨"))
            imageID = R.mipmap.rain;
        else if (weather.equals("雪"))
            imageID = R.mipmap.snow;


        tvWelcomeName = view.findViewById(R.id.z_tv_welcome_name);
        tvWelcomeName.setText("欢迎您，" + name + "同学");

        tvWelcomeWeather = view.findViewById(R.id.z_tv_welcome_weather);
        tvWelcomeWeather.setText("天气：" + weather + "，" + degree + "℃");

        ivWelcomeWeather = view.findViewById(R.id.z_iv_welcome_weather);
        ivWelcomeWeather.setImageResource(imageID);

        cvBanner = (CarouselView) view.findViewById(R.id.z_cv_banner);
        cvBanner.setPageCount(imageList.length);

        imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(imageList[position]);
            }
        };

        cvBanner.setImageListener(imageListener);
        ivGrade = view.findViewById(R.id.z_iv_grade);
        ivGrade.setOnClickListener(this);

        rlNewsModuleTitle = view.findViewById(R.id.z_rl_news_module_title);
        rlNewsModuleTitle.setOnClickListener(this);

        tvNewsTitle = view.findViewById(R.id.z_tv_news_title);
        tvNewsTitle.setText(newsDbHelper.lastNews().getTitle());
        tvNewsTitle.setOnClickListener(this);

        rlInfoModuleTitle = view.findViewById(R.id.z_rl_info_module_title);
        rlInfoModuleTitle.setOnClickListener(this);

        tvInfoTitle = view.findViewById(R.id.z_tv_info_title);
        tvInfoTitle.setText(newsDbHelper.lastInfo().getTitle());
        tvInfoTitle.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.z_iv_grade:{
                callBackValue.SendMessageValue("1");
                break;
            }
            case R.id.z_rl_news_module_title:{
                //进入新闻列表页
                callBackValue.SendMessageValue("2");
                break;
            }
            case R.id.z_tv_news_title:{
                //进入新闻详情页
                callBackValue.SendMessageValue("3");
                break;
            }
            case R.id.z_rl_info_module_title:{
                //进入公告列表页
                callBackValue.SendMessageValue("4");
                break;
            }
            case R.id.z_tv_info_title:{
                //进入公告详情页
                callBackValue.SendMessageValue("5");
                break;
            }
            default:
                break;
        }


    }
}
