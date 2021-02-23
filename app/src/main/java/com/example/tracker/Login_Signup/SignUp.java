package com.example.tracker.Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracker.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {
    // Variables
    ImageView backbtn;
    Button nextBtn;
    TextView title;


    // Data Variables
    TextInputEditText Username, registration , email , password ;
    RadioGroup radioGroup ;
    RadioButton radioButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        // Hooks
        backbtn = findViewById(R.id.back_arrow);
        nextBtn =  findViewById(R.id.nextBtn);
        title =  findViewById(R.id.sign_up_title);

        // Hooks for data var
        Username = findViewById(R.id.fullName) ;
        registration = findViewById(R.id.registration) ;
        email = findViewById(R.id.email) ;
        password = findViewById(R.id.password) ;
        radioGroup = findViewById(R.id.radioGroup) ;
    }

    // Intent
    public void callNextMobileScreen(View view) {
        if (!validateEmail() | !validateFullName() | !validateGender() |!validatePassword() | !validateRegistration() ) {
            return ;
        }

        // Transferring data to another activity
        String username  = Username.getText().toString() ;
        String Email = email.getText().toString() ;
        String Password = password.getText().toString() ;
        String Registration = registration.getText().toString() ;
        radioButton = findViewById(radioGroup.getCheckedRadioButtonId()) ;
        String Gender = radioButton.getText().toString().trim();



        Intent intent = new Intent(getApplicationContext(), SignUp2.class);
        intent.putExtra("user_name" , username) ;
        intent.putExtra("email" , Email) ;
        intent.putExtra("Password" , Password) ;
        intent.putExtra("Registration" , Registration) ;
        intent.putExtra("Gender" , Gender) ;

        Pair[] pairs = new Pair[3] ;
        pairs[0] = new Pair<View , String>(backbtn , "transition_arrow_back" ) ;
        pairs[1] = new Pair<View , String>(nextBtn, "transition_next_button" ) ;
        pairs[2] = new Pair<View , String>(title, "transition_title_text" ) ;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this , pairs) ;
        startActivity(intent , options.toBundle());

    }

    public void callBackMobileScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);

    }
    /*
    Validation functions
     */
    private boolean validateFullName(){
        String val = Username.getText().toString().trim() ;
        if(val.isEmpty()){
            Username.setError("Field can not be empty");
            return false ;
        }
        else {
            Username.setError(null);
            return true ;
        }
    }

    private boolean validateRegistration(){
        String val = registration.getText().toString().trim() ;
        String checkspaces ="\\A\\w{1,11}\\z";
        if(val.isEmpty()){
            registration.setError("Field can not be empty");
            return false ;
        }
        else if (val.length()>11){
            registration.setError("R.Number too large ");
            return false ;
        }
        else if (!val.matches(checkspaces)){
            registration.setError("No white spaces are allowed");
            return false ;
        }
        else {
            registration.setError(null);
            return true ;
        }
    }

    private boolean validateEmail(){
        String val = email.getText().toString().trim() ;
        String checkEmail ="[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if(val.isEmpty()){
            email.setError("Field can not be empty");
            return false ;
        }
        else if (!val.matches(checkEmail)){
            email.setError("Invalid Email");
            return false ;
        }
        else {
            email.setError(null);
            return true ;
        }
    }

    private boolean validatePassword(){
        String val = password.getText().toString().trim() ;
        String checkPassword  = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                //"(?=S+$)" +             //no white spaces
                ".{4,}" ;

        if(val.isEmpty()){
            password.setError("Field can not be empty");
            return false ;
        }
        else if (!val.matches(checkPassword)){
            password.setError("Invalid password - contain atleast lower , upper , special character , digit and no white spaces .  ");
            return false ;
        }
        else {
            password.setError(null);
            return true ;
        }
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }



}