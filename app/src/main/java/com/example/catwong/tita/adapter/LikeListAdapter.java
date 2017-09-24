package com.example.catwong.tita.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.util.Common;

import java.util.ArrayList;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class LikeListAdapter extends RecyclerView.Adapter<LikeListAdapter.Holder> {
    private LayoutInflater mInflater;
    private ArrayList<Event> mEventList;
    private MyItemClickListener mItemClickListener = null;
    private Context mContext;

    /**
     * Define the ItemClickListener interface
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * Constructor of DiaryListAdapter
     *
     * @param context
     * @param data
     */
    public LikeListAdapter(Context context, ArrayList<Event> data) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mEventList = data;
    }

    /**
     * Set the item click listener
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    public LikeListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.like_list_item, parent, false);
        return new Holder(mView, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(LikeListAdapter.Holder holder, int position) {
        Event event = mEventList.get(position);
        holder.mTitle.setText(event.getTitle());
        holder.mLocation.setText(event.getLocation());
        holder.mDatetime.setText(Common.dateFormat.getTimeFromeDate(event.getStartTime())
            + " - " +  Common.dateFormat.getTimeFromeDate(event.getEndTime()) );
        holder.imgLike.setTag(position);

        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                deleteItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public interface MyItemClickListenerr {
    }

    /**
     * Delete a diary and update the list
     *
     * @param postion
     */
    public void deleteItem(int postion) {
        mEventList.remove(postion);
        notifyDataSetChanged();
    }

    /**
     * Define the Holder class and implement OnClickListener and OnLongClickListener
     */
    static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mTitle;
        private final TextView mLocation;
        private final TextView mDatetime;
        private LinearLayout mLayout;
        private MyItemClickListener mListener;
        private final ImageView imgLike;

        /**
         * Constructor of Holder to bind the widgets and set the clickListener and longClickListener of every item
         *
         * @param view
         * @param listener
         */
        public Holder(View view, MyItemClickListener listener) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.like_main_title_textview);
            mLocation = (TextView) view.findViewById(R.id.like_main_location_textview);
            mDatetime = (TextView) view.findViewById(R.id.like_main_datetime_textview);
            mLayout = (LinearLayout) view.findViewById(R.id.like_list_item_linearlayout);
            imgLike = (ImageView) view.findViewById(R.id.like_list_item_like);
            mListener = listener;
            mLayout.setOnClickListener(this);
        }

        /**
         * Override the method onClick
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }
    }
}
