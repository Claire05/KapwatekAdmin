package com.malabiga.kapwatekadmin.Approval.VOL_Approval;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Model.VolunteerInformation;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewVolunteersAccountAdapter extends RecyclerView.Adapter<ViewVolunteersAccountAdapter.postViewHolder> {

    private Context mContext;
    private List<VolunteerInformation> mPosts;

    public ViewVolunteersAccountAdapter(Context context, List<VolunteerInformation> posts) {
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //We used the Context to use and call the other xml
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_view_volunteers_account_cardview, parent, false);
        return new postViewHolder(v);
    }

    //In this method, we are going get our data
    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
//we used posts to get our reference
        VolunteerInformation postCurrent = mPosts.get(position);
//This will call all the contents that we added from the admin posts, the getTitle came from the Posts
//        holder.deleteAccount.setVisibility(View.VISIBLE);
        holder.profession.setText(postCurrent.getEmail());
        holder.status.setText(postCurrent.getTypeStatus());
        holder.layout.setVisibility(View.VISIBLE);
        holder.onClick(position);
//We Used Picasso to also call the image URL and Push it in the view post, WE USED CENTER CROP TO GET THE IMAGE FULLY
        Picasso.get().load(postCurrent.getUser_Picture()).fit().centerCrop().into(holder.imageView);
    }

    //This shows the items as many as we want that was added from the admin post
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class postViewHolder extends RecyclerView.ViewHolder {

        public TextView fname, lname, email, status, profession;
        public ImageView imageView, deleteAccount;
        public Button viewInfo;
        public LinearLayout layout;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteAccount = itemView.findViewById(R.id.deleteAccount);
            profession = itemView.findViewById(R.id.registeredAs);
            imageView = itemView.findViewById(R.id.imageView);
            layout = itemView.findViewById(R.id.layout);
            status = itemView.findViewById(R.id.status);
        }

        public void onClick(final int position) {
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, View_Profile_Volunteer.class);
                    i.putExtra("first_Name", mPosts.get(position).getFirst_Name());
                    i.putExtra("last_Name", mPosts.get(position).getLast_Name());
                    i.putExtra("middle_Name", mPosts.get(position).getMiddle_Name());
                    i.putExtra("address", mPosts.get(position).getAddress());
                    i.putExtra("email", mPosts.get(position).getEmail());
                    i.putExtra("contact", mPosts.get(position).getContact());
                    i.putExtra("typeStatus", mPosts.get(position).getTypeStatus());
                    i.putExtra("user_Picture", mPosts.get(position).getUser_Picture());
                    i.putExtra("validone", mPosts.get(position).getValidone());
                    i.putExtra("validtwo", mPosts.get(position).getValidtwo());
                    i.putExtra("validID_1", mPosts.get(position).getValidID_1());
                    i.putExtra("validID_2", mPosts.get(position).getValidID_2());
                    i.putExtra("profession", mPosts.get(position).getProfession());
                    i.putExtra("city",mPosts.get(position).getCity());
                    mContext.startActivity(i);
                }
            });
        }
    }
}


