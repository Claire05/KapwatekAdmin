package com.malabiga.kapwatekadmin.Home_View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.malabiga.kapwatekadmin.R;

public class ViewFeedbackDescription extends AppCompatActivity {

    Button home;
    TextView title, message;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback_description);

        home = findViewById(R.id.home);
        title = findViewById(R.id.viewTitle);
        message =findViewById(R.id.viewMessage);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Feedback");
        String postTitle = getIntent().getExtras().getString("title");
        title.setText(postTitle);
        String postMessage = getIntent().getExtras().getString("message");
        message.setText(postMessage);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewFeedbackDescription.this, ViewPosts.class);
                startActivity(intent);
            }
        });
    }
}
