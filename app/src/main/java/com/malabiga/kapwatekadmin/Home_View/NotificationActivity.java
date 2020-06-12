package com.malabiga.kapwatekadmin.Home_View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    //To have references from the other classes
    private NotificationAdapter mAdapter;

    //Firebase
    private DatabaseReference mDatabaseRef;
    private List<PostNewInformation> mPosts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


//Call the Recycler View
        mRecyclerView = findViewById(R.id.notificationRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Campaign_ad");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPosts = new ArrayList<>();
//This is the important part to get the lists of new posts
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    PostNewInformation posts = postSnapshot.getValue(PostNewInformation.class);
                    mPosts.add(posts);
                }
//Call the Entities to View the lists of the Added Posts from the Admin Post
                Collections.reverse(mPosts);
                mAdapter = new NotificationAdapter(NotificationActivity.this, mPosts);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(NotificationActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
