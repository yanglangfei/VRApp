package app.myapp;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Toast;
import com.hustunique.parsingplayer.player.view.ParsingVideoView;

/**
 * Created by Administrator on 2017/3/6.
 *
 *   夜间模式  vs   白天模式
 */

public class ParseVideoView extends AppCompatActivity {
    private ParsingVideoView mVideoView;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parse_video);
       /* mVideoView=(ParsingVideoView)findViewById(R.id.videoView);
        mVideoView.play("http://v.youku.com/v_show/id_XOTY1MDAyNDY4.html");*/
    }

    public void onTest(View view)
    {
        Toast.makeText(this, "I AM A BUG", Toast.LENGTH_SHORT).show();
        int model=getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(model==Configuration.UI_MODE_NIGHT_YES){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mVideoView.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
       // mVideoView.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
     //   mVideoView.onDestroy();
    }
}
