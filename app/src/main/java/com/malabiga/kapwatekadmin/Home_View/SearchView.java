package com.malabiga.kapwatekadmin.Home_View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchView extends AppCompatActivity {
    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    private DatabaseReference mDatabaseRef;
    private List<PostNewInformation> mPosts;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_edit_text = (EditText) findViewById(R.id.search_field);
        recyclerView = (RecyclerView) findViewById(R.id.result_list);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        /*
         * Create a array list for each node you want to use
         * */

        mPosts = new ArrayList<>();

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    /*
                     * Clear the list when editText is empty
                     * */
                    mPosts.clear();
                    recyclerView.removeAllViews();
                }
            }
        });
    }

    private void setAdapter(final String searchedString) {

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Campaign_ad");
        mDatabaseRef.orderByChild("typeStatus").equalTo("Approved").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPosts = new ArrayList<>();
//Call for the Model
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    PostNewInformation posts = postSnapshot.getValue(PostNewInformation.class);
                    mPosts.add(posts);
                }
//Call the View Class
                Collections.reverse(mPosts);
                searchAdapter = new SearchAdapter(SearchView.this, mPosts);
                recyclerView.setAdapter(searchAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SearchView.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        databaseReference.child("Campaign_ad").orderByChild("typeStatus").equalTo("Approved").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                /*
//                 * Clear the list for every new search
//                 * */
//                mPosts.clear();
//                recyclerView.removeAllViews();
//
//                int counter = 0;
//
//                /*
//                 * Search all users for matching searched string
//                 * */
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String uid = snapshot.getKey();
//                    String full_name = snapshot.child("campaign_Title").getValue(String.class);
//                    String user_name = snapshot.child("author").getValue(String.class);
//                    String profile_pic = snapshot.child("announcement_Picture").getValue(String.class);
//
//                    if (full_name.toLowerCase().contains(searchedString.toLowerCase())) {
////                        mPosts.get
////                        userNameList.add(user_name);
////                        profilePicList.add(profile_pic);
//                        counter++;
//                    } else if (user_name.toLowerCase().contains(searchedString.toLowerCase())) {
////                        fullNameList.add(full_name);
////                        userNameList.add(user_name);
////                        profilePicList.add(profile_pic);
//                        counter++;
//                    }
//
//                    /*
//                     * Get maximum of 15 searched results only
//                     * */
//                    if (counter == 15)
//                        break;
//                }
//
//                searchAdapter = new VOL_SearchAdapter(VOL_Search.this, mPosts);
//                recyclerView.setAdapter(searchAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}



//package com.malabiga.googlemap.GeoFire.VOL;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.DownloadManager;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.SearchView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.malabiga.googlemap.GeoFire.Model.PostNewInformation;
//import com.malabiga.googlemap.GeoFire.Model.SearchModel;
//import com.malabiga.googlemap.GeoFire.Model.Users;
//import com.malabiga.googlemap.R;
//import com.squareup.picasso.Picasso;
//
//public class VOL_Search extends AppCompatActivity {
//
//    Button searchBtn;
//    RecyclerView result_list;
//    EditText search_field;
//
//    private DatabaseReference mUserDatabase;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.vol_search);
//
//        search_field = findViewById(R.id.search_field);
//        searchBtn = findViewById(R.id.searchBtn);
//        result_list = findViewById(R.id.result_list);
//
//
//        result_list.setHasFixedSize(true);
//        result_list.setLayoutManager(new LinearLayoutManager(this));
//        mUserDatabase = FirebaseDatabase.getInstance().getReference("Campaign_ad");
//
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String searchText = search_field.getText().toString();
//
//                firebaseUserSearch(searchText);
//
//            }
//        });
//
//    }
//
//
//    private void firebaseUserSearch(String searchText) {
//
//        Toast.makeText(VOL_Search.this, "Started Search", Toast.LENGTH_LONG).show();
//
//        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
//
//        FirebaseRecyclerAdapter<Users, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UserViewHolder>(
//
//                Users.class,
//                R.layout.list_layout,
//                UserViewHolder.class,
//                firebaseSearchQuery
//
//        ) {
//            @NonNull
//            @Override
//            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull Users model) {
//                holder.setDetails(getApplicationContext(), model.getName(), model.getStatus(), model.getImage());
//            }
//
//
//        };
//
//        result_list.setAdapter(firebaseRecyclerAdapter);
//
//    }
//
//
////    private void firebaseUserSearch() {
////
////        FirebaseRecyclerAdapter<SearchModel,UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SearchModel, UserViewHolder>(
////
////                SearchView.class,
////                R.layout.list_layout,
////                UserViewHolder.class,
////                mUserDatabase
////
////        ) {
////            @Override
////            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull SearchModel model) {
////
////                holder.setDetails(getApplicationContext(),model.getCampaign_Title(),model.getAuthor(),model.getAnnouncement_Picture());
////
////            }
////
////            @NonNull
////            @Override
////            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////                return null;
////            }
////        };
////
////        result_list.setAdapter(firebaseRecyclerAdapter);
////
////    }
//
//    public static class UserViewHolder extends RecyclerView.ViewHolder{
//        View mView;
//
//        public UserViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            mView = itemView;
//        }
//
//        public void setDetails(Context ctx, String userName, String userStatus, String userImage){
//
//            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
//            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
//            ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);
//
//            user_name.setText(userName);
//            user_status.setText(userStatus);
//
//            Glide.with(ctx).load(userImage).into(user_image);
//
//        }
//
//    }
//
////    private void firebaseUserSearch(String searchText) {
////
////        Toast.makeText(VOL_Search.this, "Started Search", Toast.LENGTH_LONG).show();
////
////        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
////
////        FirebaseRecyclerAdapter<PostNewInformation, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostNewInformation, UsersViewHolder>(
////
////                PostNewInformation.class,
////                R.layout.list_layout,
////                UsersViewHolder.class,
////                firebaseSearchQuery
////
////        ) {
////            @Override
////            protected void populateViewHolder(UsersViewHolder viewHolder, PostNewInformation model, int position) {
////
////
////                viewHolder.setDetails(getApplicationContext(), model.getFirst_Name(), model.getUser(), model.getAmount());
////
////            }
////        };
////
////        result_list.setAdapter(firebaseRecyclerAdapter);
////
////    }
////
////
////    // View Holder Class
////
////    public static class UsersViewHolder extends RecyclerView.ViewHolder {
////
////        View mView;
////
////        public UsersViewHolder(View itemView) {
////            super(itemView);
////
////            mView = itemView;
////
////        }
////
////        public void setDetails(Context ctx, String userName, String userStatus, String userImage){
////
////            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
////            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
////            ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);
////
////
////            user_name.setText(userName);
////            user_status.setText(userStatus);
////
////            Glide.with(ctx).load(userImage).into(user_image);
////
////
////        }
////
////
////
//
//}