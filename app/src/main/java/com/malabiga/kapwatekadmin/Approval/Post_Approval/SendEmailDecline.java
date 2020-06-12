package com.malabiga.kapwatekadmin.Approval.Post_Approval;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.malabiga.kapwatekadmin.Home_View.MainActivity;
import com.malabiga.kapwatekadmin.JavaMailAPI;
import com.malabiga.kapwatekadmin.R;

public class SendEmailDecline extends AppCompatActivity {

    public EditText mEmail;
    public EditText mSubject;
    public EditText mMessage;
    CheckBox valid1, valid2, valid3, valid4, valid5, valid6, valid7, valid8;
    Button cancel, send;

    String valid_1 = "", valid_2 = "", valid_3 = "", valid_4 = "", valid_5 = "", valid_6 = "", valid_7 = "", valid_8 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email_post_decline);

        mEmail = (EditText) findViewById(R.id.mailID);
        mMessage = (EditText) findViewById(R.id.messageID);
        mSubject = (EditText) findViewById(R.id.subjectID);
        valid1 = findViewById(R.id.valid1);
        valid2 = findViewById(R.id.valid2);
        valid3 = findViewById(R.id.valid3);
        valid4 = findViewById(R.id.valid4);
        valid5 = findViewById(R.id.valid5);
        valid6 = findViewById(R.id.valid6);
        valid7 = findViewById(R.id.valid7);
        valid8 = findViewById(R.id.valid8);


        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
                Intent i = new Intent(SendEmailDecline.this, MainActivity.class);
                startActivity(i);
            }
        });
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SendEmailDecline.this, MainActivity.class);
                startActivity(i);
            }
        });
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    private void sendMail() {

        if (valid1.isChecked()) {
            valid_1= "\n"+"●\t"+valid1.getText().toString();
        } else {
            valid_1 = "";
        }
        if (valid2.isChecked()) {
            valid_2= "\n"+"●\t"+valid2.getText().toString();
        } else {
            valid_2 = "";
        }
        if (valid3.isChecked()) {
            valid_3= "\n"+"●\t"+valid3.getText().toString();
        } else {
            valid_3 = "";
        }
        if (valid4.isChecked()) {
            valid_4= "\n"+"●\t"+valid4.getText().toString();
        } else {
            valid_4 = "";
        }
        if (valid5.isChecked()) {
            valid_5= "\n"+"●\t"+valid5.getText().toString();
        } else {
            valid_5 = "";
        }
        if (valid6.isChecked()) {
            valid_6= "\n"+"●\t"+valid6.getText().toString();
        } else {
            valid_6 = "";
        }
        if (valid7.isChecked()) {
            valid_7= "\n"+"●\t"+valid7.getText().toString();
        } else {
            valid_7 = "";
        }
        if (valid8.isChecked()) {
            valid_8= "\n"+"●\t"+valid8.getText().toString();
        } else {
            valid_8 = "";
    }
        String email= getIntent().getStringExtra("email");

    String mail = email;
        mEmail.setText(email);
    String message = "This email is to tell you that your account have been rejected by our Admin due to the following reason/s:"+"\n"+valid_1+valid_2+valid_3+valid_4+valid_5+valid_6+valid_7+valid_8;

    //        String message = mMessage.getText().toString();
    String subject = "Kapwatek";

    //Send Mail
    JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message);

        javaMailAPI.execute();

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


}