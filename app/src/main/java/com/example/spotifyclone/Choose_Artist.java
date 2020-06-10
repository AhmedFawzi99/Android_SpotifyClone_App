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



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
        }

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
             //   Toast.makeText(getContext(),selectedlist.get(0).getName(), Toast.LENGTH_LONG).show();
            dismiss();}

        }});
        //Picasso.with(getApplicationContext()).load("https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff").into(circleImageView);;
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


        Artist artist =new Artist("1170522", "Amr Diab", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff",true);
        Artist artist2 =new Artist("1170523", "Tamer Hosny", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff",true);
        Artist artist3 =new Artist("1170524", " Hamza Namira", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff",true);


        list.add(artist);
        list.add(artist2);
        list.add(artist2);
        list.add(artist2);
        list.add(artist2);
        list.add(artist3);
        list.add(artist3);
        list.add(artist3);
        list.add(artist3);
        list.add(artist);
        list.add(artist);
        list.add(artist2);
        list.add(artist3);
        list.add(artist2);


        mAdapter = new customAdapter2(getContext(), list, onclickInterface);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(2));

        return view;

    }
   /*public void onClick(View view) {
       {
           int id = view.getId();
           switch (id) {
               case R.id.cancel:
                   dismiss();
                   break;
               case R.id.skip:
                   dismiss();
                   break;
           }
       }
   }*/
}
