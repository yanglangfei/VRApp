package app.myapp.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */

public class AppInfo {

    /**
     * @param context
     * @deprecated  获取手机运行的所有service
     */
    public  List<ActivityManager.RunningServiceInfo> getRuningService(Context context){
        ActivityManager am= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(3);
        for (ActivityManager.RunningServiceInfo runningService : runningServices) {
            String serviceClass = runningService.service.getClassName();
            String packageName = runningService.service.getPackageName();
            String shortClassName = runningService.service.getShortClassName();
        }
        return  runningServices;
    }


    public  List<PackageInfo>   getInstallPackage(Context context) throws PackageManager.NameNotFoundException {
        PackageManager pm=context.getPackageManager();
        List<PackageInfo> installedApplications = pm.getInstalledPackages(0);
        for (PackageInfo installedApplication : installedApplications) {
            Drawable applicationIcon = pm.getApplicationIcon(installedApplication.packageName);
            Drawable applicationLogo = pm.getApplicationLogo(installedApplication.packageName);
            CharSequence applicationLabel = pm.getApplicationLabel(installedApplication.applicationInfo);
        }
        return   installedApplications;
    }


}
