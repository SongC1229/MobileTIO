package com.sning.mtio.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sning.mtio.Adapters.NewsInfoAdapter;
import com.sning.mtio.Database.newsDbHelper;
import com.sning.mtio.NewsInfo;
import com.sning.mtio.R;

import java.util.ArrayList;
import java.util.List;

public class NewsInfoFragment extends Fragment{

    public static final int TYPE_NEWS = 1;
    public static final int TYPE_INFO = 2;
    private Context context;
    private LoginFragment.CallBackValue callBackValue;

    private List<NewsInfo> newsInfoList = new ArrayList<>();
    private newsDbHelper newsDbHelper;
    private NewsInfoAdapter adapter;

    public static NewsInfoFragment newInstance(int type) {
        NewsInfoFragment fragment = new NewsInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE",type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue = (LoginFragment.CallBackValue) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        Bundle bundle = getArguments();
        newsDbHelper = new newsDbHelper(getActivity());

        int type = bundle.getInt("TYPE");

        initNewsInfoList(type);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_info_list,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.z_rv_news_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewsInfoAdapter(newsInfoList);
        adapter.setOnItemClickListener(new NewsInfoAdapter.OnItemClickListener() {
            @Override
            public void onCLick(int position) {
                String value = newsInfoList.get(position).getUrl();
                callBackValue.SendMessageValue(value);
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initNewsInfoList(int type) {
        switch (type) {
            case TYPE_NEWS:{
                //从数据库中获取新闻列表
                newsInfoList = newsDbHelper.getAllNews();
                break;
            }
            case TYPE_INFO:{
                //从数据库中获取公告列表
                newsInfoList = newsDbHelper.getAllInfo();
                break;
            }
            default:
                break;
        }

    }
}
