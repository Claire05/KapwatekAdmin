package com.malabiga.kapwatekadmin.Home_View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.malabiga.kapwatekadmin.Approval.Post_Approval.View_Post_Description;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

public class SearchActivity extends AppCompatActivity {

    EditText inputSearch;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<PostNewInformation> options;
    FirebaseRecyclerAdapter<PostNewInformation,MyViewHolder> adapter;
    DatabaseReference Dataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Dataref= FirebaseDatabase.getInstance().getReference().child("Campaign_ad");
        inputSearch=findViewById(R.id.inputSearch);
        recyclerView=findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        LoadData("");

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString()!=null)
                {
                    LoadData(s.toString());
                }
                else
                {
                    LoadData("");
                }
            }
        });
    }

    private void LoadData(String data) {
        final Query query=Dataref.orderByChild("campaign_Title").startAt(data).endAt(data+"\uf8ff");

        options=new FirebaseRecyclerOptions.Builder<PostNewInformation>().setQuery(query,PostNewInformation.class).build();
        adapter=new FirebaseRecyclerAdapter<PostNewInformation, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyViewHolder holder, final int position, @NonNull final PostNewInformation model) {
                holder.textView.setText(model.getCampaign_Title());
                holder.author.setText(model.getAuthor());
                Picasso.get().load(model.getAnnouncement_Picture()).into(holder.imageView);
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(SearchActivity.this, View_Post_Description.class);
                        String image = model.getAnnouncement_Picture();
                        String title = model.getCampaign_Title();
                        String author = model.getAuthor();
                        String story = model.getStory_Description();
//                        String goal = model.getDonations_Goal();
                        String date = model.getDate_Posted();
                        String time = model.getTime();
                        String status = model.getTypeStatus();
                        String address = model.getAddress();
                        intent.putExtra("Announcement_Picture",image);
                        intent.putExtra("Campaign_Title",title);
                        intent.putExtra("Author",author);
                        intent.putExtra("Story_Description",story);
                        intent.putExtra("address", address);
//                        intent.putExtra("Donation_Goal",goal);
                        intent.putExtra("typeStatus",status);
                        intent.putExtra("Date_Posted",date);
                        intent.putExtra("time",time);
//                        intent.putExtra("PostNewInformationKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_items,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}
