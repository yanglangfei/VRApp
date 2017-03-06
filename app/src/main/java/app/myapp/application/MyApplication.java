package app.myapp.application;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import org.xutils.x;

/**
 * Created by Administrator on 2017/1/5.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //默认设置一直使用夜间模式
        /* MODE_NIGHT_NO. Always use the day (light) theme(一直应用日间(light)主题).
        * MODE_NIGHT_YES. Always use the night (dark) theme(一直使用夜间(dark)主题).
        * MODE_NIGHT_AUTO. Changes between day/night based on the time of day(根据当前时间在day/night主题间切换).
        * MODE_NIGHT_FOLLOW_SYSTEM(默认选项). This setting follows the system’s setting, which is essentially MODE_NIGHT_NO(跟随系统，通常为MODE_NIGHT_NO).
        */
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }
}
