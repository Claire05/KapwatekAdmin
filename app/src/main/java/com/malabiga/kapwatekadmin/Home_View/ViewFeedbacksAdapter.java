package com.malabiga.kapwatekadmin.Home_View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.malabiga.kapwatekadmin.Model.VolunteerInformation;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewFeedbacksAdapter extends RecyclerView.Adapter<ViewFeedbacksAdapter.postViewHolder> {

    private Context mContext;
    private List<VolunteerInformation> mPosts;

    public ViewFeedbacksAdapter(Context context, List<VolunteerInformation> posts){
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //We used the Context to use and call the other xml
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_view_feedbacks_cardview, parent, false);
        return new postViewHolder(v);
    }

    //In this method, we are going get our data
    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
//we used posts to get our reference
        VolunteerInformation postCurrent = mPosts.get(position);
        holder.fname.setText(postCurrent.getFirst_Name());
        holder.lname.setText(postCurrent.getLast_Name());
//We Used Picasso to also call the image URL and Push it in the view post, WE USED CENTER CROP TO GET THE IMAGE FULLY
        Picasso.get().load(postCurrent.getUser_Picture()).fit().centerCrop().into(holder.imageView);
        holder.btnView_feedback.setVisibility(View.VISIBLE);
        holder.onClick(position);

        holder.deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Feedback");
                databaseReference.removeValue();
                Toast.makeText(mContext, "Data Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //This shows the items as many as we want that was added from the admin post
    @Override
    public int getItemCount() {
        return mPosts.size();
    }
    public class postViewHolder extends RecyclerView.ViewHolder{

        public TextView fname, lname;
        public ImageView imageView;
        Button btnView_feedback, deleteAccount;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.name);
//            message = itemView.findViewById(R.id.email_vol);
            imageView = itemView.findViewById(R.id.viewPicture_vol);
            btnView_feedback = itemView.findViewById(R.id.btnView_feedback);
            deleteAccount = itemView.findViewById(R.id.deleteAccount);
        }

        public void onClick(final int position) {
            btnView_feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, ViewFeedbackDescription.class);
//                    i.putExtra("title",mPosts.get(position).getTitle());
//                    i.putExtra("message",mPosts.get(position).getMessage());
                    mContext.startActivity(i);

                }
            });
        }
    }
}

