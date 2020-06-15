package com.malabiga.kapwatekadmin.Home_View.Categories;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Approval.EMP_COM_Approval.ViewEmployeesAccount;
import com.malabiga.kapwatekadmin.Approval.Post_Approval.ORG_Home_Cardview_Adapter;
import com.malabiga.kapwatekadmin.Approval.VOL_Approval.ViewVolunteersAccount;
import com.malabiga.kapwatekadmin.Home_View.LoginActivity;
import com.malabiga.kapwatekadmin.Home_View.MainActivity;
import com.malabiga.kapwatekadmin.Home_View.VOL_Notification_Post_Activity;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Accidents extends AppCompatActivity {

    ImageView notification;
    private int countNotification = 0;
    private TextView notification_counter;
    int count = 0;
    TextView time;
    private DrawerLayout eDrawerLayout;
    private ActionBarDrawerToggle eToggle;

    private FirebaseAuth firebaseAuth;
    private Toolbar eToolbar;
    private TextView profile_email;
    private NavigationView eNavigation;

    private RecyclerView mRecyclerView;
    //To have references from the other classes
    private ORG_Home_Cardview_Adapter mAdapter;

    //Firebase
    private DatabaseReference mDatabaseRef;
    private List<PostNewInformation> mPosts;

    TextView fullname, vol_email;
    ImageView imgProfile = null;
    String dp1;
    String companyName;

    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = currentFirebaseUser.getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users/");
//        final String email = currentFirebaseUser.getEmail().toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);
        content();
        time = findViewById(R.id.time);


        notification = findViewById(R.id.notification);

        DatabaseReference mDataAccount = FirebaseDatabase.getInstance().getReference("Campaign_ad");
        mDataAccount.orderByChild("typeStatus").equalTo("Pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    notification_counter = findViewById(R.id.notification_counter);
                    countNotification = (int) dataSnapshot.getChildrenCount();
                    notification_counter.setText(Integer.toString(countNotification));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                rootRef.orderByChild("typeStatus").equalTo("Pending").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Intent i = new Intent(Accidents.this, VOL_Notification_Post_Activity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        rootRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                mPosts = new ArrayList<>();
                eNavigation = findViewById(R.id.navigation_view_emp);
                eNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.nav_home_admin:
                                Intent i = new Intent(Accidents.this, Accidents.class);
                                startActivity(i);
                                break;
                            case R.id.nav_view_vol:
                                Intent iii = new Intent(Accidents.this, ViewVolunteersAccount.class);
                                startActivity(iii);
                                break;
                            case R.id.nav_view_company:
                                Intent ii = new Intent(Accidents.this, ViewEmployeesAccount.class);
                                startActivity(ii);
                                break;
                            case R.id.nav_view_ngo:
                                Intent ii1 = new Intent(Accidents.this, com.malabiga.kapwatekadmin.Approval.EMP_NGO_Approval.ViewEmployeesAccount.class);
                                startActivity(ii1);
                                break;
                            case R.id.nav_view_lgu:
                                Intent ii2 = new Intent(Accidents.this, com.malabiga.kapwatekadmin.Approval.EMP_LGU_Approval.ViewEmployeesAccount.class);
                                startActivity(ii2);
                                break;
                            case R.id.nav_view_school:
                                Intent ii3 = new Intent(Accidents.this, com.malabiga.kapwatekadmin.Approval.EMP_SCL_Approval.ViewEmployeesAccount.class);
                                startActivity(ii3);
                                break;
//                            case R.id.nav_logout_admin:
//
//                                AlertDialog.Builder alert = new AlertDialog.Builder(Accidents.this);
//                                alert.setMessage("By clicking Yes, you will return to the login screen.").setCancelable(false)
//                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                                FirebaseAuth.getInstance().signOut();
//                                                //closing activity
//                                                finish();
//                                                //starting login activity
//                                                startActivity(new Intent(Accidents.this, LoginActivity.class));
//                                            }
//                                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.cancel();
//                                    }
//                                });
//                                AlertDialog alertDialog = alert.create();
//                                alertDialog.setTitle("Log Out?");
//                                alertDialog.show();
//                                break;
                        }
                        return false;
                    }
                });

//                dp1 = snapshot.child("empValidID").getValue().toString();
//                companyName = snapshot.child("fullname").getValue().toString();
//                View hView = eNavigation.inflateHeaderView(R.layout.navigation_header);
//                imgProfile = hView.findViewById(R.id.profile_pic_org);
//                Glide.with(getApplicationContext()).load(dp1).into(imgProfile);
//                fullname = hView.findViewById(R.id.profile_name_emp);
//                fullname.setText(companyName);
//                vol_email = hView.findViewById(R.id.profile_email);
//                vol_email.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

//Call the Recycler View
        mRecyclerView = findViewById(R.id.homeRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Campaign_ad");
        mDatabaseRef.orderByChild("category").equalTo("Accidents and Emergencies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//Call for the Model
                mPosts = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    PostNewInformation posts = postSnapshot.getValue(PostNewInformation.class);
                    mPosts.add(posts);
                }
//Call the View Class
                Collections.reverse(mPosts);
                mAdapter = new ORG_Home_Cardview_Adapter(Accidents.this, mPosts);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Accidents.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        eToolbar = findViewById(R.id.nav_action_bar_emp);
        setSupportActionBar(eToolbar);

        eDrawerLayout = findViewById(R.id.drawerLayout_home_emp);
        eToggle = new ActionBarDrawerToggle(this, eDrawerLayout, R.string.open_emp, R.string.close_emp);

        eDrawerLayout.addDrawerListener(eToggle);
        eToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (eToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void content() {
        count++;
        time = findViewById(R.id.time);
        refresh(1000);
    }

    private void refresh(int milliseconds) {

        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content();
            }
        };
        handler.postDelayed(runnable, milliseconds);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        return super.onKeyDown(keyCode, event);
    }
}