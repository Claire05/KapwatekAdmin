package com.malabiga.kapwatekadmin.Home_View.Participate;

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

import com.malabiga.kapwatekadmin.Model.Pledge;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class View_Particapates_Adapter extends RecyclerView.Adapter<View_Particapates_Adapter.postViewHolder> {

    private Context mContext;
    private List<Pledge> mPosts;
    private int position;

    public View_Particapates_Adapter(Context context, List<Pledge> posts) {
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //We used the Context to use and call the other xml
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_view_participates_cardview, parent, false);
        return new postViewHolder(v);
    }

    //In this method, we are going get our data
    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
//we used posts to get our reference
        Pledge postCurrent = mPosts.get(position);
        holder.viewName.setText(postCurrent.getUser());
        holder.viewDetails.setVisibility(View.VISIBLE);
        holder.onClick(position);

//We Used Picasso to also call the image URL and Push it in the view post, WE USED CENTER CROP TO GET THE IMAGE FULLY
        Picasso.get().load(postCurrent.getUser_Profile()).fit().centerCrop().into(holder.imageView);
    }

    //This shows the items as many as we want that was added from the admin post
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class postViewHolder extends RecyclerView.ViewHolder {

        public TextView amount, viewName, viewVol;
        public ImageView imageView;
        public Button viewDetails;
        public postViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.circleImageView);
            amount = itemView.findViewById(R.id.amount);
            viewName = itemView.findViewById(R.id.registeredAs);
            viewDetails = itemView.findViewById(R.id.viewInfo);
        }

        public void onClick(final int position) {

            viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, View_Participates_Information.class);
                    i.putExtra("name", mPosts.get(position).getUser());
                    i.putExtra("user_Profile",mPosts.get(position).getUser_Profile());
                    mContext.startActivity(i);
                }
            });


        }
    }
}
