package com.example.catwong.tita.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.model.Goal;

import java.util.ArrayList;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class GoalListAdapter extends RecyclerView.Adapter<GoalListAdapter.Holder> {
    private LayoutInflater mInflater;
    private ArrayList<Goal> mGoalList;
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
    public GoalListAdapter(Context context, ArrayList<Goal> data) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mGoalList = data;
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
    public GoalListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.layout_goal_item, parent, false);
        return new Holder(mView, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(GoalListAdapter.Holder holder, int position) {
        Goal goal = mGoalList.get(position);
        holder.mTitle.setText(goal.getTitle());
        holder.mLocation.setText(goal.getLocation());
        holder.mDatetime.setText(goal.getStarttime());

    }

    @Override
    public int getItemCount() {
        return mGoalList.size();
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

        /**
         * Constructor of Holder to bind the widgets and set the clickListener and longClickListener of every item
         *
         * @param view
         * @param listener
         */
        public Holder(View view, MyItemClickListener listener) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.goal_main_title_textview);
            mLocation = (TextView) view.findViewById(R.id.goal_main_location_textview);
            mDatetime = (TextView) view.findViewById(R.id.goal_main_datetime_textview);
            mLayout = (LinearLayout) view.findViewById(R.id.goal_list_item_linearlayout);
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
