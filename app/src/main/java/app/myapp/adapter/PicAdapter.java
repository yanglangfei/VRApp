package app.myapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import app.myapp.R;
/**
 * Created by Administrator on 2017/2/27.
 */

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.MyHolder> implements View.OnClickListener {
    private final List<Bitmap> bitmaps;
    public  OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PicAdapter(Context context, List<Bitmap> bitmaps) {
        this.bitmaps=bitmaps;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pic_item,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.view.setTag(position);
        ImageView iv = (ImageView) holder.view.findViewById(R.id.iv);
        iv.setImageBitmap(bitmaps.get(position));
        holder.view.setOnClickListener(this);
   }

    @Override
    public int getItemCount() {
        return bitmaps.size();
    }

    @Override
    public void onClick(View v) {
        if(onItemClickListener!=null){
            onItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    class  MyHolder extends  RecyclerView.ViewHolder{
        View view;

        public MyHolder(View itemView) {
            super(itemView);
            this.view=itemView;
        }
    }


   public interface  OnItemClickListener{
        void onItemClick(View v,int position);
    }


}
