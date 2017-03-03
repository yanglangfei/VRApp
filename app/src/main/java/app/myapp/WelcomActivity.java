package app.myapp;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;
import java.io.InputStream;

public class WelcomActivity extends AppCompatActivity {
    private  VrPanoramaView vrView;
    private  LoadPanoramaImageTask mLoadPanoramaImageTask;
    private VrVideoView vrVideo;
    private  String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        initView();
}

    private void initView() {
        index=getIntent().getStringExtra("index");
        vrView= (VrPanoramaView) findViewById(R.id.vrView);
        vrVideo= (VrVideoView) findViewById(R.id.vrVideo);

        vrView.setEventListener(new VrPanoramaEventListener(){
            @Override
            public void onLoadSuccess() {
                super.onLoadSuccess();
            }

            @Override
            public void onLoadError(String errorMessage) {
                super.onLoadError(errorMessage);
            }

            @Override
            public void onClick() {
                super.onClick();
            }

            @Override
            public void onDisplayModeChanged(int newDisplayMode) {
                super.onDisplayModeChanged(newDisplayMode);
            }
        });

        vrVideo.setEventListener(new VrVideoEventListener(){
            @Override
            public void onCompletion() {
                super.onCompletion();
                Log.i("111","complete");
            }

            @Override
            public void onNewFrame() {
                super.onNewFrame();
                Log.i("111","next frame");
            }

            @Override
            public void onLoadSuccess() {
                super.onLoadSuccess();
                Log.i("111","success");
            }

            @Override
            public void onLoadError(String errorMessage) {
                Log.i("111","载入失败："+errorMessage);
            }

            @Override
            public void onClick() {
                super.onClick();
                Log.i("111","click");
            }

            @Override
            public void onDisplayModeChanged(int newDisplayMode) {
                super.onDisplayModeChanged(newDisplayMode);
                Log.i("111","model change");
            }
        });

        mLoadPanoramaImageTask=new LoadPanoramaImageTask();
        mLoadPanoramaImageTask.execute();

    }

    @Override
    protected void onPause() {
        super.onPause();
        vrView.pauseRendering();
        vrVideo.pauseRendering();
    }

    @Override
    protected void onResume() {
        super.onResume();
       vrView.resumeRendering();
        vrVideo.resumeRendering();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        vrView.shutdown();
        vrVideo.shutdown();
        if (mLoadPanoramaImageTask != null) {
            mLoadPanoramaImageTask.cancel(true);
        }
    }

    private  class  LoadPanoramaImageTask extends AsyncTask<Void,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                AssetManager assetManager = getAssets();
                InputStream open = assetManager.open(index);
                return BitmapFactory.decodeStream(open);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            VrPanoramaView.Options options=new VrPanoramaView.Options();
            //图片类型为立体图像
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            vrView.loadImageFromBitmap(bitmap, options);




            /*try {
                VrVideoView.Options options1=new VrVideoView.Options();
                options1.inputType=VrVideoView.Options.TYPE_MONO;
                vrVideo.loadVideoFromAsset("file.mp4",options1);
               // vrVideo.loadVideo(Uri.parse("http://hdl-w.quklive.com/live/w1469002576632934.flv"),options1);
                vrVideo.playVideo();
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("111","error:"+e.getMessage());
            }*/

        }
    }

}
