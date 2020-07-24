package com.malabiga.kapwatekadmin.Approval.Post_Approval;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Home_View.MainActivity;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

public class View_Post_Description extends AppCompatActivity {
    DatabaseReference reference;
    TextView title, description, goal, author, date1, status, time, address;
    ImageView picture;
    Button btnApprove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post_description);
        title = findViewById(R.id.descriptionTitleView);
        description = findViewById(R.id.descriptionDescriptionView);
        goal = findViewById(R.id.descriptionGoal);
        author = findViewById(R.id.descriptionAuthorView);
        picture = findViewById(R.id.description_img);
        date1 = findViewById(R.id.descriptionDate);
        status = findViewById(R.id.status_post);
        time = findViewById(R.id.time);
        address = findViewById(R.id.address);

        final String postImage = getIntent().getExtras().getString("Announcement_Picture");
        Picasso.get().load(postImage).resize(200, 100).into(picture);
        String postTitle = getIntent().getExtras().getString("Campaign_Title");
        title.setText(postTitle);
        String postAuthor = getIntent().getExtras().getString("Author");
        author.setText(postAuthor);
        String postDescription = getIntent().getExtras().getString("Story_Description");
        description.setText(postDescription);
        String postGoal = getIntent().getExtras().getString("Donation_Goal");
        goal.setText(postGoal);
        String postDate = getIntent().getExtras().getString("Date_Posted");
        date1.setText(postDate);
        String postStatus = getIntent().getExtras().getString("typeStatus");
        status.setText(postStatus);
        String postTime = getIntent().getExtras().getString("time");
        time.setText(postTime);
        String postAddress = getIntent().getExtras().getString("address");
        address.setText(postAddress);


        Spinner setPermission = findViewById(R.id.spinnerChangeStatus);
        String[] spinnerPermission = new String[]{"Decline", "Approve"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerPermission);
        setPermission.setAdapter(adapter);
        btnApprove = findViewById(R.id.btnApprove);
        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnApprove(postImage);
            }
        });
    }

    private void onClickBtnApprove(final String Image) {
        reference = FirebaseDatabase.getInstance().getReference().child("Campaign_ad");
        reference.orderByChild("announcement_Picture").equalTo(Image).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    final String parent = childSnapshot.getKey();
                    for (int k = 1; k == 1; k++) {
                        try {
                            final Spinner dropdown = findViewById(R.id.spinnerChangeStatus);
                            if (dropdown.getSelectedItem().toString() == "Approve") {
                                AlertDialog.Builder alert = new AlertDialog.Builder(View_Post_Description.this);
                                alert.setMessage("This post is about to be approved. Confirm?").setCancelable(false)
                                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                reference.child(parent).child("typeStatus").setValue("Approved");
                                                Intent i = new Intent(View_Post_Description.this, MainActivity.class);
                                                Toast.makeText(View_Post_Description.this, "Campaign Post Approved", Toast.LENGTH_SHORT).show();
                                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(i);
                                                finish();
                                            }
                                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog alertDialog = alert.create();
                                alertDialog.setTitle("Are you sure?");
                                alertDialog.show();
                            }
                            if (dropdown.getSelectedItem().toString() == "Decline") {
                                reference.child(parent).child("typeStatus").setValue("Declined");
                                final Intent ii = new Intent(View_Post_Description.this, SendEmailCampaign.class);
                                String uid = getIntent().getExtras().getString("uid");

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                                            String email = dataSnapshot.child("email").getValue().toString();

                                            Toast.makeText(View_Post_Description.this, "Campaign Post Declined", Toast.LENGTH_SHORT).show();
                                            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            ii.putExtra("email",email);
                                            startActivity(ii);
                                            finish();
//                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}