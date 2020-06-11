package com.example.spotifyclone;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<Login>(Login.class);

    private Login login = null;
    @Before
    public void setUp() throws Exception {
        login = loginActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = login.findViewById(R.id.etUsername);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        login=null;
    }
}