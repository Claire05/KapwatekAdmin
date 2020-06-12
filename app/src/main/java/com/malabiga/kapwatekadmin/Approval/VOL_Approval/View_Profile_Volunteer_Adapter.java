package com.malabiga.kapwatekadmin.Approval.VOL_Approval;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.malabiga.kapwatekadmin.Model.VolunteerInformation;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class View_Profile_Volunteer_Adapter extends RecyclerView.Adapter<View_Profile_Volunteer_Adapter.postViewHolder> {

    private Context mContext;
    private List<VolunteerInformation> mPosts;

    public View_Profile_Volunteer_Adapter(Context context, List<VolunteerInformation> posts){
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //We used the Context to use and call the other xml
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_view_volunteer_profile_cardview, parent, false);
        return new postViewHolder(v);
    }

    //In this method, we are going get our data
    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
//we used posts to get our reference
        VolunteerInformation postCurrent = mPosts.get(position);
//This will call all the contents that we added from the admin posts, the getTitle came from the Posts

        holder.fname.setText(postCurrent.getFirst_Name());
        holder.mname.setText(postCurrent.getMiddle_Name());
        holder.lname.setText(postCurrent.getLast_Name());
        holder.email.setText(postCurrent.getEmail());
        holder.address.setText(postCurrent.getAddress());
        holder.phone.setText(postCurrent.getContact());
        holder.city_vol.setText(postCurrent.getCity());
        holder.bday_vol.setText(postCurrent.getBirthday());
        holder.status.setText(postCurrent.getTypeStatus());
        holder.gender_vol.setText(postCurrent.getGender());
//We Used Picasso to also call the image URL and Push it in the view post, WE USED CENTER CROP TO GET THE IMAGE FULLY
        Picasso.get().load(postCurrent.getValidID_1()).fit().centerCrop().into(holder.imageProfile);
        Picasso.get().load(postCurrent.getValidID_2()).fit().centerCrop().into(holder.volValidID);
//        Picasso.get().load(postCurrent.getEmpValidID()).fit().centerCrop().into(holder.imageID);
    }

    //This shows the items as many as we want that was added from the admin post
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class postViewHolder extends RecyclerView.ViewHolder{

        public TextView fname,mname, lname, email, address, phone,  status, city_vol, bday_vol, gender_vol;
        public ImageView imageProfile,volValidID;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.viewProfile_vol);
//            imageID = itemView.findViewById(R.id.viewID_emp);
            fname = itemView.findViewById(R.id.fname_vol);
            mname= itemView.findViewById(R.id.mname_vol);
            lname= itemView.findViewById(R.id.lname_vol);
            email = itemView.findViewById(R.id.email_vol);
            address = itemView.findViewById(R.id.address_vol);
            phone = itemView.findViewById(R.id.phone_vol);
            status = itemView.findViewById(R.id.status_emp);
            city_vol = itemView.findViewById(R.id.city_vol);
            volValidID = itemView.findViewById(R.id.volValidID);
            bday_vol = itemView.findViewById(R.id.bday_vol);
            gender_vol= itemView.findViewById(R.id.gender_vol);
        }
    }
}
