package com.example.spotifyclone;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * @author shaimaa
 * choose artist page
 */
public class Choose_Artist extends DialogFragment  {
    TextView text;
    ArrayList<Artist> list = new ArrayList<>();
    ArrayList<Artist> selectedlist = new ArrayList<>();
    private int REQUEST_CODE=0;
    private RecyclerView recyclerView;
    private customAdapter2 mAdapter;
    private onClickInterface onclickInterface;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    static Choose_Artist newInstance() {
        return new Choose_Artist();
    }

    public Choose_Artist() {
        // Required empty public constructor
    }


    /**
     * MAIN FUNCTIONS OF DIALOG FRAGMENTS
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
    }

    /**
     * This func sends the  artists picked to the artist fragment page and detects which functions the user choose
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.lay2, container, false);
        CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.circleimagechoose);
        TextView textView =(TextView) view.findViewById(R.id.textView11) ;
        textView.setOnClickListener(new OnClickListener(){@Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("size",selectedlist.size());
            for(int i=0 ; i<selectedlist.size();i++) {
                intent.putExtra("Name"+i, selectedlist.get(i).getName());
                intent.putExtra("image"+i, selectedlist.get(i).getImage());
                intent.putExtra("id"+i, selectedlist.get(i).getID());
                intent.putExtra("follow"+i,selectedlist.get(i).isFollowing());
            }

            if(getTargetFragment()!=null){
                getTargetFragment().onActivityResult(
                        getTargetRequestCode(), REQUEST_CODE, intent);
                dismiss();}

        }});
        // Inflate the layout for this fragment
        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                // list.remove(abc);
                // Toast.makeText(getContext(),"Position is"+abc, Toast.LENGTH_LONG).show();
                mAdapter.notifyDataSetChanged();
                selectedlist.add(list.get(abc));
            }
        };
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_main);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        Artist artist =new Artist("1170522", "Amr Diab", "https://celebborn.com/celeb_image/amr-diab-26592.jpg?fbclid=IwAR1NnxgzRJFNahdrocTRjv1yP2OwMgnZ_Ox5J1wuVZOJN9TPOop3RwTJCZ0",true);
        Artist artist2 =new Artist("1170523", "Tamer Hosny", "https://p16-va-default.akamaized.net/img/musically-maliva-obj/1654423082333189~c5_720x720.jpeg",true);
        Artist artist3 =new Artist("1170524", " Hamza Namira", "https://www.namirahamza.com/wp-content/uploads/2019/03/479.jpeg",true);
        Artist artist4 =new Artist("1170524", " Nancy Ajram", "https://i1.wp.com/www.eg24.news/wp-content/uploads/2020/03/6534651-2015959685.jpg?fit=1000%2C678&ssl=1",true);
        Artist artist1= new Artist("1170524", " Moahmed Foad", "https://nogomistars.com/wallpaper/untitled8.jpg?fbclid=IwAR3Bl2ApUkKDd8VKUaC4sMDRAPZWAO_J-59G6r7y0NYj0OY9bGgvz52JiBo",true);
        Artist artist6= new Artist("1170524", " Elisa", "https://imagevars.gulfnews.com/2018/8/7/1_16a084fe2d8.2263028_1984710504_16a084fe2d8_medium.jpg",true);
        Artist artist5= new Artist("1170524", " Om Kalthom", "https://egyptianstreets.com/wp-content/uploads/2016/02/maxresdefault.jpg",true);


        list.add(artist);
        list.add(artist1);
        list.add(artist2);
        list.add(artist3);
        list.add(artist4);
        list.add(artist6);
        list.add(artist5);


        mAdapter = new customAdapter2(getContext(), list, onclickInterface);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(2));

        return view;

    }

}