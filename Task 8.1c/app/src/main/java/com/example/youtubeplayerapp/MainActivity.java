package com.example.youtubeplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.play_b);
        EditText link = findViewById(R.id.link_ET);

        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String videoUrl = link.getText().toString();

                if (videoUrl.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter video URL", Toast.LENGTH_SHORT).show();
                else {

                    String[] videoIdLong = videoUrl.split("/");

                    String[] videoIdShort = videoIdLong[videoIdLong.length - 1].split("=");

                    Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                    intent.putExtra(Keys.YT_API_KEY, YTConfig.getApi());
                    intent.putExtra(Keys.YT_URL_KEY, videoIdShort[videoIdShort.length - 1]);
                    startActivity(intent);
                }
            }
        });
    }
}