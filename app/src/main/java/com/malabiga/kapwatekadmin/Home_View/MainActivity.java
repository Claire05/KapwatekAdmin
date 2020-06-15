package com.malabiga.kapwatekadmin.Home_View;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.malabiga.kapwatekadmin.Home_View.Categories.Accidents;
import com.malabiga.kapwatekadmin.Home_View.Categories.Animals;
import com.malabiga.kapwatekadmin.Home_View.Categories.Church;
import com.malabiga.kapwatekadmin.Home_View.Categories.Education;
import com.malabiga.kapwatekadmin.Home_View.Categories.Medical;
import com.malabiga.kapwatekadmin.Home_View.Categories.Music;
import com.malabiga.kapwatekadmin.Home_View.Categories.Sport;
import com.malabiga.kapwatekadmin.Home_View.Categories.Volunteer;
import com.malabiga.kapwatekadmin.Home_View.Categories.Wedding;
import com.malabiga.kapwatekadmin.Map.ViewPeople;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    TextView time;
    private DrawerLayout eDrawerLayout;
    private ActionBarDrawerToggle eToggle;

    private FirebaseAuth firebaseAuth;
    private Toolbar eToolbar;
    private TextView profile_email, notification_counter;
    private NavigationView eNavigation;

    private RecyclerView mRecyclerView;
    //To have references from the other classes
    private ORG_Home_Cardview_Adapter mAdapter;

    //Firebase
    private DatabaseReference mDatabaseRef, mDataAccount;
    private List<PostNewInformation> mPosts;

    private int countNotification = 0, countNotification2 = 0;
    TextView fullname, vol_email;
    ImageView imgProfile = null;
    String dp1;
    String companyName;
    TextView school, company, ngo, lgu, volunteer1;

    Button viewPosts, viewLocations, live;
    ViewFlipper v_flipper;
    ImageView mSearch, notification;

    TextView accident, animal, music, education, medical, church, sport, volunteer, wedding;

    private long backPressedTime;
    private Toast backToast;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        content();
        updateTagEvent();
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = currentFirebaseUser.getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users/");
//        final String email = currentFirebaseUser.getEmail().toString();

        time = findViewById(R.id.time);
        notification = findViewById(R.id.notification);
        accident = findViewById(R.id.accident);
        animal = findViewById(R.id.animal);
        music = findViewById(R.id.music);
        education = findViewById(R.id.education);
        medical = findViewById(R.id.medical);
        church = findViewById(R.id.church);
        sport = findViewById(R.id.sport);
        volunteer = findViewById(R.id.volunteer);
        wedding = findViewById(R.id.wedding);


        live = findViewById(R.id.live);
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewLivePosts.class);
                startActivity(i);
            }
        });


        mDataAccount = FirebaseDatabase.getInstance().getReference("Campaign_ad");
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
                        Intent i = new Intent(MainActivity.this, VOL_Notification_Post_Activity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        mSearch = findViewById(R.id.search);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SearchView.class);
                startActivity(i);
            }
        });

        viewPosts = findViewById(R.id.viewPosts);
        viewPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewPosts.class);
                startActivity(i);
            }
        });

        viewLocations = findViewById(R.id.viewLocations);
        viewLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewPeople.class);
                startActivity(i);
            }
        });

        accident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Accidents.class);
                startActivity(i);
            }
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Animals.class);
                startActivity(i);
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Music.class);
                startActivity(i);
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Education.class);
                startActivity(i);
            }
        });

        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Medical.class);
                startActivity(i);
            }
        });

        church.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Church.class);
                startActivity(i);
            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Sport.class);
                startActivity(i);
            }
        });

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Volunteer.class);
                startActivity(i);
            }
        });

        wedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Wedding.class);
                startActivity(i);
            }
        });

        int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

        v_flipper = findViewById(R.id.v_flipper);
//
//        for (int i=0; i<images.length; i++){
//            flipperImages(images[i]);
//        }

        for (int image : images) {
            flipperImages(image);
        }

