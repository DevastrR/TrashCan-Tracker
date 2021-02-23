package com.example.tracker.userdashboard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracker.HelperClass.AdapterClass.ComplaintAdapter;
import com.example.tracker.HelperClass.AdapterClass.ComplaintHelperClass;
import com.example.tracker.HelperClass.AdapterClass.FeaturedAdapter;
import com.example.tracker.HelperClass.AdapterClass.FeaturedHelperClass;
import com.example.tracker.Login_Signup.Login;
import com.example.tracker.R;
import com.example.tracker.post_main.PostMain;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // homescreen variables
    RecyclerView featuredRecycler, complaintRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    ImageView imageView,imageView1, imageView2, imageView3;
    // Drawer Menu variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView Nav_welcome, Nav_registration, Nav_email;

     ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            */

        setContentView(R.layout.activity_user_dashboard);


        // Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        complaintRecycler = findViewById(R.id.complaint_recycler);

        // Menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        /*
        //
        Nav_welcome = (TextView) findViewById(R.id.nav_welcome) ;
        Nav_registration = (TextView) findViewById(R.id.nav_registration) ;
        Nav_email = (TextView) findViewById(R.id.nav_email) ;

        //
        String welcom_msg = getIntent().getStringExtra("fullName") ;
        String email_msg = getIntent().getStringExtra("email") ;
        String reg_msg = getIntent().getStringExtra("registration") ;

        //
        Nav_welcome.setText(welcom_msg);
        Nav_registration.setText(reg_msg);
        Nav_email.setText(email_msg);
        */


        // recycler view functions
        featuredRecycler();
        complaintRecycler();
    }

    private void complaintRecycler() {

        ArrayList<ComplaintHelperClass> complaintHelperClasses = new ArrayList<>();
        complaintHelperClasses.add(new ComplaintHelperClass("Badminton Court Sewer leakage ", "Badminton Court is smeeling bad from past 3 weeks . Authorities have not done anything yet . Also , Mosquitoes is breeding there "));
        complaintHelperClasses.add(new ComplaintHelperClass("Tank Area Not Cleaned ", " Tank Area Not Cleaned for 7 months . Bad smell . Hostellers getting bad taste while bathing and drinking  "));
        complaintHelperClasses.add(new ComplaintHelperClass("Food Court Not Cleaned ", " Difficult to sit and eat . Dirty smell and soiled view spoils the aura of Under Belly  .  "));


        complaintRecycler.setHasFixedSize(true);
        adapter = new ComplaintAdapter(complaintHelperClasses);
        complaintRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        complaintRecycler.setAdapter(adapter);
    }

    private void featuredRecycler() {
        //sethasFixed method displays item acc to space
        featuredRecycler.setHasFixedSize(true);
        // setting view of recycler to horizontal - by default its vertical
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.ub, "Under Belly", " Table seat cleaned . New dustbin has been put"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.campus2, "VIT Campus", "Parking lot and Main campus Gate 2 cleaned  "));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.hostel, "Boy's Hostel", "Mess area , Hostel Backyard got cleaned"));


        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Toast.makeText(getApplicationContext() , "Home"  , Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_maps :
                break ;
            case R.id.nav_logout :
                /* Progress bar dialog */
                setProgressDialog();
                Intent intent = new Intent(getApplicationContext() , Login.class);
                startActivity(intent);
                break ;
            case R.id.nav_report :
                Intent intent1 = new Intent(getApplicationContext() , PostMain.class) ;
                startActivity(intent1);
                Toast.makeText(getApplicationContext() , "Let us know your problem ! "  , Toast.LENGTH_LONG).show();
                break ;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callPostActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), PostMain.class);
        startActivity(intent);
    }

    public void setProgressDialog() {

        int llPadding = 40;
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(llPadding, llPadding, llPadding, llPadding);
        ll.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        ll.setLayoutParams(llParam);

        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        progressBar.setPadding(0, 0, llPadding, 0);
        progressBar.setLayoutParams(llParam);

        llParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        TextView tvText = new TextView(this);
        tvText.setText("Log Out");
        tvText.setTextColor(Color.parseColor("#000000"));
        tvText.setTextSize(20);
        tvText.setLayoutParams(llParam);

        ll.addView(progressBar);
        ll.addView(tvText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setView(ll);

        AlertDialog dialog = builder.create();
        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);
        }
    }

    public void ghostel(View view) {
        imageView = findViewById(R.id.iv4);
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.mapsactivity02");
        if (intent != null){
            startActivity(intent);
        }
    }

    public void bhostel(View view) {
        imageView1 = findViewById(R.id.iv3);
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.mapsacitvity03");
        if (intent != null){
            startActivity(intent);
        }
    }

    public void ubelly(View view) {
        imageView2 = findViewById(R.id.iv2);
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.maps");
        if (intent != null){
            startActivity(intent);
        }
    }

    public void Campus(View view) {
        imageView3 = findViewById(R.id.iv1);
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.maps");
        if (intent != null) {
            startActivity(intent);
        }
    }
}
