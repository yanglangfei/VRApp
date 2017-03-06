package app.myapp;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import app.myapp.adapter.PicAdapter;
import app.myapp.base.BaseActivity;
/**
 * Created by Administrator on 2017/2/27.
 */
public class PicListActivity extends BaseActivity {
    private RecyclerView rv;
    private List<Bitmap> bitmaps=new ArrayList<>();
    private static final String TAG = "PicListActivity";
    private PicAdapter picAdapter;

    private  String picName[]={"skin4.jpg","skin3.jpg","test.jpg"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        initView();
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initData() throws IOException {
        bitmaps.clear();
        AssetManager manager = getAssets();
        for (String name : picName) {
            InputStream open = manager.open(name);
            Bitmap bm= BitmapFactory.decodeStream(open);
            bitmaps.add(bm);
        }
        picAdapter.notifyDataSetChanged();
    }
    
    private void initView() {
        rv= (RecyclerView) findViewById(R.id.rv);
        GridLayoutManager lm=new GridLayoutManager(this,3);
        rv.setLayoutManager(lm);
        picAdapter = new PicAdapter(this,bitmaps);
        rv.setAdapter(picAdapter);
        picAdapter.setOnItemClickListener(new PicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String index=picName[position];
                Intent intent=new Intent(PicListActivity.this,WelcomActivity.class);
                intent.putExtra("index",index);
                startActivity(intent);
            }
        });


    }
}
