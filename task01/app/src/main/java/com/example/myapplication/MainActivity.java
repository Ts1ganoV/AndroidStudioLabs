package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button startGame = (Button) findViewById(R.id.startGame);
        Button infoGame = (Button) findViewById(R.id.infoGame);
        Button settingsGame = (Button) findViewById(R.id.settingsGame);

        View.OnClickListener OnClickStartGame = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //сущность
                Intent intent = new Intent(MainActivity.this, StartGame.class);
                startActivity(intent);
            }
        };
        View.OnClickListener OnClickInfoGame = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //сущность
                Intent intent = new Intent(MainActivity.this, InfoGame.class);
                startActivity(intent);
            }
        };
        View.OnClickListener OnClickSettingsGame = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //сущность
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        };

        startGame.setOnClickListener(OnClickStartGame);
        infoGame.setOnClickListener(OnClickInfoGame);
        settingsGame.setOnClickListener(OnClickSettingsGame);
        
    }
}