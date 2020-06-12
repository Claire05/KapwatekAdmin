package com.malabiga.kapwatekadmin.Home_View.Participate;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Model.Pledge;
import com.malabiga.kapwatekadmin.R;

import java.util.ArrayList;
import java.util.List;

public class View_Particapates extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    private RecyclerView mRecyclerView;
    //To have references from the other classes
    private View_Particapates_Adapter mAdapter;

    //Firebase
    private DatabaseReference mDatabaseRef, databaseReference;
    private List<Pledge> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donators_recycler);
        firebaseAuth = FirebaseAuth.getInstance();

        //Call the Recycler View
        mRecyclerView = findViewById(R.id.recycler_view_posts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("Pledges");
        reference.orderByChild("campaign_id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPosts = new ArrayList<>();
                //This is the important part to get the lists of new posts
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    for (DataSnapshot donationsnap : postSnapshot.child("volunteers").getChildren()) {
                        Pledge posts = dataSnapshot1.getValue(Pledge.class);
                        mPosts.add(posts);
//                    }
                }
                //Call the Entities to View the lists of the Added Posts from the Admin Post
                mAdapter = new View_Particapates_Adapter(View_Particapates.this, mPosts);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(View_Particapates.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
