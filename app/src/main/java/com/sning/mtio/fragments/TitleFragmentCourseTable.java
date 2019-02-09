package com.sning.mtio.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.sning.mtio.R;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

public class TitleFragmentCourseTable extends Fragment implements
        AdapterView.OnItemSelectedListener, View.OnClickListener{

    private NiceSpinner spinnerWeek;
    private ImageView ivMore;
    private LoginFragment.CallBackValue callBackValue;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue = (LoginFragment.CallBackValue)getActivity();
    }

    public static TitleFragmentCourseTable newInstance() {
        TitleFragmentCourseTable fragment = new TitleFragmentCourseTable();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_table_title_layout,container,false);

        spinnerWeek = view.findViewById(R.id.z_spinner);
        List<String> dataSet = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataSet.add("第" + (i+1) + "周");
        }
        spinnerWeek.attachDataSource(dataSet);
        spinnerWeek.setSelectedIndex(0);
        spinnerWeek.setOnItemSelectedListener(this);

        ivMore = view.findViewById(R.id.z_iv_menu);
        ivMore.setOnClickListener(this);

        return view;
    }

    /**
     * 下拉选项框选中
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //if (view.getId() == R.id.z_spinner) {
        Toast.makeText(getContext(),"用户选中了" + String.valueOf(position + 1),Toast.LENGTH_SHORT).show();
        String value = String.valueOf(position+ 1 + 10);
        callBackValue.SendMessageValue(value);

        //}
    }

    /**
     * 下拉选项框不选中
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.z_iv_menu) {
            Toast.makeText(getContext(),"用户点击了菜单按钮",Toast.LENGTH_SHORT).show();
        }
    }
}
