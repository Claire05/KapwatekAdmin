package com.malabiga.kapwatekadmin.Approval.Post_Approval;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malabiga.kapwatekadmin.Home_View.Donators.View_Donators;
import com.malabiga.kapwatekadmin.Model.PostNewInformation;
import com.malabiga.kapwatekadmin.Home_View.Participate.View_Particapates;
import com.malabiga.kapwatekadmin.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ORG_Home_Cardview_Adapter extends RecyclerView.Adapter<ORG_Home_Cardview_Adapter.postViewHolder>{

   private Context mContext;
   private List<PostNewInformation> mPosts;


   public ORG_Home_Cardview_Adapter(Context context, List<PostNewInformation> posts){
      mContext = context;
      mPosts = posts;
   }


   @NonNull
   @Override
   public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//We used the Context to use and call the other xml
      View v = LayoutInflater.from(mContext).inflate(R.layout.activity_view_post_description_cardview, parent, false);
      return new postViewHolder(v);
   }

   //In this method, we are going get our data
   @Override
   public void onBindViewHolder(@NonNull final postViewHolder holder, int position) {
//we used posts to get our reference
      PostNewInformation postCurrent = mPosts.get(position);
//This will call all the contents that we added from the admin posts, the getTitle came from the Posts
//      holder.textViewGoal.setText("Goal: ₱"+postCurrent.getDonations_Goal()+ ".00");
      holder.textViewTitle.setText(postCurrent.getCampaign_Title());
      holder.textViewSummary.setText(postCurrent.getStory_Description());
      holder.textViewStatus.setText(postCurrent.getTypeStatus());
      holder.btnViewPost.setVisibility(View.VISIBLE);
      holder.onClick(position);
//      holder.btn_viewDonators.setVisibility(View.VISIBLE);
      holder.onClick2(position);
//      holder.btn_participate.setVisibility(View.VISIBLE);
      holder.onClick3(position);

      String title = postCurrent.getCampaign_Title();
      if (title.length()>=50){
         String titleLength = title.substring(0,50);
         holder.textViewTitle.setText(titleLength+"...");
      }else{
         holder.textViewTitle.setText(postCurrent.getCampaign_Title());
      }

      String description = postCurrent.getStory_Description();
      if (description.length()>=180){
         String descrptionLength = description.substring(0,180);
         holder.textViewSummary.setText(descrptionLength+"...");
      }else{
         holder.textViewSummary.setText(postCurrent.getStory_Description());
      }

      DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Campaign_ad");
      databaseReference.orderByChild("campaign_Title").equalTo(title).addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

               if (postSnapshot.hasChild("volunteers_Goal")){
                  String volunteerGoal = postSnapshot.child("volunteers_Goal").getValue().toString();
                  holder.tv_volunteerGoal.setVisibility(View.VISIBLE);
                  holder.tv_volunteerGoal.setText("Volunteers needed: " + volunteerGoal);
               }
               if (postSnapshot.hasChild("donations_Goal")){
                  String goal = postSnapshot.child("donations_Goal").getValue().toString();
                  holder.textViewGoal.setVisibility(View.VISIBLE);
                  holder.textViewGoal.setText("Goal: ₱" + goal + ".00");
               }
            }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
      });

//We Used Picasso to also call the image URL and Push it in the view post, WE USED CENTER CROP TO GET THE IMAGE FULLY
      Picasso.get().load(postCurrent.getAnnouncement_Picture()).resize(200,100).into(holder.imageView);

      String eventTag = postCurrent.getEvent_Tag();
      if (eventTag.equals("Incoming")){
          holder.eventStatus.setText("Incoming");
          holder.bgLinear.setBackgroundColor(Color.BLUE);
      }

      if (eventTag.equals("Done")){
          holder.eventStatus.setText("Expired Event");
          holder.bgLinear.setBackgroundColor(Color.RED);
      }
   }

   //Loop ite
   @Override
   public int getItemCount() {
      return mPosts.size();
   }

   public class postViewHolder extends RecyclerView.ViewHolder{

      public TextView textViewGoal,textViewTitle, textViewSummary,textViewStatus, eventStatus, tv_volunteerGoal;
      public ImageView imageView;
      Button btnViewPost, btn_viewDonators, btn_participate;
      LinearLayout bgLinear;

      public postViewHolder(@NonNull View itemView) {
         super(itemView);

//Call the Entities to view the Posts that was added by the Admin
         bgLinear = itemView.findViewById(R.id.bgLinear);
         eventStatus = itemView.findViewById(R.id.eventStatus);
         textViewGoal = itemView.findViewById(R.id.tv_goalPostView);
         textViewTitle = itemView.findViewById(R.id.tv_titlePostView);
         textViewSummary = itemView.findViewById(R.id.tv_descriptionPostView);
         textViewStatus = itemView.findViewById(R.id.tv_status);
         imageView = itemView.findViewById(R.id.img_imagePostView);
         btnViewPost=itemView.findViewById(R.id.btnViewPost);
         btn_viewDonators= itemView.findViewById(R.id.btn_viewDonators);
         btn_participate = itemView.findViewById(R.id.btn_participate);
         tv_volunteerGoal = itemView.findViewById(R.id.tv_volunteerGoal);
      }

      public void onClick(final int position) {
         btnViewPost.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent i = new Intent(mContext, View_Post_Description.class);
            i.putExtra("Announcement_Picture", mPosts.get(position).getAnnouncement_Picture());
//            i.putExtra("Donation_Goal",mPosts.get(position).getDonations_Goal());
            i.putExtra("Campaign_Title",mPosts.get(position).getCampaign_Title());
            i.putExtra("Story_Description",mPosts.get(position).getStory_Description());
            i.putExtra("Author",mPosts.get(position).getAuthor());
            i.putExtra("Date_Posted",mPosts.get(position).getDate_Posted());
            i.putExtra("typeStatus",mPosts.get(position).getTypeStatus());
            i.putExtra("date_Posted",mPosts.get(position).getDate_Posted());
            i.putExtra("address",mPosts.get(position).getAddress());
            i.putExtra("time", mPosts.get(position).getTime());
            i.putExtra("uid", mPosts.get(position).getUid());
            mContext.startActivity(i);
         }
      });
   }

   public void onClick2(final int position){
         btn_participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(mContext, View_Particapates.class);
               i.putExtra("Announcement_Picture", mPosts.get(position).getAnnouncement_Picture());
               mContext.startActivity(i);
            }
         });
   }

   public void onClick3(final int position){
         btn_viewDonators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(mContext, View_Donators.class);
               i.putExtra("uid",mPosts.get(position).getUid());
               i.putExtra("Announcement_Picture", mPosts.get(position).getAnnouncement_Picture());
               mContext.startActivity(i);
            }
         });
   }

      private void blink() {
         String txt1 = "Happening";
         final TextView txt = itemView.findViewById(R.id.eventStatus);
         final Handler handler = new Handler();
         new Thread(new Runnable() {
            @Override
            public void run() {
               int timeToBlink = 2000;    //in milissegunds
               try {
                  Thread.sleep(timeToBlink);
               } catch (Exception e) {
               }
               handler.post(new Runnable() {
                  @Override
                  public void run() {
                     if (txt.getVisibility() == View.VISIBLE) {
                        txt.setVisibility(View.INVISIBLE);
                     } else {
                        txt.setVisibility(View.VISIBLE);
                     }
                     blink();
                  }
               });
            }
         }).start();
      }
   }
}
