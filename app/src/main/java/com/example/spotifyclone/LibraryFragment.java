package com.example.spotifyclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class LibraryFragment extends Fragment {

    @Nullable
    /**
     * the Toolbar that contains (Music)
     */
    Toolbar toolbar;
    /**
     * the TabLayout that contains (Playlist, Artists, Album)
     */
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View s;

        s=inflater.inflate(R.layout.fragment_library,container,false);

        tabLayout=(TabLayout)s.findViewById(R.id.tablayout);
        viewPager=(ViewPager)s.findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        /**
         * adding fragments and their titles
         */
        viewPagerAdapter.addfragments(new PlaylistFragment(),"Playlist");
        viewPagerAdapter.addfragments(new ArtistFragment(),"Artist");
        viewPagerAdapter.addfragments(new AlbumFragment(),"Album");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return s ;

    }


}
