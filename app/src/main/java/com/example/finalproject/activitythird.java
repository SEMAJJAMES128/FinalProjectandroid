package com.example.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activitythird extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private GridView gridView;
    private Button playAudioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Initialize and set up your audio player
        mediaPlayer = MediaPlayer.create(this, R.raw.boucy); // Default audio file

        // Initialize GridView
        gridView = findViewById(R.id.gridView);

        // Set up the grid view with three images and corresponding audio files
        int[] imageIds = {R.drawable.keyscape, R.drawable.sunburst, R.drawable.omni};
        int[] audioIds = {R.raw.wavepiano, R.raw.waveguitar, R.raw.wave};
        ImageAdapter imageAdapter = new ImageAdapter(this, imageIds, audioIds);
        gridView.setAdapter(imageAdapter);

        // Initialize play audio button
        playAudioButton = findViewById(R.id.playAudioButton);

        // Handle grid item click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Display a custom toast message
                Toast.makeText(activitythird.this, "Selected Image: " + (position + 1), Toast.LENGTH_SHORT).show();

                // Set the audio file based on the selected image
                int audioResourceId = imageAdapter.getAudioResourceId(position);
                mediaPlayer = MediaPlayer.create(activitythird.this, audioResourceId);

                // Enable play audio button
                playAudioButton.setEnabled(true);
            }
        });

        // Handle play audio button click
        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playAudioButton.setText("Play Audio");
                } else {
                    mediaPlayer.start();
                    playAudioButton.setText("Pause Audio");
                }
            }
        });

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
                        startActivity(new Intent(activitythird.this, FirstActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(activitythird.this, ActivitySecond.class));
                        break;
                    case 3:
                        startActivity(new Intent(activitythird.this, activityfourth.class));
                        break;
                    case 4:
                        startActivity(new Intent(activitythird.this, activityfifth.class));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
