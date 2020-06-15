package com.malabiga.kapwatekadmin.Approval.EMP_LGU_Approval;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.malabiga.kapwatekadmin.Model.EmployeeInformation;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class View_Profile_Employees_Adapter extends RecyclerView.Adapter<View_Profile_Employees_Adapter.postViewHolder> {

    private Context mContext;
    private List<EmployeeInformation> mPosts;

    public View_Profile_Employees_Adapter(Context context, List<EmployeeInformation> posts){
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        //We used the Context to use and call the other xml
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_view_employees_profile_cardview, parent, false);
        return new postViewHolder(v);
    }

    //In this method, we are going get our data
    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
//we used posts to get our reference
        EmployeeInformation postCurrent = mPosts.get(position);
//This will call all the contents that we added from the admin posts, the getTitle came from the Posts

        holder.name.setText(postCurrent.getName());
        holder.email.setText(postCurrent.getEmail());
        holder.address.setText(postCurrent.getAddress());
        holder.contactPerson.setText(postCurrent.getContactNumber());
        holder.contactPhone.setText(postCurrent.getPosition());
        holder.status.setText(postCurrent.getTypeStatus());
//We Used Picasso to also call the image URL and Push it in the view post, WE USED CENTER CROP TO GET THE IMAGE FULLY
        Picasso.get().load(postCurrent.getUser_Picture()).fit().centerCrop().into(holder.imageProfile);
        Picasso.get().load(postCurrent.getValidID_1()).fit().centerCrop().into(holder.imageID);
        Picasso.get().load(postCurrent.getValidID_2()).fit().centerCrop().into(holder.imageID2);
    }

    //This shows the items as many as we want that was added from the admin post
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class postViewHolder extends RecyclerView.ViewHolder{

        public TextView name, email, address, contactPerson, contactPhone,  status;
        public ImageView imageProfile, imageID, imageID2;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProfile = itemView.findViewById(R.id.viewProfile_emp);
            imageID = itemView.findViewById(R.id.viewID_emp);
            imageID2 = itemView.findViewById(R.id.empValidID2);
            name = itemView.findViewById(R.id.name_emp);
            email = itemView.findViewById(R.id.email_emp);
            address = itemView.findViewById(R.id.address_emp);
            contactPerson = itemView.findViewById(R.id.contactPerson_emp);
            contactPhone = itemView.findViewById(R.id.contactPersonNo_emp);
            status = itemView.findViewById(R.id.status_emp);

        }
    }
}
