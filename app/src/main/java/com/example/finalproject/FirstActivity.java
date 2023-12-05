// FirstActivity.java
package com.example.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // Initialize and set up your audio player
        mediaPlayer = MediaPlayer.create(this, R.raw.boucy); // Place an audio file in the 'res/raw' folder

        // Initialize and set up the dropdown menu
        Spinner spinnerMenu = findViewById(R.id.spinnerMenu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.page_names, // Create an array resource in 'res/values/arrays.xml'
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMenu.setAdapter(adapter);

        // Handle dropdown menu item selection
        spinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Open the selected page when a menu item is selected
                switch (position) {
                    case 0:
                        // Do nothing (the default selection)
                        break;
                    case 1:
                        startActivity(new Intent(FirstActivity.this, ActivitySecond.class));
                        break;
                    case 2:
                        startActivity(new Intent(FirstActivity.this, activitythird.class));
                        break;
                    case 3:
                        startActivity(new Intent(FirstActivity.this, activityfourth.class));
                        break;
                    case 4:
                        startActivity(new Intent(FirstActivity.this, activityfifth.class));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }

    // Handle play audio button click
    public void onPlayAudioClick(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            ((Button) view).setText("Play Audio");
        } else {
            mediaPlayer.start();
            ((Button) view).setText("Pause Audio");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
