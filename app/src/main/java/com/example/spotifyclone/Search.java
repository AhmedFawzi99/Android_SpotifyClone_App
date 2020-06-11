package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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
 *Helping Source: <a href="https://www.youtube.com/watch?v=nQnyAXJxngY">https://www.youtube.com/watch?v=nQnyAXJxngY</a> <br>
 * @author Salma Hazem
 * @version 1.0
 */
public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    /**
     *List that contains the songs
     * Song list in a String array
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

        /**
         * Generate sample data
         */
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
                accessArrayList(songNameList);
            }

            @Override
            public void onFailure(Call<List<SearchResponse>> call, Throwable t) {
                Log.d("SONG NAMES FAILED", String.valueOf(songNameList));
            }
        });

    }
    /**
     * Access the array retrieved from the server
     * @param danceSchool
     */
    void accessArrayList(ArrayList<String> danceSchool){
        list = (ListView) findViewById(R.id.listview);
        for (int i = 0; i < songNameList.size(); i++) {
            SongNames songNames = new SongNames(songNameList.get(i));

            // Binds all strings into an array
            arraylist.add(songNames);
        }
        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.setVisibility(View.GONE);
        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        /**
         * Moves to the SearchedSong Activity and show the searched song
         */
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int actualPosition = position;
                String searched_song = ((SongNames)parent.getItemAtPosition(position)).getSongName();
                Toast.makeText(Search.this,"The song is: "+searched_song, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Search.this, SearchedSong.class);
                intent.putExtra("SearchedSong", searched_song);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit (String query){
        return false;
    }

    @Override
    public boolean onQueryTextChange (String newText){
        list.setVisibility(View.VISIBLE);
        String text = newText;
        adapter.filter(text);
        if(newText.isEmpty()) {
            list.setVisibility(View.GONE);
        }
        return false;
    }
}