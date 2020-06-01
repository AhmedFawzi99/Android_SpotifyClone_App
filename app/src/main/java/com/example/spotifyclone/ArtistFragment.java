package com.example.spotifyclone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment {


    public ArtistFragment() {
        // Required empty public constructor
    }
    private TextView textViewResult;
    View s;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        s=inflater.inflate(R.layout.fragment_artist,container,false);
        textViewResult=s.findViewById(R.id.text_view_result);
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<post>>() {
            @Override
            public void onResponse(Call<List<post>> call, Response<List<post>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code:"+response.code());
                    return;
                }
                List<post> posts= response.body();
                for(post pt : posts)
                {
                    String content ="";
                    content += "ID"+ pt.getId()+"\n";
                    content += "UserID"+ pt.getUserid()+"\n";
                    content += "Title"+ pt.getTitle()+"\n";
                    content += "Text"+ pt.getText()+"\n\n";
                    textViewResult.append(content);



                }
            }

            @Override
            public void onFailure(Call<List<post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist, container, false);
    }

}
