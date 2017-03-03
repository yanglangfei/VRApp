package app.myapp.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017/2/28.
 *
 *   Activity 管理器
 */

public class ActivityManager {

    private static List<Activity>   activities=new ArrayList<>();


    /**
     * @param activity
     *
     *     新增activity
     */
    public static void addActivity(Activity activity){
        if(activity!=null){
            activities.add(activity);
        }
    }


    /**
     * @param activity
     *
     *     移除activity
     */
    public static void  removeActivity(Activity activity){
        if(activity!=null&&activities.contains(activity)){
            activities.remove(activity);
        }
    }

    /**
     *     关闭所有activity
     */
    public  static void  finish(){
        for (Activity activity : activities) {
            activity.finish();
        }
    }


}
