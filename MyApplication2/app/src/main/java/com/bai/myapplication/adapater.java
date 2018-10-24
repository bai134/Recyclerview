package com.bai.myapplication;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapater extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private Context context;
    private List<String> data;

    public adapater(Context context, List<String> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout,parent,false);
        return new vholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        vholder vholder = (adapater.vholder) holder;
        vholder.binddata(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

   class vholder extends RecyclerView.ViewHolder{

        private TextView tv;
        private ImageView imageView;

        public vholder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);




        }

        public void binddata(String name){
            tv.setText(name);
//            Resources resources = context.getResources();
//            DisplayMetrics dm = resources.getDisplayMetrics();
//            int width = dm.widthPixels;
//
//            ViewGroup.LayoutParams params = imageView.getLayoutParams();
//            //设置图片的相对于屏幕的宽高比
//            params.width = width/2;
//            params.height =  (int) (200 + Math.random() * 400) ;
//            imageView.setLayoutParams(params);

            imageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    ViewCompat.animate(itemView)
                            .setDuration(200)
                            .scaleX(2.0f)
                            .scaleY(2.08f)
                            .start();
                }

            });
        }
    }
}
