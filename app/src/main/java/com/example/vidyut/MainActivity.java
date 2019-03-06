package com.example.vidyut;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.vidyut.BottomNavigation.About;
import com.example.vidyut.BottomNavigation.Dashboard;
import com.example.vidyut.BottomNavigation.Home;
import com.example.vidyut.BottomNavigation.QrCode;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActionBar ab = getSupportActionBar();
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffffff"));
//        ab.setBackgroundDrawable(colorDrawable);
          getSupportActionBar().setDisplayShowTitleEnabled(false);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
//        upArrow.setColorFilter(getResources().getColor(R.color.lightBlack), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        Intent intent=getIntent();
//        Bundle b=intent.getExtras();
//        String s=b.getString("hello");
        loadFragment(new Home());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.animate();
        navigation.setFocusable(true);

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;

        }
        return false;
    }

    private void signOut() {
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent=new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                setTitle("Home");
                fragment = new Home();
                break;

            case R.id.navigation_dashboard:
                setTitle("Dashboard");
                fragment = new Dashboard();
                break;

            case R.id.navigation_qrcode:
                setTitle("QrCode");
                fragment = new QrCode();
                break;

            case R.id.navigation_about:
                setTitle("About");
                fragment = new About();
                break;
        }

        return loadFragment(fragment);
    }
}