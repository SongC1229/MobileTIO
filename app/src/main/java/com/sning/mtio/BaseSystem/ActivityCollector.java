package com.sning.mtio.BaseSystem;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {


    public static List<Activity> activityList = new ArrayList<>();

    /**
     * 作用：向ActivityList添加指定的Activity，当activity执行onCreate()时调用
     * 作者：z
     * 最后修改时间：12.28 16:22
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }


    /**
     * 作用：从ActivityList中销毁指定的Activity，当activity执行onFinish()时调用
     * 作者：z
     * 最后修改时间：12.28 15：30
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }


    /**
     * 作用：销毁所有的Activity,应用退出时调用
     * 作者：z
     * 最后修改时间：12.28 15：33
     */
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
