//package com.example.spotifyclone;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.io.IOException;
//
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//
//    Retrofit.Builder builder = new Retrofit.Builder()
//            .baseUrl("http://3.137.69.49:3000/user/")
//            .addConverterFactory(GsonConverterFactory.create());
//    Retrofit retrofit = builder.build();
//    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//
//    EditText etEmail, etPassword, etBirthdate, etGender, etName;
//    Button bLogout, bSignup1;
//    private TextView textViewResult;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        bLogout = (Button) findViewById(R.id.bLogout);
//        bSignup1 = (Button) findViewById(R.id.bSignup1);
//        etEmail = (EditText) findViewById(R.id.etEmail1);
//        etPassword = (EditText) findViewById(R.id.etPassword1);
//        etBirthdate = (EditText) findViewById(R.id.etBirthdate1);
//        etGender = (EditText) findViewById(R.id.etGender1);
//        etName = (EditText) findViewById(R.id.etName1);
//        textViewResult = (TextView) findViewById(R.id.textViewResult);
//
//        bSignup1.setOnClickListener(this);
//        bLogout.setOnClickListener(this);
//
//        //textViewResult = (TextView) findViewById(R.id.text_view_result);
//
////        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl("https://jsonplaceholder.typicode.com/")
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
////
////        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
////
////        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
////
////        call.enqueue(new Callback<List<Post>>() {
////            @Override
////            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
////                if(!response.isSuccessful()){
////                    textViewResult.setText("Code:" + response.code());
////                    return;
////                }
////                List<Post> posts = response.body();
////                for(Post post : posts) {
////                    String content = "";
////                    content += "ID:" + post.getId() + "\n";
////                    content += "User ID:" + post.getUserId() + "\n";
////                    content += "Text:" + post.getText() + "\n";
////                    content += "Title:" + post.getTitle() + "\n\n";
////
////                    textViewResult.append(content);
////
////                }
////            }
////
////            @Override
////            public void onFailure(Call<List<Post>> call, Throwable t) {
////                textViewResult.setText(t.getMessage());
////            }
////        });
//
//    }
//    // This is to keep the user logged in
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Shared shared = new Shared(getApplicationContext());
//        shared.firstTime();
//    }
//    private void userSignUp() {
//        String email = etEmail.getText().toString().trim();
//        String password = etPassword.getText().toString().trim();
//        String birthdate = etBirthdate.getText().toString().trim();
//        String gender = etGender.getText().toString().trim();
//        String name = etName.getText().toString().trim();
//
//        Call<ResponseBody> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .createUser(email, password,name, birthdate, gender);
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    if(response.isSuccessful()){
//                        String s = response.body().string();
//                        int d = response.code();
//                        Toast.makeText(MainActivity.this, "Created", Toast.LENGTH_LONG).show();
//                    }
//                    else
//                        Toast.makeText(MainActivity.this, "Not Created", Toast.LENGTH_LONG).show();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case(R.id.bSignup1):
//                userSignUp();
//            case(R.id.bLogout):
//                startActivity(new Intent(this, firstPage.class));
//                break;
//        }
//    }
//}
