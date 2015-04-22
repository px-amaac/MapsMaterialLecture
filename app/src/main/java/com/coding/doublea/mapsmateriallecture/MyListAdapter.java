package com.coding.doublea.mapsmateriallecture;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aaron on 4/16/2015.
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private OnItemClickListener mOnItemClickListener;
    private final LayoutInflater mInflater;
    private ArrayList<Location> mData = new ArrayList<>();

    public MyListAdapter(Context context, OnItemClickListener mOnItemClickListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public static interface OnItemClickListener {
        public void onItemClick(Location loc);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mLatView;
        public TextView mLonView;
        private Location location;

        public ViewHolder(View v) {
            super(v);
            mLatView = (TextView) v.findViewById(R.id.lat);
            mLonView = (TextView) v.findViewById(R.id.lon);
            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(location);
                    }
                }
            });
        }

        public void bind(Location loc) {
            location = loc;
            mLatView.setText(String.valueOf(loc.getLatitude()));
            mLonView.setText(String.valueOf(loc.getLongitude()));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.location_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    public void addData(Location loc) {
        mData.add(loc);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
