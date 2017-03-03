package app.myapp;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
/**
 * Created by Administrator on 2017/3/3.
 */
public class VideoActivity extends AppCompatActivity {
    private JCVideoPlayerStandard jv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();

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
    }

    private void initView() {
        jv= (JCVideoPlayerStandard) findViewById(R.id.jv);
        jv.setUp("http://hdl-w.quklive.com/live/w1469002576632934.flv", JCVideoPlayer.SCREEN_LAYOUT_NORMAL,"title");
        jv.thumbImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.test));
        jv.startVideo();



    }
}
