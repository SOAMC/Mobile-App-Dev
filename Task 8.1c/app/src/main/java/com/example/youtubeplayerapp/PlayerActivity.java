package com.example.youtubeplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        YouTubePlayerView mYouTubePlayerView = findViewById(R.id.youtubeView);
        Intent intent = getIntent();
        String apiKey = intent.getStringExtra(Keys.YT_API_KEY);
        videoId = intent.getStringExtra(Keys.YT_URL_KEY);
        mYouTubePlayerView.initialize(apiKey, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(videoId);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(PlayerActivity.this, "Failed to initialise video", Toast.LENGTH_SHORT).show();
        finish();
    }
}