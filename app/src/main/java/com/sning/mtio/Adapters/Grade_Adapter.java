package com.sning.mtio.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sning.mtio.R;
import java.util.List;
import java.util.Map;


public class Grade_Adapter extends RecyclerView.Adapter<Grade_Adapter.GradeViewHolder> {

    private List<Map<String,Object>> contents;
    @Override
    public GradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.s_grade_item, parent, false);
        GradeViewHolder viewHolder = new GradeViewHolder(childView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GradeViewHolder holder, int position) {
        String[] data=getItem(position);
        holder.grade_name.setText(data[0]);
        holder.grage_type.setText(data[1].equals("-3")||data[1].equals("0")?"必修":"选修");
        holder.grade_credit.setText(data[2]);
        holder.grade_grade.setText(data[3]);

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
    public String [] getItem(int position) {
        String [] data =new String [4];
        data[0]=contents.get(position).get("courName").toString();
        data[1]=contents.get(position).get("type").toString();
        data[2]=contents.get(position).get("courCred").toString();
        data[3]=contents.get(position).get("score").toString();
        return data;
    }

    public void setContent(List<Map<String,Object>> contents) {
        this.contents = contents;
    }

    class GradeViewHolder extends RecyclerView.ViewHolder {
        TextView grade_name;
        TextView grade_credit;
        TextView grade_grade;
        TextView grage_type;
        public GradeViewHolder(View itemView) {
            super(itemView);
            grade_name =  itemView.findViewById(R.id.s_grade_name);
            grade_credit =  itemView.findViewById(R.id.s_grade_credit);
            grade_grade=itemView.findViewById(R.id.s_grade_grade);
            grage_type=itemView.findViewById(R.id.s_grade_type);
        }
    }
}