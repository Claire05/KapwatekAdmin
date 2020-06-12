package com.malabiga.kapwatekadmin.Approval.VOL_Approval;

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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.R;
import com.malabiga.kapwatekadmin.SendEmail;
import com.malabiga.kapwatekadmin.Users_Approval;
import com.malabiga.kapwatekadmin.Users_Decline;

public class View_Profile_Volunteer extends AppCompatActivity {

    DatabaseReference rootref, childref;
    TextView fname_vol, mname_vol, lname_vol, email_vol, address_vol, phone_vol, status_vol, profession_vol;
    ImageView viewProfile_vol;
    Button btnApprove;
    DatabaseReference reference,mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_volunteer_profile_cardview);
        getVolunteerContent();

        fname_vol = findViewById(R.id.fname_vol);
        mname_vol = findViewById(R.id.mname_vol);
        lname_vol = findViewById(R.id.lname_vol);
        email_vol = findViewById(R.id.email_vol);
        address_vol = findViewById(R.id.address_vol);
        status_vol = findViewById(R.id.status_vol);
        profession_vol = findViewById(R.id.profession_vol);
        viewProfile_vol = findViewById(R.id.viewProfile_vol);

        btnApprove = findViewById(R.id.btnApprove);
        rootref = FirebaseDatabase
                .getInstance()
                .getReference();
        childref = rootref.child("Users");
    }

    private void getVolunteerContent() {
        if (getIntent().hasExtra("user_Picture")
                && getIntent().hasExtra("email")
                && getIntent().hasExtra("first_Name")
                && getIntent().hasExtra("middle_Name")
                && getIntent().hasExtra("last_Name")
                && getIntent().hasExtra("contact")
                && getIntent().hasExtra("profession")
                && getIntent().hasExtra("address")
                && getIntent().hasExtra("city")
                && getIntent().hasExtra("validID_1")
                && getIntent().hasExtra("validID_2")
                && getIntent().hasExtra("validone")
                && getIntent().hasExtra("validtwo")) {

            String typeStatus = getIntent().getStringExtra("typeStatus");
            final String email = getIntent().getStringExtra("email");
            String firstname = getIntent().getStringExtra("first_Name");
            String middlename = getIntent().getStringExtra("middle_Name");
            String lastname = getIntent().getStringExtra("last_Name");
            String address = getIntent().getStringExtra("address");
            String city = getIntent().getStringExtra("city");
            String contact = getIntent().getStringExtra("contact");
            String profession = getIntent().getStringExtra("profession");
            String volProfilePic = getIntent().getStringExtra("user_Picture");
            String empValidID = getIntent().getStringExtra("validID_1");
            String empValidID2 = getIntent().getStringExtra("validID_2");
            String validIDone = getIntent().getStringExtra("validone");
            String validIDtwo = getIntent().getStringExtra("validtwo");

            Spinner setPermission = findViewById(R.id.spinnerChangeStatus);
            String[] spinnerPermission = new String[]{"Approve", "Decline"};

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerPermission);
            setPermission.setAdapter(adapter);

            setImage(typeStatus, email, firstname, middlename, lastname, address, city, contact,profession, volProfilePic, empValidID,empValidID2, validIDone, validIDtwo);
            onClickBtnApprove(volProfilePic);
        }
    }

    private void setImage(String typeStatus, String email, String firstname, String middlename, String lastname, String address, String city, String
            contact, String profession, String empProfilePic, String volValidID, String volValidID2, String validIDone, String validIDtwo) {
        //Log.d(TAG, "setImage: setting te image and name to widgets.");

        ImageView images = findViewById(R.id.viewProfile_vol);

        Glide.with(this)
                .asBitmap()
                .load(empProfilePic)
                .into(images);
        ImageView images1 = findViewById(R.id.volValidID);
        Glide.with(this)
                .asBitmap()
                .load(volValidID)
                .into(images1);

        ImageView images2 = findViewById(R.id.volValidID2);
        Glide.with(this)
                .asBitmap()
                .load(volValidID2)
                .into(images2);

        TextView displayStatus = findViewById(R.id.status_vol);
        TextView displayEmail = findViewById(R.id.email_vol);
        TextView displayFirstName = findViewById(R.id.fname_vol);
        TextView displayMiddleName = findViewById(R.id.mname_vol);
        TextView displayLastName = findViewById(R.id.lname_vol);
        TextView displayContact = findViewById(R.id.phone_vol);
        TextView displayProfession = findViewById(R.id.profession_vol);
        TextView displayAddress = findViewById(R.id.address_vol);
        TextView displayCity = findViewById(R.id.city_vol);
        TextView displayvalidone = findViewById(R.id.viewValid1);
        TextView displayvalidtwo = findViewById(R.id.viewValid2);
//         TextView displaypwdpic = findViewById(R.id.profile_pic_pwd);

        displayStatus.setText(typeStatus);
        displayEmail.setText(email);
        displayFirstName.setText(firstname);
        displayMiddleName.setText(middlename);
        displayLastName.setText(lastname);
        displayContact.setText(contact);
        displayProfession.setText(profession);
        displayAddress.setText(address);
        displayCity.setText(city);
        displayvalidone.setText(validIDone);
        displayvalidtwo.setText(validIDtwo);
    }

    public void onClickBtnApprove(final String user_Profile) {
        btnApprove = findViewById(R.id.btnApprove);
        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("Users");
                reference.orderByChild("user_Picture").equalTo(user_Profile).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            final String parent = childSnapshot.getKey();
//                            for (int k = 1; k == 1; k++) {
//                                try {
                                    final Spinner dropdown = findViewById(R.id.spinnerChangeStatus);

//                                        if (dropdown.getSelectedItem().toString() == "Pending") {
//                                            reference.child(parent).child("typeStatus").setValue("Pending");
//                                            Toast.makeText(View_Profile_Volunteer.this, "Account Pending", Toast.LENGTH_SHORT).show();
//                                            Intent i = new Intent(View_Profile_Volunteer.this, ViewVolunteersAccount.class);
//                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                            startActivity(i);
//                                            finish();
//                                        }

                                    if (dropdown.getSelectedItem().toString() == "Approve") {



                                        AlertDialog.Builder alert = new AlertDialog.Builder(View_Profile_Volunteer.this);
                                        alert.setMessage("This post is about to be approved. Confirm?").setCancelable(false)
                                                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        reference.child(parent).child("typeStatus").setValue("Approved");
                                                        Intent i = new Intent(View_Profile_Volunteer.this, Users_Approval.class);
                                                        Toast.makeText(View_Profile_Volunteer.this, "Account Approved", Toast.LENGTH_SHORT).show();
                                                        String email = getIntent().getExtras().getString("email");
                                                        i.putExtra("email", email);
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
                                        Intent ii = new Intent(View_Profile_Volunteer.this, SendEmail.class);
                                        String email = getIntent().getExtras().getString("email");
                                        ii.putExtra("email",email);
                                        Toast.makeText(View_Profile_Volunteer.this, "Account Declined", Toast.LENGTH_SHORT).show();
                                        ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(ii);
                                        finish();
//                                            reference.child(parent).child("typeStatus").setValue("Declined");
//                                            Intent intent = new Intent(View_Profile_Volunteer.this, Users_Decline.class);
//                                            startActivity(intent);
//                                            Toast.makeText(View_Profile_Volunteer.this, "User Declined", Toast.LENGTH_SHORT).show();
//                                            finish();
                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}



