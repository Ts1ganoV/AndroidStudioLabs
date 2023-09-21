package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    Switch switcher;
    String[] item = {"2","4"};
    String[] itemIcon = {"animal", "robot"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    AutoCompleteTextView autoCompleteIconView;
    ArrayAdapter<String> adapterIcon;
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

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                PoleSize.getInstance().setSize(item);
            }
        });

        autoCompleteIconView = findViewById(R.id.autoIcon);
        adapterIcon = new ArrayAdapter<String>(this, R.layout.list_item, itemIcon);
        autoCompleteIconView.setAdapter(adapterIcon);
        autoCompleteIconView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                IconClass.getInstance().setIconPrefix(item);
            }
        });

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