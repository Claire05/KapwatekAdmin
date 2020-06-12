package com.malabiga.kapwatekadmin.Home_View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context mContext;
    private List<PostNewInformation> mPosts;

    public SearchAdapter(Context context, List<PostNewInformation> posts) {
        mContext = context;
        mPosts = posts;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView full_name, user_name;
        LinearLayout container;

        public SearchViewHolder(View itemView) {
            super(itemView);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            full_name = (TextView) itemView.findViewById(R.id.full_name);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            container = itemView.findViewById(R.id.container);
        }

        public void onClick1(final int position) {
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, ViewPosts.class);
                    i.putExtra("announcement_Picture", mPosts.get(position).getAnnouncement_Picture());
                    i.putExtra("donation_Goal", mPosts.get(position).getDonations_Goal());
                    i.putExtra("campaign_Title", mPosts.get(position).getCampaign_Title());
                    i.putExtra("story_Description", mPosts.get(position).getStory_Description());
                    i.putExtra("author", mPosts.get(position).getAuthor());
                    i.putExtra("date_Posted", mPosts.get(position).getDate_Posted());
                    i.putExtra("total_Donations", mPosts.get(position).getTotal_DONATIONS());
                    i.putExtra("geofenceLayer", mPosts.get(position).getGeofenceLayer());
                    i.putExtra("uid", mPosts.get(position).getUid());
                    mContext.startActivity(i);
                }
            });
        }
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        holder.full_name.setText(mPosts.get(position).getCampaign_Title());
        holder.user_name.setText(mPosts.get(position).getAuthor());
        holder.container.setVisibility(View.VISIBLE);
        holder.onClick1(position);
        Glide.with(mContext).load(mPosts.get(position).getAnnouncement_Picture()).into(holder.profileImage);
//        Glide.with(context).load(profilePicList.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.profileImage);


    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

}