//
//        rootRef.addValueEventListener(new ValueEventListener() {
//            public void onDataChange(DataSnapshot snapshot) {
        mPosts = new ArrayList<>();
        eNavigation = findViewById(R.id.navigation_view_emp);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view_emp);
        //Inititalise items to add count value/badge value
        school = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_view_school));
        school.setVisibility(View.VISIBLE);

        lgu = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_view_lgu));
        lgu.setVisibility(View.VISIBLE);

        company = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_view_company));
        company.setVisibility(View.VISIBLE);

        ngo = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_view_ngo));
        ngo.setVisibility(View.VISIBLE);

        volunteer1 = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_view_vol));

        initializeCountDrawer();

        eNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_home_admin:
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_view_vol:
                        Intent iii = new Intent(MainActivity.this, ViewVolunteersAccount.class);
                        startActivity(iii);
                        break;
                    case R.id.nav_view_company:
                        Intent ii = new Intent(MainActivity.this, ViewEmployeesAccount.class);
                        startActivity(ii);
                        break;
                    case R.id.nav_view_ngo:
                        Intent ii1 = new Intent(MainActivity.this, com.malabiga.kapwatekadmin.Approval.EMP_NGO_Approval.ViewEmployeesAccount.class);
                        startActivity(ii1);
                        break;
                    case R.id.nav_view_lgu:
                        Intent ii2 = new Intent(MainActivity.this, com.malabiga.kapwatekadmin.Approval.EMP_LGU_Approval.ViewEmployeesAccount.class);
                        startActivity(ii2);
                        break;
                    case R.id.nav_view_school:
                        Intent ii3 = new Intent(MainActivity.this, com.malabiga.kapwatekadmin.Approval.EMP_SCL_Approval.ViewEmployeesAccount.class);
                        startActivity(ii3);
                        break;
//                    case R.id.nav_logout_admin:
//
//                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
//                        alert.setMessage("By clicking Yes, you will return to the login screen.").setCancelable(false)
//                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//
//                                        FirebaseAuth.getInstance().signOut();
//                                        //closing activity
//                                        finish();
//                                        //starting login activity
//                                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                                    }
//                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//                        AlertDialog alertDialog = alert.create();
//                        alertDialog.setTitle("Log Out?");
//                        alertDialog.show();
//                        break;
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
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });

