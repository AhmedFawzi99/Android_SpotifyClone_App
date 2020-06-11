package com.example.spotifyclone;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author shaimaa
 * this class makes the view pager in which the user can navigates between fragments(playlist - artist - albums)
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> tabTitles =new ArrayList<>();
    public void addfragments (Fragment fragment, String title )
    {
        this.fragments.add(fragment);
        this.tabTitles.add(title);
    }
    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public CharSequence getPageTitle(int pos)
    {
        return tabTitles.get(pos);
    }
}