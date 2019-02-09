package com.sning.mtio.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sning.mtio.Adapters.Grade_Adapter;
import com.sning.mtio.Database.cour_stuDbHelper;
import com.sning.mtio.R;

import java.util.List;
import java.util.Map;

public class Frag_Grade extends Fragment {
    private int term=0;
    public static Frag_Grade newInstance(int term) {
        Frag_Grade fragment = new Frag_Grade();
        Bundle bundle = new Bundle();
        bundle.putInt("term", term);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.term=getArguments().getInt("term");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.s_grade_page, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        Grade_Adapter grade_adapter=new Grade_Adapter();
        cour_stuDbHelper db=new cour_stuDbHelper(view.getContext());
        List<Map<String,Object>> data=db.findScoreByStuId("1000001",this.term);
        grade_adapter.setContent(data);
        recyclerView.setAdapter(grade_adapter);
        TextView term_credit=view.findViewById(R.id.s_term_credit);
        TextView term_gpa=view.findViewById(R.id.s_term_gpa);
        TextView all_credit=view.findViewById(R.id.s_all_credit);
        TextView all_gpa=view.findViewById(R.id.s_all_gpa);
        double []res,res1;
        res=db.findAllGC("1000001");
        res1=db.findTermGC("1000001",this.term);
        term_credit.setText("本学期学分:"+new java.text.DecimalFormat("#.0").format(res1[1]));
        term_gpa.setText("本学期GPA:"+new java.text.DecimalFormat("#0.00").format(res1[0]));
        all_credit.setText("课程总学分:"+new java.text.DecimalFormat("#.0").format(res[1]));
        all_gpa.setText("课程总GPA:"+new java.text.DecimalFormat("#.00").format(res[0]));
    }

}
