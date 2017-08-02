package com.example.ranga.inclass06_rangam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SimGames extends AppCompatActivity implements getAsyncTask.IData {

    ListView ll;
    String a,b,w;
    ArrayList<Games> c=new ArrayList<Games>();
    ArrayList<String> sm=new ArrayList<String>();
    Button finish1;
    Games currentSimilarGame;
    String id1;
    final static String key="Id";
    final static String key1="Name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_games);
        finish1 = (Button) findViewById(R.id.finishSim);
        sm=null;
        if(getIntent().getExtras()!=null){
            a=(String) getIntent().getExtras().getSerializable(GameDetails.key2);
            b=(String) getIntent().getExtras().getSerializable(GameDetails.key3);
            w=(String) getIntent().getExtras().getSerializable(GameDetails.key4);
            sm=(ArrayList<String>) getIntent().getExtras().getSerializable(GameDetails.key5);
        }
        ll=(ListView) findViewById(R.id.listViewSimGamesID);
        TextView tv=(TextView) findViewById(R.id.titleSimGamesID);
       /* ll.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        finish1.setVisibility(View.INVISIBLE);
*/
       finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tv.setText("Similar games to "+b);
        String d="http://thegamesdb.net/api/GetGamesList.php?name="+a;
        Log.d("demo","sim games: "+sm);
        if(sm==null) {
            /*TextView t=new TextView(this);
            t.setText("No similar games");
            ll.addView(t);*/
            Toast.makeText(this,"No similar games",Toast.LENGTH_LONG).show();
        }
        else{
            new getAsyncTask(SimGames.this).execute(d);
        }





    }

    @Override
    public void setList(final ArrayList<Games> games) {
        c=games;
        Log.d("demo","c is"+ c);
        Log.d("demo","sim games size" +sm.size());

        final ArrayList<Games> similarGames = new ArrayList<Games>();
        for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < sm.size(); j++) {
                if (c.get(i).getId().equals(sm.get(j))) {
                    Log.d("demo","Similar Games");
                    similarGames.add(c.get(i));
                   /* Log.d("demo", "id in list " + c.get(i).getId() + " , sim Id is " + sm.get(j));
                    TextView t = new TextView(this);
                    t.setText(c.get(i).getTitle() + ", Released in " + c.get(i).getDate() + ", Platform: " + c.get(i).getPlatform() + ".");
                    ll.addView(t);*/
                }
            }
        }



        ArrayAdapter<Games> adapter = new ArrayAdapter<Games>(this,android.R.layout.simple_list_item_1,android.R.id.text1,similarGames);

        ll.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        //adapter.remove(colors.get(0));

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo","clicked"+position + "value"+similarGames.get(position));
                currentSimilarGame = similarGames.get(position);
                id1= currentSimilarGame.getId();
                Intent intent = new Intent(SimGames.this,GameDetails.class);
                intent.putExtra(key,id1);
                //intent.putExtra(key1,sea.getText().toString());
                startActivity(intent);

            }
        });
    }
}
