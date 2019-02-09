package com.sning.mtio.Widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.sning.mtio.R;

public class PhotoPopupWindow extends PopupWindow {
    private View view;  //PopupWindow 菜单布局
    private Context context;    //上下文参数
    private View.OnClickListener selectListener;    //相册选取的点击监听器
    private View.OnClickListener captureListener;   //拍照的点击监听器


    public PhotoPopupWindow(Context context, View.OnClickListener selectListener, View.OnClickListener captureListener) {
        this.context = context;
        this.selectListener = selectListener;
        this.captureListener = captureListener;
        Init();
    }

    private void Init(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pop_item,null);
        Button btn_camera = view.findViewById(R.id.w_icon_btn_camera);
        Button btn_select = view.findViewById(R.id.w_icon_btn_select);
        Button btn_cancel = view.findViewById(R.id.w_icon_btn_cancel);

        btn_camera.setOnClickListener(captureListener);
        btn_select.setOnClickListener(selectListener);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //导入布局
        this.setContentView(view);
        //设置动画效果
        this.setAnimationStyle(R.style.popwindow_anim_style);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置可触
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x0000000);
        this.setBackgroundDrawable(dw);
        //单击弹出窗以外处 关闭弹出窗
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.w_ll_pop).getTop();
                int y = (int) event.getY();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(y<height)
                        dismiss();
                }
                return true;
            }
        });
    }
}
