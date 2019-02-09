package com.sning.mtio.Widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.sning.mtio.R;

public class Alter_Dialog extends Dialog {


    /**
     * 上下文对象 *
     */
    private Context context;

    private String data[];
    public EditText text_name;
    public EditText text_teacher;
    public EditText text_room;
    public EditText text_start;
    public EditText text_end;

    public int row,col;

    private View.OnClickListener mClickListener;

    public Alter_Dialog(Context context, int theme, View.OnClickListener clickListener, int r, int c, String [] data) {
        super(context, theme);
        this.context = context;
        this.mClickListener = clickListener;
        this.row=r;
        this.col=c;
        this.data=data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.s_alter_dialog);
        text_name =findViewById(R.id.s_edit_name);
        text_teacher =findViewById(R.id.s_edit_teacher);
        text_room = findViewById(R.id.s_edit_room);
        text_start=findViewById(R.id.s_edit_startweek);
        text_end=findViewById(R.id.s_edit_endweek);

        text_name.setText(data[0]);
        text_teacher.setText(data[1]);
        text_room.setText(data[2]);
        text_start.setText(data[3]);
        text_end.setText(data[4]);
        text_end.setInputType( InputType.TYPE_CLASS_NUMBER);
        text_start.setInputType( InputType.TYPE_CLASS_NUMBER);
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
//        int screenHeight = dm.heightPixels;

        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (screenWidth * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);
        // 根据id在布局中找到控件对象
        Button btn_save = findViewById(R.id.s_btn_save_course);
        Button btn_cancel= findViewById(R.id.s_btn_cancel);
        Button btn_delete=findViewById(R.id.s_btn_delete_course);

        // 为按钮绑定点击事件监听器
        btn_save.setOnClickListener(mClickListener);
        btn_cancel.setOnClickListener(mClickListener);
        btn_delete.setOnClickListener(mClickListener);
        if(!data[5].equals("1")){
            text_name.setEnabled(false);
            text_name.setFocusable(false);
            text_name.setKeyListener(null);
            text_teacher.setEnabled(false);
            text_teacher.setFocusable(false);
            text_teacher.setKeyListener(null);
            text_room.setEnabled(false);
            text_room.setFocusable(false);
            text_room.setKeyListener(null);
            text_start.setEnabled(false);
            text_start.setFocusable(false);
            text_start.setKeyListener(null);
            text_end.setEnabled(false);
            text_end.setFocusable(false);
            text_end.setKeyListener(null);
            btn_delete.setEnabled(false);
            btn_save.setEnabled(false);
        }
        this.setCancelable(true);
    }

}
