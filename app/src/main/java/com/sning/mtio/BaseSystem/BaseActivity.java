package com.sning.mtio.BaseSystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    private long firstTime = 0;         //用户第一次点击退出键时间

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity",getClass().getSimpleName() + "create");
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("BaseActivity",getClass().getSimpleName() + "destroy");
        ActivityCollector.removeActivity(this);
    }

    /**
     * 作用：当用户在2秒内点击两次返回键时退出程序
     * 作者：z
     * 最后修改时间：12.28 16:00
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {         //第一次点击返回键
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {                                                     //两秒内第二次点击
                ActivityCollector.finishAll();
                Log.d("BaseActivity--","finish");
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }




}
