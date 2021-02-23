package com.example.tracker.Login_Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.tracker.Database.UserHelperClass;
import com.example.tracker.R;
import com.example.tracker.userdashboard.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    PinView pinFromUser;
    String codeBySystem;
    TextView textView ;
    // data variables
    String  PhoneNumber , UserName, Email , Password , Registration , Gender ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verify_o_t_p);


        //hooks
        pinFromUser = findViewById(R.id.pin_view);
        textView = findViewById(R.id.text_otp) ;

        // getting data
        PhoneNumber = getIntent().getStringExtra("phone_number");
        UserName = getIntent().getStringExtra("user_name");
        Email = getIntent().getStringExtra("email");
        Password = getIntent().getStringExtra("Password");
        Registration = getIntent().getStringExtra("Registration");
        Gender = getIntent().getStringExtra("Gender");

        textView.setText("Enter One Time Password sent on " + PhoneNumber);
        sendVerificationCodeToUser(PhoneNumber) ;
        
    }

    private void sendVerificationCodeToUser(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s ;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if(code!= null){
                        pinFromUser.setText(code);
                        verifyCode(code) ; 
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(VerifyOTP.this , e.getMessage() , Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem , code) ;
        signInWithPhoneAuthCredential( credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance() ;

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            storeNewUserData() ;
                            Intent intent = new Intent(getApplicationContext() , Login.class) ;
                            startActivity(intent);


                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(VerifyOTP.this , "Verification Not Completed ! Try Again ." , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void storeNewUserData() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users") ;

        UserHelperClass addNewuser = new UserHelperClass(Registration , UserName, Email , Password , PhoneNumber, Gender) ;
        reference.child(Registration).setValue(addNewuser) ;
    }


    public void callNextScreenFromOTP(View view) {
        String code = pinFromUser.getText().toString() ;
        if(!code.isEmpty()){
            verifyCode(code);
        }
    }
}