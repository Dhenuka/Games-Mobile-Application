package com.example.ranga.inclass06_rangam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GameDetails extends AppCompatActivity  implements DetailsAsync.Data{

    String g,z;
    Button s,f;
    ImageView iv;
    TextView tv,ov;
    TextView gn,pb;
    ProgressBar pg;
    ArrayList<Details> dt=new ArrayList<Details>();
    final static String key2="Name";
    final static String key3="Title";
    final static String key4="Id";
    final static String key5="similar";
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        if(getIntent().getExtras()!=null){
            if(getIntent().getExtras().containsKey(MainActivity.key)) {
                g = (String) getIntent().getExtras().getSerializable(MainActivity.key);
            }
            if(getIntent().getExtras().containsKey(MainActivity.key1)) {
                z = (String) getIntent().getExtras().getSerializable(MainActivity.key1);
            }else if(!(getIntent().getExtras().containsKey(MainActivity.key1)))
            {
                s.setEnabled(false);
            }
        }
        Log.d("demo","http://thegamesdb.net/api/GetGame.php?id="+g);
        String u="http://thegamesdb.net/api/GetGame.php?id="+g;
        tv=(TextView) findViewById(R.id.titleID);
        ov=(TextView) findViewById(R.id.overText);
        gn= (TextView) findViewById(R.id.genre_val_ID);
        pb= (TextView) findViewById(R.id.publ_val_ID);


        s=(Button) findViewById(R.id.similarID);
        f=(Button) findViewById(R.id.finishID);
        iv=(ImageView) findViewById(R.id.ivDetailsID);
        pg= (ProgressBar) findViewById(R.id.progressBarDetailsID);
        pg.setVisibility(View.VISIBLE);
       /* s.setVisibility(View.INVISIBLE);
        f.setVisibility(View.INVISIBLE);
        iv.setVisibility(View.INVISIBLE);
        ov.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        gn.setVisibility(View.INVISIBLE);
        pb.setVisibility(View.INVISIBLE);*/
        new DetailsAsync(GameDetails.this).execute(u);


        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.isEnabled()) {
                    Intent i = new Intent(GameDetails.this, SimGames.class);
                    i.putExtra(key2, z);
                    i.putExtra(key3, dt.get(0).getTitle());
                    i.putExtra(key4, g);
                    i.putExtra(key5, dt.get(0).getSim());
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public void over(ArrayList<Details> details) {
        Log.d("demo","Entered Array List");
        dt=details;
        pg.setVisibility(View.INVISIBLE);
        Log.d("demo","values in "+dt);

        tv.setText(dt.get(0).getTitle());
        ov.setText(dt.get(0).getOverview());
        gn.setText(dt.get(0).getGenre());
        pb.setText(dt.get(0).getPub());
        if(dt.get(0).getImage()==null){
            Toast.makeText(this,"no image for this",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"loading image ",Toast.LENGTH_LONG).show();
            Picasso.with(this)
                    .load(dt.get(0).getImage())
                    .into(iv);
        }
    }
}
