package com.sning.mtio.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sning.mtio.Note;
import com.sning.mtio.R;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Note> notes;

    public NoteAdapter(Context context, List<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }


    public final class ViewHolder{  //自定义控件集合
        public TextView w_tv_note_data;
        public ImageView w_iv_note_icon;
        public TextView w_tv_note_title;
        public TextView w_tv_note_content;
    }

    @Override
    public int getCount() {
        return  notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_time,parent,false);
            //加载布局
            viewHolder = new ViewHolder();
            viewHolder.w_iv_note_icon = convertView.findViewById(R.id.w_iv_note_icon);
            viewHolder.w_tv_note_data = convertView.findViewById(R.id.w_tv_note_data);
            viewHolder.w_tv_note_title = convertView.findViewById(R.id.w_tv_note_title);
            viewHolder.w_tv_note_content = convertView.findViewById(R.id.w_tv_note_content);

            convertView.setTag(viewHolder);
        }else{//说明convertView已经被复用，说明convertView中已经设置过viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Note data = notes.get(position);
        viewHolder.w_tv_note_data.setText(data.getDate());
        viewHolder.w_tv_note_title.setText(data.getTitle());
        viewHolder.w_tv_note_content.setText(data.getContent());

        return convertView;
    }

}
