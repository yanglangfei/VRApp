package app.myapp.base;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017/2/27.
 */
public class BaseActivity extends Activity {
    private List<View> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onResume() {
        super.onResume();
        //注册监听 通用广播

    }

    private List<View> initView(int ids[]) {
        views.clear();
        for (int id : ids) {
            View view = findViewById(id);
            views.add(view);
        }
        return views;
    }


    @Override
    protected void onPause() {
        super.onPause();
        //解除监听 通用广播

    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }
}
