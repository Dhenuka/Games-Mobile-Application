package com.example.ranga.inclass06_rangam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements getAsyncTask.IData {

    EditText searchText;
    Button searchBtn;
    ListView lv;
    ProgressBar pg;
    ArrayList<Games> gamesArrayList;
    ColorAdapter adapter;
    Games currentGame;
    ArrayList<Games> gameArrayList10;
    String id1;
    final static String key="Id";
    final static String key1="Name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = (EditText) findViewById(R.id.searchEditTxtID);
        searchBtn = (Button) findViewById(R.id.searchID);
        lv = (ListView) findViewById(R.id.listViewID);
        pg = (ProgressBar) findViewById(R.id.progressMainId);
        pg.setVisibility(View.INVISIBLE);
        String url = "http://wiki.thegamesdb.net/index.php/GetGamesList";
        gameArrayList10 = new ArrayList<Games>();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchText.getText().length() != 0) {
                    String u="http://thegamesdb.net/api/GetGamesList.php?name="+searchText.getText().toString();
                    new getAsyncTask(MainActivity.this).execute(u);
                    pg.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "No keyword entered", Toast.LENGTH_LONG).show();
                }
            }
        });





    }

    public void setList(final ArrayList<Games> games) {
        gamesArrayList = games;
      /*  for(int i =0;i<10;i++)
        {
            gameArrayList10.add(games.get(i));
        }*/
        pg.setVisibility(View.INVISIBLE);
        adapter = new ColorAdapter(this,R.layout.row_item_layout,gamesArrayList);

        lv.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        //adapter.remove(colors.get(0));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo","clicked"+position + "value"+gamesArrayList.get(position));
                currentGame = gamesArrayList.get(position);
                id1= currentGame.getId();
                Intent intent = new Intent(MainActivity.this,GameDetails.class);
                intent.putExtra(key,id1);
                intent.putExtra(key1,searchText.getText().toString());
                startActivity(intent);

            }
        });


    }

}
