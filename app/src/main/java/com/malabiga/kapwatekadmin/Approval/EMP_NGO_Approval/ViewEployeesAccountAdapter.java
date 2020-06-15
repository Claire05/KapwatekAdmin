package com.malabiga.kapwatekadmin.Approval.EMP_NGO_Approval;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.malabiga.kapwatekadmin.Model.EmployeeInformation;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewEployeesAccountAdapter extends RecyclerView.Adapter<ViewEployeesAccountAdapter.postViewHolder> {

    private Context mContext;
    private List<EmployeeInformation> mPosts;

    public ViewEployeesAccountAdapter(Context context, List<EmployeeInformation> posts) {
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        //We used the Context to use and call the other xml
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_view_employees_account_cardview_com, parent, false);
        return new postViewHolder(v);
    }

    //In this method, we are going get our data
    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, final int position) {
//we used posts to get our reference
        EmployeeInformation postCurrent = mPosts.get(position);
//This will call all the contents that we added from the admin posts, the getTitle came from the Posts
        holder.name.setText(postCurrent.getName());
        holder.status.setText(postCurrent.getTypeStatus());
        holder.email.setText(postCurrent.getEmail());
        holder.container.setVisibility(View.VISIBLE);
        holder.onClick(position);

//We Used Picasso to also call the image URL and Push it in the view post, WE USED CENTER CROP TO GET THE IMAGE FULLY
        Picasso.get().load(postCurrent.getUser_Picture()).fit().centerCrop().into(holder.IDemp);
    }

    //This shows the items as many as we want that was added from the admin post
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class postViewHolder extends RecyclerView.ViewHolder {


        public TextView name,status, date, email;
        public ImageView IDemp;
        public Button deleteAccount;
        private FirebaseAuth firebaseAuth;
        private CardView container;

        DatabaseReference reference;


        public postViewHolder(@NonNull View itemView) {
            super(itemView);

            IDemp = itemView.findViewById(R.id.viewID_emp);
            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
            firebaseAuth = FirebaseAuth.getInstance();
            container = itemView.findViewById(R.id.btnView_emp);
            date = itemView.findViewById(R.id.date);
            email = itemView.findViewById(R.id.email);

        }

        public void onClick(final int position) {

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, View_Profile_Employees.class);
                    i.putExtra("name", mPosts.get(position).getName());
                    i.putExtra("email", mPosts.get(position).getEmail());
                    i.putExtra("address", mPosts.get(position).getAddress());
                    i.putExtra("contact_Person", mPosts.get(position).getContactNumber());
                    i.putExtra("contact_Person_Number", mPosts.get(position).getPosition());
                    i.putExtra("typeStatus", mPosts.get(position).getTypeStatus());
                    i.putExtra("validID_1", mPosts.get(position).getValidID_1());
                    i.putExtra("validID_2", mPosts.get(position).getValidID_2());
                    i.putExtra("user_Picture", mPosts.get(position).getUser_Picture());
                    i.putExtra("valone",mPosts.get(position).getValone());
                    i.putExtra("valtwo",mPosts.get(position).getValtwo());
                    mContext.startActivity(i);
                }
            });


//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//            final Query applesQuery = ref.child("Users").orderByChild("title").equalTo("Apple");
//            deleteAccount.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
//                                appleSnapshot.getRef().removeValue();
//                        }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//
//                }
//            });


//
//            confirmAccount.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    reference = FirebaseDatabase.getInstance().getReference().child("Users");
//                    reference.orderByChild("user_Picture").equals()
//                }
//            });


        }

    }

}
