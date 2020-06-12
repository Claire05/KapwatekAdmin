package com.malabiga.kapwatekadmin.Approval.EMP_LGU_Approval;

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
import com.malabiga.kapwatekadmin.Model.EmployeeInformation;
import com.malabiga.kapwatekadmin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewEmployeesAccount extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    //To have references from the other classes
    private ViewEployeesAccountAdapter mAdapter;
    private DatabaseReference databaseReference;

    //Firebase
    private DatabaseReference mDatabaseRef;

    private List<EmployeeInformation> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employees_account_recyclerview);

//Call the Recycler View
        mRecyclerView = findViewById(R.id.recycler_view_employees);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
        mDatabaseRef.orderByChild("registered_As").equalTo("LGU").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mPosts = new ArrayList<>();

                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//
                                    EmployeeInformation posts = postSnapshot.getValue(EmployeeInformation.class);
                                    mPosts.add(posts);
                                }

//This is the important part to get the lists of new posts


//Call the Entities to View the lists of the Added Posts from the Admin Post
                Collections.reverse(mPosts);
                mAdapter = new ViewEployeesAccountAdapter(ViewEmployeesAccount.this, mPosts);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewEmployeesAccount.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}

