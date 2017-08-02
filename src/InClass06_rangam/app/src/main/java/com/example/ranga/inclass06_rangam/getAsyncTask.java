package com.example.ranga.inclass06_rangam;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ranga on 2/20/2017.
 */

public class getAsyncTask extends AsyncTask<String,Void,ArrayList<Games>> {
    IData activity;
    public getAsyncTask(IData activity) {

        this.activity = activity;
    }




    @Override
    protected ArrayList<Games> doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                Log.d("demo",in.toString());
                return GamesUtil.GamesPullParser.parseGames(in);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPostExecute(ArrayList<Games> games) {
      if(games!=null)
      {
          activity.setList(games);
      }
    }

    public interface IData{
        public void setList(ArrayList<Games> games);
    }


}
