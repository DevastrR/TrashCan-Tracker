package com.example.tracker.Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.example.tracker.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUp2 extends AppCompatActivity {
    // variables
    TextInputEditText phoneNumber ;
    CountryCodePicker countryCodePicker ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2);
        phoneNumber = findViewById(R.id.phoneNumber) ;
        countryCodePicker = findViewById(R.id.CodePicker) ;
    }


    public void callVerifyOTPScreen(View view) {
        // validate fields
        if (!validatePhoneNumber()) {
            return ;
        }

        // retrieving data from previous activities
        String UserName = getIntent().getStringExtra("user_name");
        String Email = getIntent().getStringExtra("email");
        String Password = getIntent().getStringExtra("Password");
        String Registration = getIntent().getStringExtra("Registration");
        String Gender = getIntent().getStringExtra("Gender") ;

        String getEnteredPhoneNumber = phoneNumber.getText().toString().trim() ;
        String phone = "+91"+ getEnteredPhoneNumber ;



        Intent intent = new Intent(getApplicationContext() , VerifyOTP.class) ;
        intent.putExtra("phone_number" , phone) ;
        intent.putExtra("user_name" , UserName) ;
        intent.putExtra("email" , Email) ;
        intent.putExtra("Password" , Password) ;
        intent.putExtra("Registration" , Registration) ;
        intent.putExtra("Gender" , Gender) ;
        startActivity(intent );
    }


    public void callBackMobileScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);

    }

    private boolean validatePhoneNumber() {
        String val = phoneNumber.getText().toString().trim();
        String checkspaces = "Aw{0,10}z";
        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        }
        else if (val.length()!= 10){
            phoneNumber.setError("Enter 10 digit phone number");
            return false ;
        }
        else {
            phoneNumber.setError(null);
            return true;
        }
    }
}