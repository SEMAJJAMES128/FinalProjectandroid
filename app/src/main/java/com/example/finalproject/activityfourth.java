package com.example.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class activityfourth extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private WebView webView;
    private Spinner spinnerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        // Initialize and set up your audio player
        mediaPlayer = MediaPlayer.create(this, R.raw.boucy);

        // Initialize and set up the dropdown menu
        spinnerMenu = findViewById(R.id.spinnerMenu);
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
                        // Handle the default selection
                        break;
                    case 1:
                        startActivity(new Intent(activityfourth.this, ActivitySecond.class));
                        break;
                    case 2:
                        // Show the WebView
                        showWebView("https://www.youtube.com/@FL_STUDIO/videos");
                        break;
                    case 3:
                        startActivity(new Intent(activityfourth.this, activitythird.class));
                        break;
                    case 4:
                        startActivity(new Intent(activityfourth.this, activityfifth.class));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        // Show the WebView by default (change URL as needed)
        showWebView("https://www.youtube.com/@FL_STUDIO/videos");
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

    // Show the WebView with the specified URL
    private void showWebView(String url) {
        webView = findViewById(R.id.webView);
        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        // Handle the back button press for the WebView
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
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
