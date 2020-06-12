package com.malabiga.kapwatekadmin.Home_View.Donators;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Model.Donations;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.Model.VolunteerInformation;
import com.malabiga.kapwatekadmin.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class View_Donators extends AppCompatActivity {

    DatabaseReference reference, mDataDonate;
    FirebaseAuth firebaseAuth;
    private RecyclerView mRecyclerView;
    //To have references from the other classes
    private View_Donators_Adapter mAdapter;
    private List<Donations> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donators_recycler);
        firebaseAuth = FirebaseAuth.getInstance();

        String uid = getIntent().getStringExtra("Announcement_Picture");

        //Call the Recycler View
        mRecyclerView = findViewById(R.id.recycler_view_posts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("Campaign_ad");
        reference.orderByChild("announcement_Picture").equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPosts = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    for(DataSnapshot donationsnap : postSnapshot.child("donations").getChildren()) {
                        Donations posts = donationsnap.getValue(Donations.class);
                        mPosts.add(posts);
                    }
                }
                //Call the Entities to View the lists of the Added Posts from the Admin Post
                mAdapter = new View_Donators_Adapter(View_Donators.this, mPosts);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(View_Donators.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
