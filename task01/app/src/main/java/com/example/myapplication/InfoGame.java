package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_game);

        Button returnMainMenu = (Button) findViewById(R.id.returnMainMenuBut);
        View.OnClickListener OnClickStartGame = new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //сущность
                Intent intent = new Intent(InfoGame.this, MainActivity.class);
                startActivity(intent);
            }
        };
        returnMainMenu.setOnClickListener(OnClickStartGame);


    }
}