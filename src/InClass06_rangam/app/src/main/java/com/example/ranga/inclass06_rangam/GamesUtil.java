package com.example.ranga.inclass06_rangam;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ranga on 2/20/2017.
 */

public class GamesUtil {
    static public class GamesPullParser {

        static ArrayList<Games> parseGames(InputStream in) throws Exception {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            Games game = null;
            ArrayList<Games> gamesList = new ArrayList<Games>();
            int event = parser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:

                        if (parser.getName().equals("Game")) {
                            game = new Games();
                        } else if (parser.getName().equals("id")) {
                            game.setId(parser.nextText().trim());
                        } else if (parser.getName().equals("GameTitle")) {
                            game.setTitle(parser.nextText().trim());
                        } else if (parser.getName().equals("ReleaseDate")) {
                            game.setDate(parser.nextText().trim());
                        } else if (parser.getName().equals("Platform")) {
                            game.setPlatform(parser.nextText().trim());
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("Game")) {
                         /*   if(gamesList.size()<10) {*/
                                gamesList.add(game);
                                game = null;
                            /*}else
                            {
                                return gamesList;
                            }*/
                        }
                        break;
                    default:
                        break;
                }
                event = parser.next();
            }
            return gamesList;
        }
    }
}

