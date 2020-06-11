package com.example.spotifyclone;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Adapter for Search List
 * @author Salma Hazem
 * @version 1.0
 */

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    /**
     * Declare Variables
     */
    Context mContext;
    LayoutInflater inflater;
    private List<SongNames> songNamesList = null;
    private ArrayList<SongNames> arraylist;

    /**
     * Constructor
     * @param context
     * @param songNamesList
     */

    public ListViewAdapter(Context context, List<SongNames> songNamesList) {
        mContext = context;
        this.songNamesList = songNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<SongNames>();
        this.arraylist.addAll(songNamesList);
    }

    /**
     * View holder class
     */
    public class ViewHolder {
        TextView name;
    }

    /**
     * Getter for count
     * @return
     */
    @Override
    public int getCount() {
        return songNamesList.size();
    }

    /**
     * Getter for Item
     * @param position
     * @return
     */
    @Override
    public SongNames getItem(int position) {
        return songNamesList.get(position);
    }

    /**
     * Getter for ID
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Locate teh text views in the list_item.xml
     * Set the results into TextViews
     * @param position
     * @param view
     * @param parent
     * @return
     */
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(songNamesList.get(position).getSongName());
        holder.name.setTextColor(Color.WHITE);
        return view;
    }

    // Filter Class

    /**
     * Filter Class
     * @param charText
     */
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        songNamesList.clear();
        if (charText.length() == 0) {
            songNamesList.addAll(arraylist);
        } else {
            for (SongNames wp : arraylist) {
                if (wp.getSongName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    songNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}