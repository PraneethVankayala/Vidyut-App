package com.example.vidyut;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "hello";
    public static GoogleSignInClient mGoogleSignInClient;
    String token;
    ProgressBar progressBar;
    public static  String Auth;
    Data data;
    ApiManager apiManager=new ApiManager();
     private static final int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        progressBar = findViewById(R.id.progressBar);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .build();

         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null) {
            getAuth(account);
            updateUI(account);
        }

    }

    private void updateUI(GoogleSignInAccount account) {
        if(account != null){
            Intent intent=new Intent(SignInActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    public void detailsverify(String auth, GoogleSignInAccount GoogleSignInAccount ){

        apiManager.getUser(auth, new Callback<User>() {
            boolean s;
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
               User user=response.body();
                String vid=user.getDatas().getVid();
                boolean d=user.getDatas().isDetails();
                boolean e=user.getDatas().isEdu();
                String email=user.getDatas().getEmail();
                String fname=user.getDatas().getFname();
                String lname=user.getDatas().getLname();
                String pic=user.getDatas().getPic();
                String phno=user.getDatas().getPhno();

                data=new Data(vid,email,fname,lname,pic,d,e,phno);

               if(data.isDetails() && data.isEdu()){
                   updateUI(GoogleSignInAccount);
                }
                else{
                    //mGoogleSignInClient.signOut();
                   Bundle bu = new Bundle();
                   bu.putString("token",token);
                   Intent i = new Intent(SignInActivity.this,EditProfileActivity.class);
                   i.putExtras(bu);
                   startActivity(i);
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Error is " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }

        });
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
            final GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            AuthToken authToken=new AuthToken(account.getIdToken());
            apiManager.createId(authToken, new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    ResponseAuth responseAuth=response.body();
                    try {
                        Auth = responseAuth.getAuth();
                        detailsverify(Auth,account);
                        //Toast.makeText(getApplicationContext(),responseAuth.getAuth(),Toast.LENGTH_LONG).show();
                    }
                    catch (NullPointerException n)
                    {
                        mGoogleSignInClient.signOut();
                        Toast.makeText(getApplicationContext(),"Error is " + n.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),
                            "Error is " + t.getMessage()
                            , Toast.LENGTH_LONG).show();
                }
            });

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    public void getAuth(GoogleSignInAccount account){

        AuthToken authToken=new AuthToken(account.getIdToken());
        apiManager.createId(authToken, new Callback<ResponseAuth>() {
            @Override
            public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                ResponseAuth responseAuth=response.body();
                try{        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

                    Auth=responseAuth.getAuth();
                    //Toast.makeText(getApplicationContext(),responseAuth.getAuth(),Toast.LENGTH_LONG).show();
                }
                catch (NullPointerException e){
                    mGoogleSignInClient.signOut();
                    Toast.makeText(getApplicationContext(),"Error is "+e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseAuth> call, Throwable t) {
                mGoogleSignInClient.signOut();
                Toast.makeText(getApplicationContext(),
                        "Error is " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
