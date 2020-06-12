package com.malabiga.kapwatekadmin.Approval.VOL_Approval;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Model.VolunteerInformation;
import com.malabiga.kapwatekadmin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewVolunteersAccount extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    //To have references from the other classes
    private ViewVolunteersAccountAdapter mAdapter;

    //Firebase
    private DatabaseReference mDatabaseRef;
    private List<VolunteerInformation> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_volunteer_account_recyclerview);

        //Call the Recycler View
        mRecyclerView = findViewById(R.id.recycler_view_volunteer);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));




        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
        mDatabaseRef.orderByChild("registered_As").equalTo("Volunteer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPosts = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    VolunteerInformation posts = postSnapshot.getValue(VolunteerInformation.class);
                    mPosts.add(posts);
                }
                //Call the Entities to View the lists of the Added Posts from the Admin Post
                Collections.reverse(mPosts);
                mAdapter = new ViewVolunteersAccountAdapter(ViewVolunteersAccount.this, mPosts);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewVolunteersAccount.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
