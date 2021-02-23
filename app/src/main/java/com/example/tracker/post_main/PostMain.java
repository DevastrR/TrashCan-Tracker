package com.example.tracker.post_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tracker.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

public class  PostMain extends AppCompatActivity {


    EditText postComplaint , postHeading , registration;
    Button showUpload , postUpload ;

    FirebaseDatabase rootNode ;
    DatabaseReference reference ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_post_main);

        final LoadingDialog loadingDialog = new LoadingDialog(PostMain.this) ;

        // Hooks
        postComplaint = findViewById(R.id.editTextComplaint) ;
        postHeading = findViewById(R.id.editTextHeading) ;
        showUpload = findViewById(R.id.showUploadButton) ;
        postUpload = findViewById(R.id.buttonUpload) ;
        registration = findViewById(R.id.editTextRegistration) ;

        // save data to firebase
        postUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (registration.getText().toString().length() != 0){
                    loadingDialog.starLoadingDialog();
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        Toast.makeText(getApplicationContext(), "Complaint Uploaded . Thankyou !", Toast.LENGTH_SHORT).show();
                        ;
                    }
                }, 4000);

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("posts");

                //Get all the values
                String heading = postHeading.getText().toString();
                String complaint = postComplaint.getText().toString();
                String reg = registration.getText().toString();

                Upload helperClass = new Upload(heading, complaint);

                reference.child(reg).setValue(helperClass);
            }
                else {
                    registration.setError("Enter Reg. No. Please");
                }

            }
        });

        showUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , Images.class) ;
                startActivity(intent);
            }
        });


    }
}