package com.malabiga.kapwatekadmin.Home_View.Donators;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.R;

import org.w3c.dom.Text;

public class View_Donators_Information extends AppCompatActivity {

    ImageView circleImageView;
    TextView donatorName, amount;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donators_information);
        getContent();

        circleImageView = findViewById(R.id.circleImageView);
        donatorName= findViewById(R.id.donatorName);
        amount = findViewById(R.id.amount);

//
//        FirebaseAuth firebaseAuth;
//        firebaseAuth = FirebaseAuth.getInstance();
//        final FirebaseUser usere = firebaseAuth.getCurrentUser();
//
//        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = currentFirebaseUser.getUid();
//        final String userz = usere.getUid();
//
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userz);
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String empValidID1 = dataSnapshot.child("user_Picture").getValue().toString();
//                Glide.with(getApplicationContext()).load(empValidID1).into(circleImageView);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
    }

    private void getContent() {
        if (getIntent().hasExtra("amount")
                && getIntent().hasExtra("name")
                && getIntent().hasExtra("user_Picture")){

            String amount = getIntent().getStringExtra("amount");
            String firstName = getIntent().getStringExtra("name");
            String userImage = getIntent().getStringExtra("user_Picture");

            setImage(amount, firstName,userImage);
        }
    }

    private void setImage(String amount, String firstName, String userImage) {

        ImageView images = findViewById(R.id.circleImageView);
        Glide.with(this)
                .asBitmap()
                .load(userImage)
                .into(images);

        TextView name = findViewById(R.id.donatorName);
        TextView amount1 = findViewById(R.id.amount);

        name.setText(firstName);
        amount1.setText(amount);
    }
}
