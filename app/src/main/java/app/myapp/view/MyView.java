package app.myapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import app.myapp.R;

/**
 * Created by Administrator on 2017/3/1.
 */

public class MyView extends LinearLayout {
    private  String title;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.myAttrs);
        title=array.getString(R.styleable.myAttrs_titleText);

        array.recycle();
    }


}
