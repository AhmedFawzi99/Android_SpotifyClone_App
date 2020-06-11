package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class customAdapter2 extends RecyclerView.Adapter<customAdapter2.MyViewHolder> {
        Context context;
        ArrayList<Artist> list;
        onClickInterface onClickInterface;
        private static final String TAG = "MyActivity";
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public CircleImageView imageView;
            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.description2);
                imageView=(CircleImageView) view.findViewById(R.id.circleimagechoose);
            }
        }
   public customAdapter2(Context context, ArrayList<Artist> list, onClickInterface onClickInterface) {
            this.context = context;
            this.list = list;
            this.onClickInterface = onClickInterface;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lay, viewGroup, false);
            return new MyViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
            myViewHolder.title.setText(list.get(i).getName());
            Picasso.with(context).
                 load(list.get(i).getImage())
                    .into(myViewHolder.imageView);
            myViewHolder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickInterface.setClick(i);



                }
            });
            myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickInterface.setClick(i);



                }
            });
        }
        @Override
        public int getItemCount() {
            return list.size();
        }
    }

