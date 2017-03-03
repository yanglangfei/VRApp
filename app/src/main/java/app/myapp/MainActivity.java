package app.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.laifeng.sopcastsdk.configuration.AudioConfiguration;
import com.laifeng.sopcastsdk.configuration.CameraConfiguration;
import com.laifeng.sopcastsdk.configuration.VideoConfiguration;
import com.laifeng.sopcastsdk.ui.CameraLivingView;
public class MainActivity extends AppCompatActivity {
   private CameraLivingView mLFLiveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mLFLiveView= (CameraLivingView) findViewById(R.id.liveView);
        configCamera();
        videoConfig();
        audioConfig();

        mLFLiveView.start();
    }


    public  void audioConfig(){
        AudioConfiguration.Builder builder=new AudioConfiguration.Builder();
        builder.setAec(true)
        .setBps(32, 64).setFrequency(16000).setMime(AudioConfiguration.DEFAULT_MIME).
                setAacProfile(AudioConfiguration.DEFAULT_AAC_PROFILE).setAdts(AudioConfiguration.DEFAULT_ADTS).
                setChannelCount(1).setEncoding(AudioConfiguration.DEFAULT_AUDIO_ENCODING);
        AudioConfiguration config=builder.build();
        mLFLiveView.setAudioConfiguration(config);
    }



    public void videoConfig(){
        VideoConfiguration.Builder builder=new VideoConfiguration.Builder();
        builder.setSize(640,300)
                .setMime(VideoConfiguration.DEFAULT_MIME)
                .setFps(15).setBps(300, 800).setIfi(2);
        VideoConfiguration config=builder.build();
        mLFLiveView.setVideoConfiguration(config);
    }


    public void configCamera(){
        CameraConfiguration.Builder builder=new CameraConfiguration.Builder();
        builder.setOrientation(CameraConfiguration.Orientation.LANDSCAPE)
                .setFacing(CameraConfiguration.Facing.BACK)
                .setPreview(720,1280)
                .setFps(24)
                .setFocusMode(CameraConfiguration.FocusMode.TOUCH);
        CameraConfiguration config=builder.build();
        mLFLiveView.setCameraConfiguration(config);
    }




}
