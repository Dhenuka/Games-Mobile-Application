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

public class DetailsAsync extends AsyncTask<String,Void,ArrayList<Details>> {
    Data activ;

    public DetailsAsync(Data activ) {
        this.activ = activ;
    }


    @Override
    protected ArrayList<Details> doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                Log.d("demo", "entered in "+in.toString());
                return DetailsPull.detailsPullParser.parseDetails(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Details> detailses) {
        super.onPostExecute(detailses);
        if(detailses!= null){
            activ.over(detailses);
        }
        /*else{
            activ.over(null);
        }*/
    }

    public interface Data{
        public void over(ArrayList<Details> details);
    }



}
