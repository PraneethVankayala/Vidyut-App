package com.example.vidyut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "hello";
    GoogleSignInClient mGoogleSignInClient;
    String token;
     private static final int RC_SIGN_IN = 0;
    List<Home> arrayList;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<HomeView> madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Home");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .build();

         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView= findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<Home>();
        arrayList.add(new Home("Contests",R.drawable.contests));
        arrayList.add(new Home("Workshops",R.drawable.work));
        arrayList.add(new Home("Concerts",R.drawable.concerts));
        arrayList.add(new Home("Ted-Talks",R.drawable.tedtalks));
        arrayList.add(new Home("Exhibition",R.drawable.exhibition));
        arrayList.add(new Home("Sponsers",R.drawable.sponsers));

            madapter=new HomeAdapter(arrayList,this);
            madapter.notifyDataSetChanged();
            recyclerView.setAdapter(madapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account != null){
            Bundle bundle=new Bundle();
            String token=account.getIdToken();
            bundle.putString("hello",token);
            Intent intent=new Intent(SignInActivity.this,MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.signin_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.sign_in:
                signIn();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            token=account.getIdToken();
            if(token!=null) {
                updateUI(account);
            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
}
