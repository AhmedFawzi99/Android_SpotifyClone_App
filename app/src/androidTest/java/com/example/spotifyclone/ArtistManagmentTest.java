package com.example.spotifyclone;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArtistManagmentTest {

    @Rule
    public ActivityTestRule<ArtistManagment> artistManagmentActivityTestRule = new ActivityTestRule<ArtistManagment>(ArtistManagment.class);

    private ArtistManagment artistManagment = null;
    @Before
    public void setUp() throws Exception {
        artistManagment = artistManagmentActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = artistManagment.findViewById(R.id.imageButton2);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        artistManagment=null;
    }
}