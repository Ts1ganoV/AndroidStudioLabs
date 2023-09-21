package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class StartGame extends AppCompatActivity {
    private Chronometer mChronometer;
    private GridView mGrid;
    private GridAdapter mAdapter;

    int GRID_SIZE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        mChronometer =findViewById(R.id.playTime);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();

        GRID_SIZE = PoleSize.getInstance().getIntSize();

        mGrid = (GridView)findViewById(R.id.gridView);
        mGrid.setNumColumns(GRID_SIZE);
        mGrid.setEnabled(true);

        mAdapter = new GridAdapter(this, GRID_SIZE, GRID_SIZE);
        mGrid.setAdapter(mAdapter);

        mGrid.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                mAdapter.checkOpenCells ();
                mAdapter.openCell (position);

                if (mAdapter.checkGameOver()) {
                    int c = mAdapter.getInvoice();
                    Toast.makeText(getApplicationContext(), "Cчет: " + c, Toast.LENGTH_SHORT).show();
                    mChronometer.stop();
                    mAdapter.setInvoice(0);
                }
            }
        });

        String playerName = DataManager.getInstance().getPlayerName();
        if (playerName != null) {
            TextView playerNameTextView = findViewById(R.id.playerName);
            playerNameTextView.setText(playerName);
        }else{
            TextView playerNameTextView = findViewById(R.id.playerName);
            playerNameTextView.setText("NoName");
        }


    }

    public void returnMainMenu(View view){
        Button returnMainMenu = (Button) findViewById(R.id.returnMainMenuBut);
        View.OnClickListener OnClickStartGame = new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //сущность
                Intent intent = new Intent(StartGame.this, MainActivity.class);
                startActivity(intent);
            }
        };
        returnMainMenu.setOnClickListener(OnClickStartGame);
    }
    public void restartGame(View view){
        Button restartGame = (Button) findViewById(R.id.restartGameBut);
        GridView gridView = findViewById(R.id.gridView);
        View.OnClickListener OnClickRestartGame = new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.start();
                mAdapter.resetGame();
                mAdapter.notifyDataSetChanged();
            }
        };
        restartGame.setOnClickListener(OnClickRestartGame);
    }
}