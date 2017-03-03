package app.myapp;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.aplayer.aplayerandroid.APlayerAndroid;

import app.myapp.util.Constant;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
/**
 * Created by Administrator on 2017/3/3.
 *
 *   JCVideo   VS     APlayer
 *
 */
public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private JCVideoPlayerStandard jv;
    private SurfaceView sv;
    private APlayerAndroid     aplyer;
    private SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initJCView();
        initAPlayer();
    }

    private void initAPlayer() {
        sv= (SurfaceView) findViewById(R.id.sv);
        aplyer=new APlayerAndroid();
        //是否使用系统播放器   不建议使用
     //   aplyer.useSystemPlayer(false);
        aplyer.setConfig(APlayerAndroid.CONFIGID.AUTO_PLAY,"0");
        aplyer.setView(sv);
        //创建  VR View
      //  View vr = aplyer.createVRView(this);
        aplyer.setOnOpenSuccessListener(new APlayerAndroid.OnOpenSuccessListener() {
            @Override
            public void onOpenSuccess() {
                Toast.makeText(VideoActivity.this, "打开成功", Toast.LENGTH_SHORT).show();
            }
        });


        holder = sv.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jv.release();
        JCVideoPlayerStandard.releaseAllVideos();

        aplyer.close();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        aplyer.destroy();
    }

    private void initJCView() {
        jv= (JCVideoPlayerStandard) findViewById(R.id.jv);
        //显示 ActionBar
        jv.hideSupportActionBar(this);
        //显示   亮度调节Dialog
       // jv.showBrightnessDialog(30);
        //显示  声音调节Dialog
        //jv.showVolumeDialog(22,10);
        //显示 网络提示Dialog
        // jv.showWifiDialog();
        jv.setUp(Constant.FC, JCVideoPlayer.SCREEN_LAYOUT_NORMAL,"title");
        jv.thumbImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.test));
        jv.setProgressAndText();
        //重新设置标题
        jv.titleTextView.setText("change   title");
        jv.startVideo();
        jv.autoFullscreen(2);

     /*   jv.fullscreenButton.performClick();
        jv.startButton.performClick();
*/

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //  打开视频    成功 0   失败   -1
        int open=aplyer.open(Constant.FC);
        Log.i("111","open:"+open);
        int play= aplyer.play();




        aplyer.setOnBufferListener(new APlayerAndroid.OnBufferListener() {
            @Override
            public void onBuffer(int i) {
                aplyer.play();
                Log.i("111","     state:"+aplyer.getState());
            }
        });
        /*if(open==0){
            //开始播放   0  成功     -1  失败
           int play= aplyer.play();

          *//*  //暂停
            int pause = aplyer.pause();
            //获取当前状态
            int state = aplyer.getState();*//*
        }*/

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
