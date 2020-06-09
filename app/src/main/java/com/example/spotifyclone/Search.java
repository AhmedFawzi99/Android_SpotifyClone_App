package com.example.spotifyclone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Search activity to type what the user wants to search
 * @author Salma Hazem
 * @version 1.0
 */
public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    /**
     *List that contains the animals
     * Abimal list in a String array
     */
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] animalNameList;
    //    String[] songNameList;
    ArrayList<String> songNameList;
    ArrayList<SongNames> arraylist = new ArrayList<SongNames>();

    /**
     * Assign Instances
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        songNameList = new ArrayList<String>();


        // Generate sample data

        Call<List<SearchResponse>> call = RetrofitSingleton.getInstance().getApi().searchList();
        call.enqueue(new Callback<List<SearchResponse>>() {
            @Override
            public void onResponse(Call<List<SearchResponse>> call, Response<List<SearchResponse>> response) {
                Log.d(response.message(), "onResponse: ");
                List<SearchResponse> s = response.body();

                for (SearchResponse searchResponse : s) {
                    String songName = searchResponse.getName();
//                    songNameList = new String[2];
                    songNameList.add(songName);
//                    Log.d("SONG NAMES" , String.valueOf(songNameList));
                }
                Log.d("SONG NAMES", String.valueOf(songNameList));
                accessArrayList(songNameList);
            }

            @Override
            public void onFailure(Call<List<SearchResponse>> call, Throwable t) {
                Log.d("SONG NAMES FAILED", String.valueOf(songNameList));
            }
        });
        Log.d("songNameListSize ", String.valueOf(songNameList.size()));

    }
    void accessArrayList(ArrayList<String> danceSchool){
        list = (ListView) findViewById(R.id.listview);

        Log.d("songNameListSize ", String.valueOf(songNameList.size()));
        for (int i = 0; i < songNameList.size(); i++) {
            Log.d("FOR LOOP ", "I ENTERED FOR LOOP");
            SongNames songNames = new SongNames(songNameList.get(i));
            Log.d("THE SONG IS: ", String.valueOf(songNameList));

            // Binds all strings into an array
            arraylist.add(songNames);
        }
        Log.d("FOR LOOP ", "I DID NOT ENTER FOR LOOP");
        Log.d("ARRAY LIST", String.valueOf(arraylist));
        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.setVisibility(View.GONE);
        Log.d("LIST", String.valueOf(list));
        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit (String query){
        list.setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public boolean onQueryTextChange (String newText){
        list.setVisibility(View.VISIBLE);
        String text = newText;
        adapter.filter(text);
        return false;

    }

}