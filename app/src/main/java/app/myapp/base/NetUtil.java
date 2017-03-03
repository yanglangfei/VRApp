package app.myapp.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 *
 */
public class NetUtil {
    private static final String TAG = "NetUtil";
    public  OnHttpResult onHttpResult;
    private  Context contexts;

    public void setOnHttpResult(OnHttpResult onHttpResult) {
        this.onHttpResult = onHttpResult;
    }

    /**
     * @param param
     * @param context
     * @deprecated    发送GET 请求
     */
    public void sendGet(RequestParams param, Context context){
        this.contexts=context;
        if(!checkNet()){
            // 1、无网络处理
            if(onHttpResult!=null){
                onHttpResult.onNetFail();
            }
            Toast.makeText(contexts, "网络连接失败", Toast.LENGTH_SHORT).show();
            return;
        }
        x.http().get(param, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                if (onHttpResult != null) {
                    onHttpResult.onCache(result);
                }
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (onHttpResult != null) {
                    //2、   无数据 处理
                    if(result==null){

                    }
                    //3、   数据正常处理
                    onHttpResult.onSuccess(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (onHttpResult != null) {
                    onHttpResult.onError(ex);
                }
                // 4、请求失败
                Toast.makeText(contexts, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (onHttpResult != null) {
                    onHttpResult.onCancell(cex);
                }
            }

            @Override
            public void onFinished() {
                if (onHttpResult != null) {
                    onHttpResult.onFinish();
                }
            }
        });
    }


    /**
     * @param param
     * @param context
     * @deprecated  发送POST 请求
     */
    public  void sendPost(RequestParams param, Context context){
        this.contexts=context;
        if(!checkNet()){
            // 1、无网络处理    -----提示网络连接失败，并终止访问数据
            if(onHttpResult!=null){
                onHttpResult.onNetFail();
            }
            Toast.makeText(contexts, "网络连接失败", Toast.LENGTH_SHORT).show();
            return;
        }
        x.http().post(param, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                //缓存数据    返回true 信任缓存   否则不信任，继续访问
                if (onHttpResult != null) {
                    onHttpResult.onCache(result);
                }
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (onHttpResult != null) {
                    if(result==null){
                        //2、   无数据 处理  -----显示暂无数据界面


                    }
                    Log.i(TAG,"result==="+result);
                    //3、   数据正常处理
                    onHttpResult.onSuccess(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (onHttpResult != null) {
                    onHttpResult.onError(ex);
                }
                // 4、请求失败
                Toast.makeText(contexts, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (onHttpResult != null) {
                    onHttpResult.onCancell(cex);
                }
            }

            @Override
            public void onFinished() {
                if (onHttpResult != null) {
                    onHttpResult.onFinish();
                }
            }
        });
    }


    /**
     * @return
     * @deprecated  监听网络状态
     */
    private boolean checkNet() {

       return  1!=2;
    }


    /**
     *  @deprecated  请求结果
     */
      interface  OnHttpResult{

        /**
         * @deprecated  网络连接失败
         */
        void onNetFail();

        /**
         * @param result
         * @deprecated  请求成功
         */
        void onSuccess(String result);

        /**
         * @param ex
         * @deprecated  请求失败
         */
        void onError(Throwable ex);

        /**
         * @param ex
         * @deprecated  请求被取消
         */
        void onCancell(Callback.CancelledException ex);

        /**
         * @deprecated  请求完成
         */
        void onFinish();

        /**
         * @param result
         * @deprecated  获取到缓存的数据
         */
        void onCache(String result);
    }
}
