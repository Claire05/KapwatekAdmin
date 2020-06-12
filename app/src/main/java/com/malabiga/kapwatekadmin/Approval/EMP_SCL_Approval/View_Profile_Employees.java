package com.malabiga.kapwatekadmin.Approval.EMP_SCL_Approval;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Home_View.MainActivity;
import com.malabiga.kapwatekadmin.R;
import com.malabiga.kapwatekadmin.SendEmail;
import com.malabiga.kapwatekadmin.Users_Approval;
import com.malabiga.kapwatekadmin.Users_Decline;

public class View_Profile_Employees extends AppCompatActivity {
    //    private RecyclerView mRecyclerView;
//    //To have references from the other classes
//    private ORG_Home_Cardview_Adapter mAdapter;
//
//    //Firebase
//    private DatabaseReference mDatabaseRef;
//    private List<PostNewInformation> mPosts;
    DatabaseReference rootref, childref;
    TextView name_emp, email_emp, address_emp, contactPersonNo_emp, status_emp, contactPerson_emp;
    ImageView viewProfile_emp, empProfilePic, viewID2_emp;
    Button btnApprove;
    LinearLayout mContainer;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employees_profile_cardview);
        getCompanyContent();

        name_emp = findViewById(R.id.name_emp);
        email_emp = findViewById(R.id.email_emp);
        address_emp = findViewById(R.id.address_emp);
        contactPerson_emp = findViewById(R.id.contactPerson_emp);
        contactPersonNo_emp = findViewById(R.id.contactPersonNo_emp);
        viewProfile_emp = findViewById(R.id.viewProfile_emp);
        status_emp = findViewById(R.id.status_emp);
        empProfilePic = findViewById(R.id.empValidID);
//        viewID2_emp = findViewById(R.id.viewID2_emp);
        mContainer =findViewById(R.id.container);
        firebaseAuth = FirebaseAuth.getInstance();
        rootref = FirebaseDatabase
                .getInstance()
                .getReference();
        childref = rootref.child("Users");
    }
    private void getCompanyContent() {
        if (getIntent().hasExtra("user_Picture")
                && getIntent().hasExtra("email")
                && getIntent().hasExtra("name")
                && getIntent().hasExtra("contact_Person")
                && getIntent().hasExtra("valone")
                && getIntent().hasExtra("valtwo")
                || getIntent().hasExtra("contact_Person_Number")){
            //Log.d(TAG, "getIncomingIntent: found intent extras.");

            String typeStatus = getIntent().getStringExtra("typeStatus");
            String email= getIntent().getStringExtra("email");
            String name = getIntent().getStringExtra("name");
            String address = getIntent().getStringExtra("address");
            String contactPerson = getIntent().getStringExtra("contact_Person");
            String contactPersonNumber= getIntent().getStringExtra("contact_Person_Number");
            String empProfilePic= getIntent().getStringExtra("user_Picture");
            String empValidID= getIntent().getStringExtra("validID_1");
            String empValidID2= getIntent().getStringExtra("validID_2");
            String validone = getIntent().getStringExtra("valone");
            String validtwo = getIntent().getStringExtra("valtwo");

            Spinner setPermission = findViewById(R.id.spinnerChangeStatus);
            String[] spinnerPermission = new String[]{"Approve","Decline"};

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerPermission);
            setPermission.setAdapter(adapter);

            setImage( typeStatus,  email, name, address, contactPerson, contactPersonNumber, empProfilePic, empValidID, empValidID2, validone, validtwo);
            onClickBtnApprove(empProfilePic);
        }
    }


    private void setImage(String typeStatus, String email,String name, String address, String contactPerson,
                          String contactPersonNumber, String empProfilePic,
                          String empValidID, String empValidID2,String validone , String validtwo){
        //Log.d(TAG, "setImage: setting te image and name to widgets.");

        ImageView images = findViewById(R.id.viewProfile_emp);


        Glide.with(this)
                .asBitmap()
                .load(empProfilePic)
                .into(images);

        ImageView images1 = findViewById(R.id.empValidID);


        Glide.with(this)
                .asBitmap()
                .load(empValidID)
                .into(images1);

        ImageView images2 = findViewById(R.id.empValidID2);

        Glide.with(this)
                .asBitmap()
                .load(empValidID2)
                .into(images2);

        TextView displayStatus = findViewById(R.id.status_emp);
        TextView displayEmail = findViewById(R.id.email_emp);
        TextView displayName = findViewById(R.id.name_emp);
        TextView displaycontactPerson = findViewById(R.id.contactPerson_emp);
        TextView displaycontactPersonNumber = findViewById(R.id.contactPersonNo_emp);
        TextView displayAddress = findViewById(R.id.address_emp);
        TextView displayvalidone = findViewById(R.id.viewValid1);
        TextView displayvalidtwo = findViewById(R.id.viewValid2);
//         TextView displaypwdpic = findViewById(R.id.profile_pic_pwd);
//
        displayStatus.setText(typeStatus);
        displayEmail.setText(email);
        displayName.setText(name);
        displaycontactPerson.setText(contactPerson);
        displaycontactPersonNumber.setText(contactPersonNumber);
        displayAddress.setText(address);
        displayvalidone.setText(validone);
        displayvalidtwo.setText(validtwo);
         }
    public void onClickBtnApprove ( final String user_Profile){
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
//                            for(int k=1;k==1;k++) {
//                                try {
                                    final Spinner dropdown = findViewById(R.id.spinnerChangeStatus);

//                                    if(dropdown.getSelectedItem().toString()=="Pending"){
//                                        reference.child(parent).child("typeStatus").setValue("Pending");
//
//                                        Intent i = new Intent(View_Profile_Employees.this, ViewEmployeesAccount.class);
//                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                        startActivity(i);
//                                        Toast.makeText(View_Profile_Employees.this, "User Pending", Toast.LENGTH_SHORT).show();
//                                        finish();
//                                    }
                            if (dropdown.getSelectedItem().toString() == "Approve") {
                                AlertDialog.Builder alert = new AlertDialog.Builder(View_Profile_Employees.this);
                                alert.setMessage("This user is about to be approved. Confirm?").setCancelable(false)
                                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                reference.child(parent).child("typeStatus").setValue("Approved");
                                                Intent i = new Intent(View_Profile_Employees.this, MainActivity.class);

                                                String email = getIntent().getStringExtra("email");
                                                i.putExtra("email",email);
                                                Toast.makeText(View_Profile_Employees.this, "Account Approved", Toast.LENGTH_SHORT).show();
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
                                Intent ii = new Intent(View_Profile_Employees.this, SendEmail.class);
                                Toast.makeText(View_Profile_Employees.this, "Account Declined", Toast.LENGTH_SHORT).show();
                                String email= getIntent().getStringExtra("email");
                                ii.putExtra("email",email );
                                ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(ii);
                                finish();

//                                    Intent i = new Intent(View_Profile_Employees.this, ViewEmployeesAccount.class);
//                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(i);
//                                    Toast.makeText(View_Profile_Employees.this, "User Approved", Toast.LENGTH_SHORT).show();
//                                    finish();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//
//                                }
//                            }
                        }
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