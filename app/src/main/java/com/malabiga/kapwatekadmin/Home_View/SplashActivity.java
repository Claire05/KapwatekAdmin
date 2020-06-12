package com.malabiga.kapwatekadmin.Home_View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.malabiga.kapwatekadmin.R;
import com.malabiga.kapwatekadmin.SampleOnly;


public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* Intent intent = new Intent(SplashActivity.this, SelectionScreenActivity.class);
                startActivity(intent);*/
                if(firebaseAuth.getCurrentUser()== null){
                    Intent intent = new Intent(SplashActivity.this, SampleOnly.class);
                    startActivity(intent);

                }else{
                    Intent intent = new Intent(SplashActivity.this, SampleOnly.class);
                    startActivity(intent);
                    }
                }


        }, 2000);
    }
}
