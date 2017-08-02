package com.example.ranga.inclass06_rangam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ranga on 2/20/2017.
 */

public class ColorAdapter extends ArrayAdapter<Games> {
    List<Games> mdata;
    Context mcontext;
    int mResouce;
    public ColorAdapter(Context context, int resource, List<Games> objects) {
        super(context, resource,  objects);
        this.mdata=objects;
        this.mResouce = resource;
        this.mcontext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResouce,parent,false);
        }
        Games myGame = mdata.get(position);
        String dob = mdata.get(position).getDate();

        int year = 0;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date d = sdf.parse(dob);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);

            year = (cal.get(Calendar.YEAR));

        } catch (Exception e) {
            e.printStackTrace();
        }


        TextView tvName= (TextView) convertView.findViewById(R.id.adaptertext);
        tvName.setText(myGame.getTitle()+" Released in "+ year+ " Platform: "+myGame.getPlatform()+".");
        ImageView iv= (ImageView) convertView.findViewById(R.id.icon);
        Picasso.with(mcontext).load("http://i.imgur.com/DvpvklR.png").into(iv);
        return convertView;
    }




}
