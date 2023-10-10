package com.example.labs2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> email = new ArrayList<>();
    ArrayList<String> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset("users.json"));
            JSONArray jsonArray = jsonObject.getJSONArray("users");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userData = jsonArray.getJSONObject(i);
                name.add(userData.getString("name"));
                email.add(userData.getString("email"));
                info.add(userData.getString("info"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HelperAdapter helperAdapter = new HelperAdapter(name, email, info, ListActivity.this);

        // Установка слушателя нажатия на элемент RecyclerView
        helperAdapter.setOnItemClickListener(new HelperAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, String email,String info) {
                // Здесь вы можете выполнить действия с переданными данными, например, запустить новую активность и передать данные в нее
                Intent intent = new Intent(ListActivity.this, CartWindow.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("info", info);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(helperAdapter);
    }

    private String JsonDataFromAsset(String fileName) {
        String json = null;
        try {
            InputStream inputStream = getAssets().open(fileName);
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
