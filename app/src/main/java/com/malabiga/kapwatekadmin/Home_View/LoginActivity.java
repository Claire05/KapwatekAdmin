package com.malabiga.kapwatekadmin.Home_View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.malabiga.kapwatekadmin.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    //defining views
    private Button buttonSignIn, buttonforgotpass;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);

        progressDialog = new ProgressDialog(this);

        //attaching click listener
        buttonSignIn.setOnClickListener(this);

//get uid
//        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
//        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

//
//
//        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");
//        String datePosted =dateFormat.format(Calendar.getInstance().getTime());
//        Toast.makeText(this,""+datePosted,Toast.LENGTH_LONG).show();

        //Time_Posted
//        long date = new Date().getTime()*-1;
//        Toast.makeText(this,""+date,Toast.LENGTH_LONG).show();
//
//        Date currentTime = Calendar.getInstance().getTime();
//        Toast.makeText(this,""+currentTime,Toast.LENGTH_LONG).show();
////
//        Time now = new Time();
//        now.setTime();


        //TIME
//        DateFormat df = new SimpleDateFormat("h:mm a");
//        String date = df.format(Calendar.getInstance().getTime());
//        Toast.makeText(this, ""+date, Toast.LENGTH_LONG).show();

//        public long timeDifference(long current, long previous) {
//
//            int msPerMinute = 60 * 1000;
//            int msPerHour = msPerMinute * 60;
//            int msPerDay = msPerHour * 24;
//            int msPerMonth = msPerDay * 30;
//            int msPerYear = msPerDay * 365;
//
//            double elapsed = current - previous;
//
//            if (elapsed < msPerMinute) {
//                return Math.floor(elapsed / 1000);
//            } else if (elapsed < msPerHour) {
//                return Math.floor(elapsed / msPerMinute) + ' minutes ago';
//            } else if (elapsed < msPerDay) {
//                return Math.floor(elapsed / msPerHour) + ' hours ago';
//            } else if (elapsed < msPerMonth) {
//                return 'approximately ' + Math.floor(elapsed / msPerDay) + ' days ago';
//            } else if (elapsed < msPerYear) {
//                return 'approximately ' + Math.floor(elapsed / msPerMonth) + ' months ago';
//            } else {
//                return 'approximately ' + Math.floor(elapsed / msPerYear) + ' years ago';
//            }

//
//        long elapsed = System.nanoTime();
//
//        long tRes = elapsed - msPerMinute;
//
//        long msPerMinute = 60 * 1000;
//        long msPerHour = msPerMinute * 60;
//        long msPerDay = msPerHour * 24;
//        long msPerMonth = msPerDay * 30;
//        long msPerYear = msPerDay * 365;
//        if (elapsed < msPerMinute) {
//            return Math.floor(elapsed / 1000);
//        } else if (elapsed < msPerHour) {
//            return Math.floor(elapsed / msPerMinute) + ' minutes ago';
//        } else if (elapsed < msPerDay) {
//            return Math.floor(elapsed / msPerHour) + ' hours ago';
//        } else if (elapsed < msPerMonth) {
//            return 'approximately ' + Math.floor(elapsed / msPerDay) + ' days ago';
//        } else if (elapsed < msPerYear) {
//            return 'approximately ' + Math.floor(elapsed / msPerMonth) + ' months ago';
//        } else {
//            return 'approximately ' + Math.floor(elapsed / msPerYear) + ' years ago';
//
//
//        }

//         long tStart = System.nanoTime();
//        long tEnd = System.nanoTime();
//        long tRes = tEnd - tStart;
//        Toast.makeText(this,""+tRes,Toast.LENGTH_LONG).show();
//

    }


    //method for user login
    private void userLogin(){
        final String email = editTextEmail.getText().toString().trim();
        final String password  = editTextPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter username", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Signing in, please wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            if(email.equals("kapwa@gmail.com") && password.equals("admin")) {
                                Intent intent = new Intent(LoginActivity.this, ViewPosts.class);
                                startActivity(intent);
                            }else{
                                AlertDialog.Builder alert =  new AlertDialog.Builder(LoginActivity.this);
                                alert.setMessage("Please try again.").setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();

                                            }
                                        });
                                AlertDialog alertDialog = alert.create();
                                alertDialog.setTitle("Invalid Account");
                                alertDialog.show();
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        if (!email.equals("1")) {
                            Toast.makeText(LoginActivity.this,"Incorrect Username", Toast.LENGTH_LONG).show();
                        } else if (!password.equals("1")) {
                            Toast.makeText(LoginActivity.this,"Incorrect password", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this,"Successfully logged in", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, ViewPosts.class);
                            startActivity(intent);
                        }
                    }
                });

    }

    //back
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        return super.onKeyDown(keyCode, event);
    }

    public static class CheckNetwork {


        private static final String TAG = CheckNetwork.class.getSimpleName();



        public static boolean isInternetAvailable(Context context)
        {
            NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

            if (info == null)
            {
                Log.d(TAG,"no internet connection");
                return false;
            }
            else
            {
                if(info.isConnected())
                {
                    Log.d(TAG," internet connection available...");
                    return true;
                }
                else
                {
                    Log.d(TAG," internet connection");
                    return true;
                }

            }
        }
    }
    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            if(CheckNetwork.isInternetAvailable(LoginActivity.this)) //returns true if internet available
            {
                userLogin();
            }
            else
            {
                AlertDialog.Builder alert =  new AlertDialog.Builder(LoginActivity.this);
                alert.setMessage("No internet connection.").setCancelable(false)
                        .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alertDialog = alert.create();
                alertDialog.setTitle("No internet connection");
                alertDialog.show();
            }

        }

    }
}