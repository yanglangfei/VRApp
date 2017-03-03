package app.myapp.application;

import android.app.Application;
import android.content.pm.PackageManager;
import android.widget.SlidingDrawer;

import com.alipay.euler.andfix.patch.PatchManager;

import org.xutils.x;

/**
 * Created by Administrator on 2017/1/5.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

      /*  // AndFix   热修复
        PatchManager manager=new PatchManager(this);
        try {
            String appversion= getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            manager.init(appversion); //当前版本
            manager.loadPatch();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
*/
    }
}
