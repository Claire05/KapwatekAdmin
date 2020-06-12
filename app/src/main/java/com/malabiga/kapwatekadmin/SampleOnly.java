package com.malabiga.kapwatekadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Model.EmployeeInformation;

import java.util.Date;

public class SampleOnly extends AppCompatActivity {

    Button confirm;
    DatabaseReference mDatabase;
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_only);

        time = findViewById(R.id.time);

        timeDifference();

        confirm = findViewById(R.id.buttonConfirm);
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("typeStatus").setValue("Pending");
            }
        });


//        FirebaseAuth firebaseAuth;
//        firebaseAuth = FirebaseAuth.getInstance();
//        final FirebaseUser user = firebaseAuth.getCurrentUser();
//
//        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = currentFirebaseUser.getUid();
//        final String userz = user.getUid();
//
//        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userz);
//        final DatabaseReference childref = rootRef.child("typeStatus");
//        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
//
//                EmployeeInformation order = userDataSnapshot.getValue(EmployeeInformation.class);
//                String state = order.getTypeStatus();
//
//                if (state.equals("Not Payments")) {
//                    MYConfirmPayments.setEnabled(true);
//                    MYShowOrderBtn.setEnabled(true);
//                }else if (state.equals("Not Payments")){
//                    MYConfirmPayments.setEnabled(false);
//                }
//            }
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//            // Getting Post failed, log a message
//            Log.w("tag", "loadPost:onCancelled", databaseError.toException());
//        }
//    });


//
//
//        long date = new Date().getTime();
//        long date2 = new Date().getTime();

//        timeDifference();
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//
//        public void timeDifference () {
//
//
//            long date = new Date().getTime();
//            long date2 = new Date().getTime();
//
//            long msPerMinute = 60 * 1000;
//            long msPerHour = msPerMinute * 60;
//            long msPerDay = msPerHour * 24;
//            long msPerMonth = msPerDay * 30;
//            long msPerYear = msPerDay * 365;
//
//            long elapsed = date - date2;
//
//            if (elapsed < msPerMinute) {
//
//                Toast.makeText(this, "" + Math.floor(elapsed / 1000) + "seconds ago", Toast.LENGTH_SHORT).show();
//
//
//            } else if (elapsed < msPerHour) {
//                Toast.makeText(this, "" + Math.floor(elapsed / msPerMinute) + "minutes ago", Toast.LENGTH_SHORT).show();
//            } else if (elapsed < msPerDay) {
//
//                Toast.makeText(this, "" + Math.floor(elapsed / msPerHour) + "hours ago", Toast.LENGTH_SHORT).show();
//
//            } else if (elapsed < msPerMonth) {
//
//                Toast.makeText(this, "approximately" + Math.floor(elapsed / msPerDay) + " days ago", Toast.LENGTH_SHORT).show();
//            } else if (elapsed < msPerYear) {
//
//                Toast.makeText(this, "approximately" + Math.floor(elapsed / msPerMonth) + " months ago", Toast.LENGTH_SHORT).show();
//
//            } else {
//
//                Toast.makeText(this, "approximately" + Math.floor(elapsed / msPerYear) + " years ago", Toast.LENGTH_SHORT).show();
//
//            }
//        }
    }

    public void timeDifference () {

            long date = new Date().getTime();
            long date2 = new Date().getTime();

            long msPerMinute = 60 * 1000;
            long msPerHour = msPerMinute * 60;
            long msPerDay = msPerHour * 24;
            long msPerMonth = msPerDay * 30;
            long msPerYear = msPerDay * 365;

            long elapsed = date - date2;

            if (elapsed < msPerMinute) {

                Toast.makeText(this, "" + Math.floor(elapsed / 1000) + "seconds ago", Toast.LENGTH_SHORT).show();

            } else if (elapsed < msPerHour) {
                Toast.makeText(this, "" + Math.floor(elapsed / msPerMinute) + "minutes ago", Toast.LENGTH_SHORT).show();
            } else if (elapsed < msPerDay) {

                Toast.makeText(this, "" + Math.floor(elapsed / msPerHour) + "hours ago", Toast.LENGTH_SHORT).show();

            } else if (elapsed < msPerMonth) {

                Toast.makeText(this, "approximately" + Math.floor(elapsed / msPerDay) + " days ago", Toast.LENGTH_SHORT).show();
            } else if (elapsed < msPerYear) {

                Toast.makeText(this, "approximately" + Math.floor(elapsed / msPerMonth) + " months ago", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "approximately" + Math.floor(elapsed / msPerYear) + " years ago", Toast.LENGTH_SHORT).show();

            }
        }

}


