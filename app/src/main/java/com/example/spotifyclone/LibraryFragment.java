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

/**
 * library fragment that contains the view pager (artist- playlist- album)
 * Helping Source: <a href="https://abhiandroid.com/materialdesign/viewpager">https://abhiandroid.com/materialdesign/viewpager</a> <br>
 */
public class LibraryFragment extends Fragment {
    /**
     * library fragment that contains the view pager (artist- playlist- album)
     */
    @Nullable
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    /**
     * on creating the fragment this function is called
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View s;

        s=inflater.inflate(R.layout.fragment_library,container,false);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        tabLayout=(TabLayout)s.findViewById(R.id.tablayout);
        viewPager=(ViewPager)s.findViewById(R.id.viewPager);

        viewPagerAdapter.addfragments(new PlaylistFragment(),"Playlist");
        viewPagerAdapter.addfragments(new ArtistFragment(),"Artist");
        viewPagerAdapter.addfragments(new AlbumFragment(),"Album");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return s ;

    }


}