package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

//import com.google.firebase.quickstart.auth.R;


public class facebook extends AppCompatActivity {

    private LoginButton loginButton;
    private CircleImageView circleImageView;
    private TextView txtName,txtEmail;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    String TAG = "facebook Login";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb);

// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        loginButton = findViewById(R.id.login_button);
        txtName = findViewById(R.id.profile_name);
        txtEmail = findViewById(R.id.profile_email);
        circleImageView = findViewById(R.id.profile_photo);

        callbackManager = CallbackManager.Factory.create();

        // Adding back button
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loginButton.setPermissions(Arrays.asList("email", "public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess"+loginResult);

                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                handleFacebookAccessToken(accessToken);

                Toast.makeText(getApplicationContext(), "Login Success with facebook", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(facebook.this, LoggedIn.class));

               // startActivity(new Intent(this, MainActivity.class));
                //call new activity here.
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        // callbackManager onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode,resultCode,data);
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(facebook.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

//    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//            if(currentAccessToken==null){
//                txtName.setText("");
//                txtEmail.setText("");
//                circleImageView.setImageResource(0);
//                Toast.makeText(facebook.this,"User Logged out", Toast.LENGTH_LONG).show();
//
//            }
//            else
//                loadUserProfile(currentAccessToken);
//
//        }
//    };

//    private void loadUserProfile(AccessToken newAccessToken){
//        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                try{
//                    String first_name = object.getString("first_name");
//                    String last_name = object.getString("last_name");
//                    String email = object.getString("email");
//                    String id = object.getString("id");
//                    String image_url = "https://graph.facebook.com/"+id+ "/picture?type=normal";
//
//                    txtEmail.setText(email);
//                    txtName.setText(first_name+""+last_name);
//                    RequestOptions requestOptions = new RequestOptions();
//                    requestOptions.dontAnimate();
//
//                    Glide.with(facebook.this).load(image_url).into(circleImageView);
//
//
//
//                } catch (JSONException e){
//                    e.printStackTrace();
//                }

//
//
//            }
//        });

//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "first_name,last_name,email_id");
//        request.setParameters(parameters);
//        request.executeAsync();



        //go to Home page
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        finish();

       // profile = Profile.getCurrentProfile().getCurrentProfile();
//        if (Profile.getCurrentProfile().getCurrentProfile() != null) {
//            startActivity(new Intent(this, MainActivity.class));
//
//            // user has logged in
//        } else {
//            // user has not logged in
//        }
//    }
////    catch (Exception e) {
//        Log.d("ERROR", e.toString());
//    }
}


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if(id==android.R.id.home) {
//            this.finish();
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
