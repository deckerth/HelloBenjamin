package com.example.hallopaul;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    public static Context sContext;
    public static MediaPlayer sPlayer = null;
    public static MediaController sController = null;
    public static VideoView sVideoView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sContext = this;

        setContentView(R.layout.activity_main);

        sVideoView = findViewById(R.id.video_player);
        sController = new MediaController(this);
        sController.setAnchorView(sVideoView);
        sVideoView.setMediaController(sController);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.happy_birthday);
        sVideoView.setVideoURI(video);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

 /*
       Implementation for Android 8.0:
       fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of( today.getYear() , Month.AUGUST , 11 );
                String text = "";
                if ( today.isAfter(birthday) ) {
                    birthday = birthday.withYear(birthday.getYear()+1);
                }
                if ( today.equals(birthday) ) {
                    // happy birthday
                    text = "Herzlichen Glückwunsch!";
                    sVideoView.setVisibility(View.VISIBLE);
                    if (sVideoView.isPlaying() ) {
                        sVideoView.stopPlayback();
                    } else {
                        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.happy_birthday);
                        sVideoView.setVideoURI(video);
                        sVideoView.start();
                    }
                } else {
                    Period timePeriod = today.until(birthday);
                    Integer days = timePeriod.getDays();
                    if ( days == 1 ) {
                        text = "Morgen ist es soweit!";
                    } else {
                        text = "Du musst noch " + days.toString() + " Tage warten!";
                    }
                }

                Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GregorianCalendar today = new GregorianCalendar();
                    GregorianCalendar birthday = new GregorianCalendar(today.getWeekYear(),7,24, 23, 59);
                    String text = "";
                    if ( today.after(birthday) ) {
                        birthday = new GregorianCalendar(today.getWeekYear()+1,7,24, 23, 59);
                    }
                    Date todayDate = today.getTime();
                    Date birtdayDate = birthday.getTime();
                    long todayTime = todayDate.getTime();
                    long birthdayTime = birtdayDate.getTime();
                    long diff = birthdayTime - todayTime;
                    // day = 24 * 60 * 60 * 1000 Milliseconds = 86400000 Milliseconds
                    Long days = diff / 86400000;

                    if ( days == 0 ) {
                        // happy birthday
                        text = "Herzlichen Glückwunsch!";
                        sVideoView.setVisibility(View.VISIBLE);
                        if (sVideoView.isPlaying() ) {
                            sVideoView.stopPlayback();
                        } else {
                            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.happy_birthday);
                            sVideoView.setVideoURI(video);
                            sVideoView.start();
                        }
                    } else if (days == 1){
                        text = "Morgen ist es soweit!";
                    } else {
                        text = "Du musst noch " + days.toString() + " Tage warten!";

                    }


                    Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
