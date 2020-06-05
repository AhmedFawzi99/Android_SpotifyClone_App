package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * Created by shaimaa
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.HomeViewHolder> {
    onClickInterface onClickInterface2;
    onClickInterface onClickInterface;
    private Context context;
    private List<Category> data;
    private Player_Adapter_two horizontalAdapter;
    private RecyclerView.RecycledViewPool recycledViewPool;
    FragmentManager fragmentmanager;


    public CategoryAdapter(List<Category> data, Context context, onClickInterface onClickInterface, FragmentManager fragmentmanager) {
        this.data = data;
        this.context = context;
        this.onClickInterface = onClickInterface;
        this.fragmentmanager=fragmentmanager;
        recycledViewPool = new RecyclerView.RecycledViewPool();

    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View theView = LayoutInflater.from(context).inflate(R.layout.horizontal_home, parent, false);
        return new HomeViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, final int position) {
        onClickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                // list.remove(abc);

                 //Toast.makeText(context,"Position is"+abc, Toast.LENGTH_LONG).show();

                Type BLOCK= data.get(position).getBlocks().get(abc);
                Toast.makeText(context,BLOCK.getType(), Toast.LENGTH_LONG).show();
               if(BLOCK.getType().equals("playlist")) {
                    String image = BLOCK.rowitem.getImage();
                    String name = BLOCK.rowitem.getName();
                    String id=BLOCK.rowitem.getId();
                    Fragment selectedFragment=new EachPlaylist(name,image,id);
                    fragmentmanager.beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                }
                else if(BLOCK.getType().equals("album")) {
                    String image = BLOCK.getAlbum().getImage_id();
                    String name = BLOCK.getAlbum().getAlbum_name();
                    String id=BLOCK.getAlbum().getID();
                    Fragment selectedFragment=new Each_album(name,image,id);
                    fragmentmanager.beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                }

                // if(dataList.get(abc).)
                // mAdapter.notifyDataSetChanged();
                // selectedlist.add(list.get(abc));
            }
        };
        holder.textViewCategory.setText(data.get(position).getName());
        horizontalAdapter = new Player_Adapter_two(context, data.get(position).getBlocks(),onClickInterface);
        holder.recyclerViewHorizontal.setAdapter(horizontalAdapter);

        holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool);
/*holder.textViewCategory.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onClickInterface2.setClick(position);
    }
});*/

    }


    @Override
    public int getItemCount() {
        return data.size();

    }


    public class HomeViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerViewHorizontal;
        private TextView textViewCategory;
        private RelativeLayout relativeLayout;
        private LinearLayoutManager horizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        public HomeViewHolder(View itemView) {
            super(itemView);

            recyclerViewHorizontal = itemView.findViewById(R.id.home_recycler_view_horizontal);
            recyclerViewHorizontal.setHasFixedSize(true);
            recyclerViewHorizontal.setNestedScrollingEnabled(false);
            recyclerViewHorizontal.setLayoutManager(horizontalManager);
            recyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());
            relativeLayout=itemView.findViewById(R.id.relativeLayout);
            textViewCategory = itemView.findViewById(R.id.playlist_name2);


        }


    }


}