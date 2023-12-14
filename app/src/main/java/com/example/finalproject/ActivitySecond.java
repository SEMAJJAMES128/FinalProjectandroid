package com.example.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySecond extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private EditText editTextBPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize and set up your audio player
        mediaPlayer = MediaPlayer.create(this, R.raw.schoool);

        // Initialize the EditText for BPM
        editTextBPM = findViewById(R.id.editTextBPM);

        // Initialize and set up the dropdown menu
        Spinner spinnerMenu = findViewById(R.id.spinnerMenu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.page_names,
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
                        startActivity(new Intent(ActivitySecond.this, FirstActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(ActivitySecond.this, activitythird.class));
                        break;
                    case 3:
                        startActivity(new Intent(ActivitySecond.this, activityfourth.class));
                        break;
                    case 4:
                        startActivity(new Intent(ActivitySecond.this, activityfifth.class));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        // Additional initialization code as needed
    }

    // Handle play/pause button click
    public void onPlayPauseClick(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            ((Button) view).setText("Play");
        } else {
            int bpm = Integer.parseInt(editTextBPM.getText().toString());
            adjustPlaybackSpeed(bpm);
            mediaPlayer.start();
            ((Button) view).setText("Pause");
        }
    }

    // Adjust playback speed based on BPM
    private void adjustPlaybackSpeed(int bpm) {
        float speed = calculateSpeedFromBPM(bpm);
        mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
    }

    // Calculate playback speed based on BPM
    private float calculateSpeedFromBPM(int bpm) {
        // Adjust this formula based on your desired speed calculation
        return bpm / 60.0f;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
