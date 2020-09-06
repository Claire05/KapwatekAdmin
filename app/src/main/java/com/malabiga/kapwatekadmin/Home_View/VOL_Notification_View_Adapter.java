package com.malabiga.kapwatekadmin.Home_View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.malabiga.kapwatekadmin.Approval.Post_Approval.View_Post_Description;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VOL_Notification_View_Adapter extends RecyclerView.Adapter<VOL_Notification_View_Adapter.postViewHolder> {

    private Context mContext;
    private List<PostNewInformation> mPosts;

    public VOL_Notification_View_Adapter(Context context, List<PostNewInformation> posts) {
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //We used the Context to use and call the other xml
        View v = LayoutInflater.from(mContext).inflate(R.layout.vol_notification_cardview, parent, false);
        return new postViewHolder(v);
    }

    //In this method, we are going get our data
    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
//we used posts to get our reference
        PostNewInformation postCurrent = mPosts.get(position);
//This will call all the contents that we added from the admin posts, the getTitle came from the Posts
//        holder.textViewCompany.setText(postCurrent.getRegistered_As());
        holder.textDate.setText(postCurrent.getDate_Posted());
//        holder.textViewTitle.setText(postCurrent.);
        holder.textViewfullname.setText(postCurrent.getAuthor());
        holder.container.setVisibility(View.VISIBLE);
        holder.onClick(position);
//We Used Picasso to also call the image URL and Push it in the view post, WE USED CENTER CROP TO GET THE IMAGE FULLY
        Picasso.get().load(postCurrent.getAnnouncement_Picture()).fit().centerCrop().into(holder.imageView);
    }

    //This shows the items as many as we want that was added from the admin post
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class postViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewCompany, textViewfullname, textDate;
        public ImageView imageView;
        public LinearLayout container;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);

//            textViewCompany = itemView.findViewById(R.id.company);
            textViewfullname = itemView.findViewById(R.id.fname);
//            textViewTitle = itemView.findViewById(R.id.title);
            textDate = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.imageView);
            container = itemView.findViewById(R.id.container);
        }

        public void onClick(final int position) {
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, View_Post_Description.class);
                    i.putExtra("Announcement_Picture", mPosts.get(position).getAnnouncement_Picture());
//                    i.putExtra("Donation_Goal", mPosts.get(position).getDonations_Goal());
                    i.putExtra("Campaign_Title", mPosts.get(position).getCampaign_Title());
                    i.putExtra("Story_Description", mPosts.get(position).getStory_Description());
                    i.putExtra("Author", mPosts.get(position).getAuthor());
                    i.putExtra("Date_Posted", mPosts.get(position).getDate_Posted());
                    i.putExtra("typeStatus", mPosts.get(position).getTypeStatus());
                    i.putExtra("date_Posted", mPosts.get(position).getDate_Posted());
                    i.putExtra("address", mPosts.get(position).getAddress());
                    i.putExtra("time", mPosts.get(position).getTime());
                    i.putExtra("uid", mPosts.get(position).getUid());
                    mContext.startActivity(i);
                }
            });
        }
    }
}