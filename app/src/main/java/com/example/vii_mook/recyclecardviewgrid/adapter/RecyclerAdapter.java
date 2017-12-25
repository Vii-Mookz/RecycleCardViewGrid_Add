package com.example.vii_mook.recyclecardviewgrid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vii_mook.recyclecardviewgrid.R;
import com.example.vii_mook.recyclecardviewgrid.model.Animal;

import java.util.List;

/**
 * Created by vii-mook on 8/31/2017 AD.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Animal> mData;
    private LayoutInflater mInflater;

    private static final String TAG = RecyclerAdapter.class.getSimpleName();

    public RecyclerAdapter(Context context, List<Animal> data) {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onCreateViewHolder" + position);

        Animal current = mData.get(position);
        holder.setData(current, position);
        holder.setListeners();
    }

    public void addItem(int position, Animal animal) {
        mData.add(position, animal);
        notifyItemInserted(position);
//		notifyItemRangeChanged(position, mDataList.size());
//		notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView imgThumb, imgCopy;
        int position;
        Animal current;
        Toolbar toolbar;

        public MyViewHolder(View itemview) {
            super(itemview);

            title = (TextView) itemview.findViewById(R.id.txv_row);
            imgThumb = (ImageView) itemview.findViewById(R.id.img_row);
            imgCopy = (ImageView) itemview.findViewById(R.id.img_row_add);
            toolbar = (Toolbar) itemview.findViewById(R.id.add_img);


        }

        public void setData(Animal current, int position) {
            this.title.setText(current.getTitle());
            this.imgThumb.setImageResource(current.getImageId());
            this.position = position;
            this.current = current;
//
        }

        public void setListeners() {
            imgCopy.setOnClickListener(MyViewHolder.this);

        }

        public void onClick(View v) {
            Log.i(TAG, "onClick before operation" + position + " Size : " + mData.size());
            switch (v.getId()) {

                case R.id.img_row_add:
                    addItem(position, current);
                    break;
            }
            Log.i(TAG, "onClick after operation  Size :" + mData.size());
        }
    }
}
