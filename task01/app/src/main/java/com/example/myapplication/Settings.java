package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {
    Switch switcher;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView tekNik = findViewById(R.id.tekNik);
        String playerName = DataManager.getInstance().getPlayerName();
        tekNik.setText(playerName);

        switcher = findViewById(R.id.switcher);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);
        if(nightMode){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switcher.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });

    }

    public void returnMainMenuFunct(View view){
        Button returnMainMenu = (Button) findViewById(R.id.returnMainMenuBut);
        View.OnClickListener onClickListener = new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //сущность
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
            }
        };
        returnMainMenu.setOnClickListener(onClickListener);
    }

    public void clickSaveButton(View view) {
        EditText loginText = findViewById(R.id.inputPlayerName);
        String login = loginText.getText().toString();
        DataManager.getInstance().setPlayerName(login);

        TextView tekNik = findViewById(R.id.tekNik);
        tekNik.setText(login);
    }

}