package com.example.spotifyclone;

import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
class MusicActivityTest {
    @Rule
    public ActivityTestRule<MusicActivity> musicActivityActivityTestRule=new ActivityTestRule<MusicActivity>(MusicActivity.class);

    public MusicActivity musicActivity=null;

    @Test
    void onBackPressed() {
    }

    @Test
    void setMusicPlayerComponents() {
        TextView songname=musicActivity.findViewById(R.id.song_name);
        String exSonngName="Jazz in Paris";
        musicActivity.mockgetsong();
        assertEquals(exSonngName,songname.getText());



    }
}