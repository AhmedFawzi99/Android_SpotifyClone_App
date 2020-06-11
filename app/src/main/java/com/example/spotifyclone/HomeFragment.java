package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Shaimaa Osama
 */
public class HomeFragment extends Fragment {
    private List<Category> dataList;
    private List<RowItem> dataList2;
    private Category recentlyplayed;
    private TextView textView;
    private onClickInterface onclickInterface;
    private onClickInterface onclickInterface2;
    private RecyclerView.RecycledViewPool recycledViewPool;
    //android viewa
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager layoutManager2;
    ImageButton imageButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_home, container, false);

        //initialize the list
        dataList = new ArrayList<>();
        dataList2=new ArrayList<>();
        // initialize the home adapter passing the list and the context
        adapter = new CategoryAdapter(dataList, this.getContext(), onclickInterface, getFragmentManager());
        imageButton = (ImageButton) v.findViewById(R.id.img_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Profile.class);
                i.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);;
                startActivityForResult(i,0);

//                showPopup(v);
            }
        });


        // defining the layout manager for the recycler view
        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager2 = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        textView= v.findViewById(R.id.playlist_name2);
        //get reference to android views
        recyclerView = v.findViewById(R.id.rv_main);
        recyclerView2 = v.findViewById(R.id.home_recycler_view_horizontal2);
        progressBar = v.findViewById(R.id.pb_home);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        //set up main recvycler view
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView2.setLayoutManager(layoutManager2);
        //Toast.makeText(getContext(),, Toast.LENGTH_LONG).show();

        recyclerView2.setRecycledViewPool(recycledViewPool);
        //recyclerView2.setHasFixedSize(true);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        Call<HomeBodyResponse> responseCall = RetrofitSingleton.getInstance().getApi().getPlaylistsByCategory();

        responseCall.enqueue(new Callback<HomeBodyResponse>() {
            @Override
            public void onResponse(Call<HomeBodyResponse> call, Response<HomeBodyResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    List<Category> categories = response.body().getData().getData();
                    for (Category data : categories) {

                        dataList.add(data);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<HomeBodyResponse> call, Throwable t) {

                progressBar.setVisibility(View.GONE);

            }
        });
        Call<HomeBodyResponse> responseCall2 = RetrofitSingleton.getInstance().getApi().getrecentlyplayed();

        responseCall2.enqueue(new Callback<HomeBodyResponse>() {
            @Override
            public void onResponse(Call<HomeBodyResponse> call, Response<HomeBodyResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    recentlyplayed = response.body().getData().getRecentlyplayed();
                    dataList2= recentlyplayed.getplaylist();
                    adapter.notifyDataSetChanged();
                    textView.setVisibility(View.VISIBLE);
                    adapter2 = new RecentlyPlayedAdapter(getContext(),dataList2);
                    //  LOdataList2.size()
                    recyclerView2.setAdapter(adapter2);
                    // Toast.makeText(getContext(), recentlyplayed.getplaylist().get(1).getName(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<HomeBodyResponse> call, Throwable t) {

                progressBar.setVisibility(View.GONE);

            }
        });
        return v;
    }
}


