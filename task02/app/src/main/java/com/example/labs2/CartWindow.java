package com.example.labs2;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartWindow extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_window);

        // Получение переданных данных из Intent
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String info = getIntent().getStringExtra("info");

        // Нахождение TextView элементов в макете
        TextView nameTextView = findViewById(R.id.LsetName);
        TextView emailTextView = findViewById(R.id.LsetEmail);
        TextView infoTextView = findViewById(R.id.LsetInfo);

        // Установка данных в TextView элементы
        nameTextView.setText(name);
        emailTextView.setText(email);
        if(info == null){
            infoTextView.setText("None");
        }else{
            infoTextView.setText(info);
        }
    }
}
