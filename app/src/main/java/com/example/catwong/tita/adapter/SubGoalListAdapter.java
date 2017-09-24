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
import com.example.catwong.tita.model.SubGoal;
import com.example.catwong.tita.util.Common;

import java.util.ArrayList;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class SubGoalListAdapter extends RecyclerView.Adapter<SubGoalListAdapter.Holder> {
    private LayoutInflater mInflater;
    private ArrayList<SubGoal> mSubGoalList;
    private MyItemClickListener mItemClickListener = null;

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
    public SubGoalListAdapter(Context context, ArrayList<SubGoal> data) {
        mInflater = LayoutInflater.from(context);
        mSubGoalList = data;
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
    public SubGoalListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.layout_goal_daily_item, parent, false);
        return new Holder(mView, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(final SubGoalListAdapter.Holder holder, int position) {
        SubGoal subGoal = mSubGoalList.get(position);
//        holder.mTitle.setText("" + subGoal.getGoalID());
        holder.mLocation.setText(subGoal.getEndTime().toString());
        holder.mDatetime.setText(subGoal.getStartTime().toString());
        holder.imgFinish.setTag(position);
        holder.imgMiss.setTag(position);

        holder.imgFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgMiss.setImageResource(R.drawable.ic_success);
                holder.imgFinish.setVisibility(View.GONE);
            }
        });

        holder.imgMiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgMiss.setImageResource(R.drawable.ic_fail);
                holder.imgFinish.setVisibility(View.GONE);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mSubGoalList.size();
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
        private ImageView imgMiss, imgFinish;

        /**
         * Constructor of Holder to bind the widgets and set the clickListener and longClickListener of every item
         *
         * @param view
         * @param listener
         */
        public Holder(View view, MyItemClickListener listener) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.subGoal_main_title_textview);
            mLocation = (TextView) view.findViewById(R.id.subGoal_main_location_textview);
            mDatetime = (TextView) view.findViewById(R.id.subGoal_main_datetime_textview);
            mLayout = (LinearLayout) view.findViewById(R.id.subGoal_list_item_linearlayout);
            imgMiss = (ImageView) view.findViewById(R.id.goal_daily_miss);
            imgFinish = (ImageView) view.findViewById(R.id.goal_daily_finish);
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
