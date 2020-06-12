package com.malabiga.kapwatekadmin.Home_View.Participate;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.malabiga.kapwatekadmin.R;

public class View_Participates_Information extends AppCompatActivity {

    ImageView circleImageView;
    TextView donatorName, amount, date;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participates_information);

        getContent();
        circleImageView = findViewById(R.id.circleImageView);
        donatorName= findViewById(R.id.donatorName);
        amount = findViewById(R.id.amount);
    }

    private void getContent() {
        if (getIntent().hasExtra("user_Profile")
                && getIntent().hasExtra("name")){

            String userImage = getIntent().getStringExtra("name");
            String firstName = getIntent().getStringExtra("user_Profile");

            setImage(userImage, firstName);
        }
    }

    private void setImage(String userImage, String firstName) {
        ImageView images = findViewById(R.id.circleImageView);
        Glide.with(this)
                .asBitmap()
                .load(userImage)
                .into(images);
        TextView name = findViewById(R.id.donatorName);
//        TextView amount1 = findViewById(R.id.amount);
        name.setText(firstName);
    }
}
