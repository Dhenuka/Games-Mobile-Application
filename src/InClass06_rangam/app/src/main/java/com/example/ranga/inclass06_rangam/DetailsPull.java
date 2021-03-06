package com.example.ranga.inclass06_rangam;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ranga on 2/20/2017.
 */

public class DetailsPull {
    static public class detailsPullParser{
        static ArrayList<Details> parseDetails(InputStream in) throws XmlPullParserException,IOException {
            Details det=null;
            int flag=0;
            String im = null;
            XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            ArrayList<Details> detList=new ArrayList<>();
            int event=parser.getEventType();
            while(event!=XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:

                        if(parser.getName().equals("baseImgUrl")){
                            det=new Details();
                            im = (parser.nextText().trim());
                            Log.d("demo","entered base"+det);
                        }
                        else if(parser.getName().equals("GameTitle")){
                            det.setTitle(parser.nextText().trim());
                            Log.d("demo","entered title "+det);
                        }
                        else if(parser.getName().equals("Overview")){
                            det.setOverview(parser.nextText().trim());
                            Log.d("demo","entered overview "+det);
                        }
                        else if(parser.getName().equals("genre")){
                            det.setGenre(parser.nextText().trim());
                            Log.d("demo","entered genres "+det);
                        }
                        else if(parser.getName().equals("Publisher")){
                            det.setPub(parser.nextText().trim());
                            Log.d("demo","entered publisher "+det);
                        }
                        else if(parser.getName().equals("Similar")){
                            ArrayList<String> s=new ArrayList<String>();
                            int i=0;

                            while(parser.getName().equals("id")){
                                String id=parser.nextText().trim();

                                Log.d("demo","Similar Tag id"+id);
                                s.add(id);
                                i++;
                            }
                            det.setSim(s);
                            Log.d("demo","sim in pull"+ s);
                        }

                        else if(parser.getName().equals("boxart")){
                            Log.d("demo","entered boxart");
                            if (flag == 0) {
                                String im1 = (parser.nextText().trim());
                                det.setImage(im+im1);
                                Log.d("demo", "value set to: "+im+im1);
                                flag=1;
                            }
                        }
                        else if(flag==0) {
                            if (parser.getName().equals("thumb")) {
                                Log.d("demo","entered thumb");
                                String im1 = (parser.nextText().trim());
                                if(det!=null) {
                                    det.setImage(im + im1);
                                    Log.d("demo", "value thumb: " + im + im1);
                                    flag = 1;
                                }
                            }
                            else if(flag==0) {
                                if(parser.getName().equals("original")) {
                                    Log.d("demo","entered original");
                                    String im1 = (parser.nextText().trim());
                                    Log.d("demo","string im1 in original is "+im1);
                                    if(det!=null) {
                                        det.setImage(im + im1);
                                        Log.d("demo", "value of original: " + im + im1);
                                        flag = 1;
                                    }
                                }
                            }
                            else{
                                Log.d("demo","no image");
                                det.setImage(" ");
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("Game")){
                            detList.add(det);
                            Log.d("demo","entered end tag"+detList);
                          //  det=null;
                        }
                        break;
                    default:
                        break;
                }
                Log.d("demo","end tag "+detList);
                event = parser.next();
            }
            return detList;
        }
    }





}