//Call the Recycler View
//        mRecyclerView = findViewById(R.id.homeRecyclerView);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Campaign_ad");
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////Call for the Model
//                mPosts = new ArrayList<>();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    PostNewInformation posts = postSnapshot.getValue(PostNewInformation.class);
//                    mPosts.add(posts);
//                }
////Call the View Class
//                Collections.reverse(mPosts);
//                mAdapter = new ORG_Home_Cardview_Adapter(MainActivity.this, mPosts);
//                mRecyclerView.setAdapter(mAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        eToolbar = findViewById(R.id.nav_action_bar_emp);
        setSupportActionBar(eToolbar);

        eDrawerLayout = findViewById(R.id.drawerLayout_home_emp);
        eToggle = new ActionBarDrawerToggle(this, eDrawerLayout, R.string.open_emp, R.string.close_emp);

        eDrawerLayout.addDrawerListener(eToggle);
        eToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateTagEvent() {

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Campaign_ad");
        databaseReference.orderByChild("campaign_Title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String parent = "";
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    parent = postSnapshot.getKey();
                    String start = postSnapshot.child("start_of_Event").getValue().toString();
                    String end = postSnapshot.child("endof_Event").getValue().toString();
                    String a = postSnapshot.child("dateof_Event").getValue().toString();
                    Date d = new Date();
                    CharSequence da = DateFormat.format("yyyy-dd-MM", d.getTime());

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
                    Date c = Calendar.getInstance().getTime();
                    long currentTime = c.getTime();

                    try {
                        Date date1 = format.parse(a);
                        long expirationDate = date1.getTime();

                        CharSequence s = DateFormat.format("H:mma", d.getTime());
                        SimpleDateFormat formatter = new SimpleDateFormat("h:mma");
                        Date conver = formatter.parse((String) s);
                        long conerer = conver.getTime();

                        Date start1 = formatter.parse(start);
                        Date end1 = formatter.parse(end);

                        long start2 = start1.getTime();
                        long end2 = end1.getTime();
                        if (da.equals(a)) {
                            if (conerer >= start2 && conerer <= end2) {
                                //Live Event
                                if (dataSnapshot.exists()) {
                                    databaseReference.child(parent).child("event_Tag").setValue("Happening Event");
                                }
                            } else if (conerer <= start2) {
                                databaseReference.child(parent).child("event_Tag").setValue("Incoming Event");
                            } else if (conerer >= end2) {
                                databaseReference.child(parent).child("event_Tag").setValue("Expired Event");
                            }
                        } else if (currentTime > expirationDate) {
                            databaseReference.child(parent).child("event_Tag").setValue("Expired Event");
                        } else if (currentTime < expirationDate) {
                            databaseReference.child(parent).child("event_Tag").setValue("Incoming Event");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //back
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
        return super.onKeyDown(keyCode, event);
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

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(5000);
        v_flipper.setAutoStart(true);

//        v_flipper.setAnimation(this, android.R.anim.slide_in_left);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private void initializeCountDrawer() {
        DatabaseReference mDataCount = FirebaseDatabase.getInstance().getReference("Users");
        mDataCount.orderByChild("registered_As").equalTo("School").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    countNotification = (int) dataSnapshot.getChildrenCount();
                    school.setGravity(Gravity.CENTER_VERTICAL);
                    school.setTypeface(null, Typeface.BOLD);
                    school.setTextColor(getResources().getColor(R.color.colorAccent));
                    school.setText(Integer.toString(countNotification));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        DatabaseReference mDataCount4 = FirebaseDatabase.getInstance().getReference("Users");
        mDataCount4.orderByChild("registered_As").equalTo("LGU").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    countNotification = (int) dataSnapshot.getChildrenCount();
                    lgu.setGravity(Gravity.CENTER_VERTICAL);
                    lgu.setTypeface(null, Typeface.BOLD);
                    lgu.setTextColor(getResources().getColor(R.color.colorAccent));
                    lgu.setText(Integer.toString(countNotification));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        DatabaseReference mDataCount2 = FirebaseDatabase.getInstance().getReference("Users");
        mDataCount2.orderByChild("registered_As").equalTo("Company").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    countNotification = (int) dataSnapshot.getChildrenCount();
                    company.setGravity(Gravity.CENTER_VERTICAL);
                    company.setTypeface(null, Typeface.BOLD);
                    company.setTextColor(getResources().getColor(R.color.colorAccent));
                    company.setText(Integer.toString(countNotification));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference mDataCount3 = FirebaseDatabase.getInstance().getReference("Users");
        mDataCount3.orderByChild("registered_As").equalTo("NGO").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    countNotification = (int) dataSnapshot.getChildrenCount();
                    ngo.setGravity(Gravity.CENTER_VERTICAL);
                    ngo.setTypeface(null, Typeface.BOLD);
                    ngo.setTextColor(getResources().getColor(R.color.colorAccent));
                    ngo.setText(Integer.toString(countNotification));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        DatabaseReference mDataCount5 = FirebaseDatabase.getInstance().getReference("Users");
        mDataCount5.orderByChild("registered_As").equalTo("Volunteer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    countNotification = (int) dataSnapshot.getChildrenCount();
                    volunteer1.setGravity(Gravity.CENTER_VERTICAL);
                    volunteer1.setTypeface(null, Typeface.BOLD);
                    volunteer1.setTextColor(getResources().getColor(R.color.colorAccent));
                    volunteer1.setText(Integer.toString(countNotification));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
