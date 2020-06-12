package com.malabiga.kapwatekadmin.Home_View.Donators;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.malabiga.kapwatekadmin.Model.Donations;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.Model.VolunteerInformation;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class View_Donators_Adapter extends RecyclerView.Adapter<View_Donators_Adapter.postViewHolder> {

    private Context mContext;
    private List<Donations> mPosts;

    public View_Donators_Adapter(Context context, List<Donations> posts) {
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //We used the Context to use and call the other xml
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_view_donators_cardview, parent, false);
        return new postViewHolder(v);
    }

    //In this method, we are going get our data
    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
//we used posts to get our reference
        Donations postCurrent = mPosts.get(position);
//This will call all the contents that we added from the admin posts, the getTitle came from the Posts

        holder.viewName.setText(postCurrent.getUser());
        holder.viewDetails.setVisibility(View.VISIBLE);
        holder.onClick(position);

//We Used Picasso to also call the image URL and Push it in the view post, WE USED CENTER CROP TO GET THE IMAGE FULLY
        Picasso.get().load(postCurrent.getUser_Profile()).fit().centerCrop().into(holder.viewDonators);
    }

    //This shows the items as many as we want that was added from the admin post
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class postViewHolder extends RecyclerView.ViewHolder {

        public TextView viewName;
        public Button viewDetails;
        public ImageView viewDonators;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.viewName);
            viewDonators = itemView.findViewById(R.id.viewDonators);
            viewDetails = itemView.findViewById(R.id.viewDetails);
        }

        //
        public void onClick(final int position) {

            viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, View_Donators_Information.class);
                    i.putExtra("name", mPosts.get(position).getUser());
                    i.putExtra("amount", mPosts.get(position).getAmount());
                    mContext.startActivity(i);
                }
            });


        }
    }
}
