/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ParseQuery<ParseObject>query =ParseQuery.getQuery("Score");
    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
        if(e==null)
        {
          if(objects.size()>0)
          {
            for(ParseObject i:objects)
            {
              Log.i("Username:",i.getString("username"));
              Log.i("Score:",i.getInt("score")+"");
            }

          }
        }
        else{
          Log.i("Eroor:","Parse Exception of some sort");
        }
      }
    });









    /*
    ParseObject score=new ParseObject("Score");
    score.put("username","Anjali");
    score.put("score",65);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if(e==null)
        {
          //ok
          Log.i("Success:","we saved the scotre");
        }
        else{
          e.printStackTrace();
        }
      }
    });

    ParseQuery<ParseObject> query=ParseQuery.getQuery("Score");
    query.getInBackground("eNx7p8u16k", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e==null && object!=null){
            object.put("score",95);
            object.saveInBackground();
          Log.i("Username:",object.getString("username"));
          Log.i("Score:",Integer.toString(object.getInt("score")));

        }
      }
    });
    */

    /*ParseObject tweet=new ParseObject("Tweet");
    tweet.put("username","alamfaiz71");
    tweet.put("tweet","GDG DevFest Kolkata was Fab!");
    tweet.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if(e== null){
          Log.i("Tweeted:","successfully");
        }
        else {
          Log.i("Tweeted:","Unsuccessfully");
          e.printStackTrace();
        }
      }
    });
    ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("Tweet");
    query.getInBackground("qUjKJ1c2eJ", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e==null && object!=null)
        {
          object.put("tweet","GDG DevFest Kolkata was Fabulous");
          object.saveInBackground();
          Log.i("Username:",object.getString("username"));
          Log.i("Tweet:",object.getString("tweet"));
        }
        else {
          e.printStackTrace();
        }
      }
    });
    */


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}