package com.example.tracker.Login_Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tracker.R;
import com.example.tracker.userdashboard.UserDashboard;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    // variabes
    TextInputEditText registration, password;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //hooks
        registration = findViewById(R.id.Login_registration);
        password = findViewById(R.id.Login_password);
        progressBar = findViewById(R.id.login_progress);
        progressBar.setVisibility(View.INVISIBLE);

    }

    // login the user in app
    public void LetUserLogin(View view) {
        // validate password and username
        if (!validateFields()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        // get data
        final String _registration = registration.getText().toString().trim();
        final String _password = password.getText().toString().trim();

        //Database
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("registration").equalTo(_registration);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // if id exists
                if (snapshot.exists()) {
                    registration.setError(null);
                    registration.setEnabled(false);

                    // if password for the id exists
                    String systemPassword = snapshot.child(_registration).child("password").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        password.setError(null);
                        password.setEnabled(false);


                        //Retrieving data
                        String _fullname = snapshot.child(_registration).child("fullname").getValue(String.class);
                        String _email = snapshot.child(_registration).child("email").getValue(String.class);
                        String _phoneNo = snapshot.child(_registration).child("phoneNumber").getValue(String.class);


                        Intent intent = new Intent(getApplicationContext() , UserDashboard.class) ;
                        intent.putExtra("fullName" , _fullname) ;
                        intent.putExtra("email" , _email) ;
                        intent.putExtra("registration" , _registration) ;
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Account doesn't exist ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private boolean validateFields() {
        String _registration = registration.getText().toString().trim();
        String _password = password.getText().toString().trim();

        if (_registration.isEmpty()) {
            registration.setError("Reg. No. can not be empty");
            registration.requestFocus();
            return false;
        } else if (_password.isEmpty()) {
            password.setError("Enter Password");
            password.requestFocus();
            return false;
        }
        return true;
    }


    public void btn_signupForm(View view) {
        // using intent
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }

}