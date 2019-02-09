package com.sning.mtio.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sning.mtio.NewsInfo;
import com.sning.mtio.R;

import java.util.List;

public class NewsInfoAdapter extends RecyclerView.Adapter<NewsInfoAdapter.ViewHolder> {

    private Context context;
    private List<NewsInfo> newsInfoList;

    private OnItemClickListener mOnItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvTitle;
        TextView tvContent;
        TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            tvTitle = itemView.findViewById(R.id.z_tv_news_info_item_title);
            tvContent = itemView.findViewById(R.id.z_tv_news_info_item_content);
            tvTime = itemView.findViewById(R.id.z_tv_news_info_item_time);
        }
    }

    public NewsInfoAdapter(List<NewsInfo> newsInfoList) {
        this.newsInfoList = newsInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.news_info_item,viewGroup,false);
        return new ViewHolder(view);
    }

    public interface OnItemClickListener{
        void onCLick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        NewsInfo newsInfo = newsInfoList.get(position);
        holder.tvTitle.setText(newsInfo.getTitle());
        holder.tvContent.setText(newsInfo.getUrl());
        String time = newsInfo.getAuthor() + "    " + newsInfo.getTime();
        holder.tvTime.setText(time);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onCLick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return newsInfoList.size();
    }
}
