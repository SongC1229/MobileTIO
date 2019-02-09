package com.sning.mtio.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.sning.mtio.Activities.LoginActivity;
import com.sning.mtio.Activities.SettingActivity;
import com.sning.mtio.Adapters.NoteAdapter;
import com.sning.mtio.Database.memoDbHelper;
import com.sning.mtio.Note;
import com.sning.mtio.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;




public class UserFragment extends Fragment {


    //blurImageView磨砂背景
    private ImageView blurImageView;
    //avatarImageView圆形图像
    private ImageView avatarImageView;
    //悬浮按钮
    private FloatingActionButton fab;
    //noteAdapter适配器
    private NoteAdapter adapter;
    //notes集合
    public List<Note> notes;
    public View v;
    private SwipeMenuListView w_lv_time;

    private String userId ;
    private TextView w_tv_user_name,w_tv_user_val;
    private ProgressBar progressBar;


    public static Fragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = LoginActivity.getCurrentUser().getId();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_personal,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;
        //初始化view
        initView();
        //设置note适配器
        SetNoteAdapter();
        //进入个人设置
        setting();
        //添加note
        addNote();
        //删除note
        deleteNote();
        //侧滑删除
        NoteSwipeMenu();
        //修改note
        changeNote();
    }

    //初始化view
    public void initView(){
        blurImageView = v.findViewById(R.id.w_iv_back);
        avatarImageView = v.findViewById(R.id.w_iv_head);
        fab = v.findViewById(R.id.w_fab);
        w_lv_time = v.findViewById(R.id.w_lv_time);
        w_tv_user_name = v.findViewById(R.id.w_tv_user_name);
        w_tv_user_val = v.findViewById(R.id.w_tv_user_val);
        progressBar = v.findViewById(R.id.w_pb_log);

        w_tv_user_name.setText(LoginActivity.getCurrentUser().getName());
        w_tv_user_val.setText(LoginActivity.getCurrentUser().getClas());

        progressBar.setVisibility(View.GONE);
        //初始化头像
        glideImage();
    }

    //数据刷新
    private void refreshNote(){
        //数据源的创建
        notes = new ArrayList<Note>();
        memoDbHelper memoDb=new memoDbHelper(v.getContext());
        Note note;
        List<Map<String,Object>> list=memoDb.findMemoByUserId("1000001");
        for(int i=list.size()-1;i>=0;i--){
            int id =Integer.parseInt(String.valueOf(list.get(i).get("memo_id")));
            String note_time = list.get(i).get("time").toString();
            String note_title = list.get(i).get("getTitle").toString();
            String note_content = list.get(i).get("getCont").toString();
            note = new Note(userId,id,note_title,note_content,note_time);
            notes.add(note);
        }
        if(adapter!=null) {
            //数据源地址引用改变，会导致适配器无法刷新，重置适配器
            adapter = new NoteAdapter(v.getContext(), notes);
            w_lv_time.setAdapter(adapter);
        }

    }

    //noteAdapter的使用
    public void SetNoteAdapter(){
        refreshNote();
        //创建适配器
        adapter = new NoteAdapter(v.getContext(),notes);
        //设置适配器
        w_lv_time.setAdapter(adapter);
    }

    //跳转setting界面
    public void setting(){
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),SettingActivity.class));
            }
        });
    }

    //加载头像
    public void glideImage(){
        //图片加载
        SettingActivity settingActivity = new SettingActivity();
        String temp = settingActivity.iconPath;
        Log.i("TAG",temp+"xxxx");
        Glide.with(this).load(R.drawable.pic1).into(avatarImageView);
//                .bitmapTransform(new BlurTransformation(v.getContext(),25),new CenterCrop(v.getContext()))
//                .into(blurImageView);

        Glide.with(this).load(R.drawable.pic1).into(avatarImageView);
//                .bitmapTransform(new CropCircleTransformation(v.getContext()))
//                .into(avatarImageView);
    }

    public void addNote(){
        //悬浮按钮响应事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加note
                AlertDialog.Builder addNoteDialog = new AlertDialog.Builder(v.getContext(),R.style.s_nobackdialog);
                final View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.note_dialog,null);
                addNoteDialog.setTitle("添加备忘录");
                addNoteDialog.setView(dialogView);
                addNoteDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText title = dialogView.findViewById(R.id.w_et_title);
                        EditText content = dialogView.findViewById(R.id.w_et_content);
                        String note_title = title.getText().toString();
                        String note_content = content.getText().toString();
                        Date now = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd HH:mm");
                        String note_time = dateFormat.format(now);
//                        Note note1 = new Note(userId,12,note_title,note_content,note_time);
                        memoDbHelper myDbHeper=new memoDbHelper(dialogView.getContext());
                        myDbHeper.insertMemo(userId,note_title,note_content);
//                        notes.add(note1);
                        refreshNote();
                        progressBar.setVisibility(View.VISIBLE);
                    }
                });
                addNoteDialog.show();
                //Toast.makeText(view.getContext(),"fab test",Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void deleteNote(){
        //id获取
        w_lv_time.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int note_id = notes.get(position).getId();
                memoDbHelper myDbHeper=new memoDbHelper(v.getContext());
                myDbHeper.delById(note_id);
                notes.remove(position);
                adapter.notifyDataSetChanged();
                //Toast.makeText(v.getContext(),"删除"+notes.get(position).title,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void changeNote(){
        w_lv_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder changeNoteDialog = new AlertDialog.Builder(v.getContext(),R.style.s_nobackdialog);
                final View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.note_dialog,null);

                final EditText title = dialogView.findViewById(R.id.w_et_title);
                final EditText content = dialogView.findViewById(R.id.w_et_content);
                final int note_id = notes.get(position).getId();
                final String note_title = notes.get(position).getTitle();
                final String note_content = notes.get(position).getContent();
                title.setText(note_title);
                content.setText(note_content);

                changeNoteDialog.setTitle("修改备忘录");
                changeNoteDialog.setView(dialogView);
                changeNoteDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String temp_title = title.getText().toString();
                        String temp_content = content.getText().toString();
                        memoDbHelper myDbHeper=new memoDbHelper(dialogView.getContext());
                        myDbHeper.updateMemo(note_id,temp_title,temp_content);
                        refreshNote();
                        onResume();
                        progressBar.setVisibility(View.VISIBLE);
                    }
                });
                changeNoteDialog.show();
            }
        });
    }

    //删除侧滑键
    public void NoteSwipeMenu(){
        SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem topItem = new SwipeMenuItem(v.getContext());
                topItem.setBackground(R.color.background_gray);
                topItem.setTitle("置顶");
                topItem.setTitleColor(Color.WHITE);
                topItem.setWidth(250);
                topItem.setTitleSize(18);
                menu.addMenuItem(topItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(v.getContext());
                deleteItem.setBackground(R.color.colorAccent);
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setWidth(250);
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                menu.addMenuItem(deleteItem);
            }
        };
        w_lv_time.setMenuCreator(swipeMenuCreator);

        w_lv_time.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        Toast.makeText(v.getContext(),"看起来有点复杂，就不写了...",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        int note_id = notes.get(position).getId();
                        memoDbHelper myDbHeper=new memoDbHelper(v.getContext());
                        myDbHeper.delById(note_id);
                        notes.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
    }

}
