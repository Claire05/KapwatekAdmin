package com.malabiga.kapwatekadmin.Home_View;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VOL_Notification_Post_Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    //To have references from the other classes
    private VOL_Notification_View_Adapter mAdapter;

    //Firebase
    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabaseAccount, mDataStatus;
    private Query mDatabaseRef;
    private List<PostNewInformation> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vol_notification_recyclerview);

        firebaseAuth = FirebaseAuth.getInstance();

//Call the Recycler View
        mRecyclerView = findViewById(R.id.notificationRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabaseAccount = FirebaseDatabase.getInstance().getReference("Campaign_ad");
        mDatabaseAccount.orderByChild("typeStatus").equalTo("Pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPosts = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    PostNewInformation posts = postSnapshot.getValue(PostNewInformation.class);
                    mPosts.add(posts);
                }
                Collections.reverse(mPosts);
                mAdapter = new VOL_Notification_View_Adapter(VOL_Notification_Post_Activity.this, mPosts);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            finish();
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
