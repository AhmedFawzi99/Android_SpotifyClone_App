package com.example.spotifyclone;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MusicControlTest extends TestCase {

    private MusicControl music;
    private IOException e;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    void milliSecondsToTIme() {
        String expected ="8:20";
        music=new MusicControl();
        String a= music.milliSecondsToTIme(500000);
        assertEquals(expected,a);
    }

    @After
    public void tearDown() throws Exception {
    }
}