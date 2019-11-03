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
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener,View.OnKeyListener {

    public void showUserList()
    {
        Intent intent=new Intent(getApplicationContext(),UserListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.loginTextView)
        {
            //Log.i("Login was Pressed","Succesfully");
            signUp=findViewById(R.id.signUpButton);
            if(signupmodeactive)
            {
                signupmodeactive=false;
                signUp.setText("Login");
                loginTextView.setText("or SignUp");

            }
            else {
                signupmodeactive=true;
                signUp.setText("SignUp");
                loginTextView.setText("or Login");
            }
        }
        else if(view.getId()==R.id.backgroundLayout || view.getId()==R.id.logoImageView)
        {
            InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN)
        {
            signUpclicked(view);
        }

        return false;
    }

    Boolean signupmodeactive= true;
    TextView loginTextView;
    EditText usernameEditText;
    EditText passwordEditText;
    Button signUp;
    ConstraintLayout backgroundLayout;
    ImageView logoImageView;
    public void signUpclicked(View view){
        //Log.i("Info:","Sihnup pressed");



        if(usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals(""))
        {
            Toast.makeText(this, "Username or password empty", Toast.LENGTH_SHORT).show();

        }
        else{
            ParseUser user=new ParseUser();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());

            if(signupmodeactive) {
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Signup", "SUCCESSFUL");
                            showUserList();

                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            else{
                user.logInInBackground((usernameEditText.getText().toString()), (passwordEditText.getText().toString()), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user!= null) {
                            Log.i("Login", "SUCCESSFUL");
                            showUserList();

                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    loginTextView=findViewById(R.id.loginTextView);
    loginTextView.setOnClickListener(this);
      usernameEditText=findViewById(R.id.nameEditText);
      passwordEditText=findViewById(R.id.passwordEditText);
      logoImageView=findViewById(R.id.logoImageView);
      backgroundLayout=findViewById(R.id.backgroundLayout);

      logoImageView.setOnClickListener(this);
      backgroundLayout.setOnClickListener(this);

      passwordEditText.setOnKeyListener(this);



    //ParseUser user=new ParseUser();

    if(ParseUser.getCurrentUser()!=null)
    {
        showUserList();
    }
      /*
      //Signup Code
      user.setUsername("Anjali");
      user.setPassword("pass123");
      user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
              if(e==null)
              {
                  System.out.println("Signed Up Successfully");
              }
              else
              {
                  e.printStackTrace();
              }
          }
      });*/

      /*
    //Login Code
    ParseUser.logInInBackground("Anjali", "pass123", new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        if(user!=null)
        {
          System.out.println("Logged in Sucessfully");
        }
        else {
          e.printStackTrace();
        }
      }
    });
    */
      /*logout code
      ParseUser.logOutInBackground(new LogOutCallback() {
        @Override
        public void done(ParseException e) {
          if(e!=null)
          {
            e.printStackTrace();
          }
        }
      });
      */
      /*
      //Checking if a user is currently logged in or not
      if(ParseUser.getCurrentUser()!=null){
        System.out.println("Signed in:"+"Username= "+ ParseUser.getCurrentUser().getUsername());
      }
      else {
        System.out.println("User needs to Login");
      }*/

    /*
    ParseQuery<ParseObject>query =ParseQuery.getQuery("Score");
    query.whereGreaterThan("score",50);
    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
        if(e==null && objects.size()>0)
        {
          for(ParseObject score:objects)
          {
            score.put("score",score.getInt("score")+20);
            score.saveInBackground();
          }
        }
        else{
          e.printStackTrace();
          Log.i("Error:","Parse Exception of some sort");
        }
      }
    });
    */

    /*ParseQuery<ParseObject>query =ParseQuery.getQuery("Score");
    query.whereEqualTo("username","Anjali");
    query.setLimit(1);
    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
        if(e==null)
        {
          if(objects.size()>0)
          {
            for(ParseObject i:objects)
            {
              System.out.println("Username:"+i.getString("username"));
              Log.i("Score:",i.getInt("score")+"");
            }

          }
        }
        else{
          e.printStackTrace();
          Log.i("Error:","Parse Exception of some sort");
        }
      }
    });*/



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
          Log.i("Success:","we saved the score");
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
    });*/


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