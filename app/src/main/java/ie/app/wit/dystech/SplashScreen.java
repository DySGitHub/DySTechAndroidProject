package ie.app.wit.dystech;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ie.app.wit.dystech.activities.AddReviewActivity;
import ie.app.wit.dystech.activities.Core;
import ie.app.wit.dystech.activities.MainActivity;
import ie.app.wit.dystech.activities.MainMenuActivity;
import ie.app.wit.dystech.main.ReviewApp;

/**
 * Created by dylan on 04/02/2016.
 */
public class SplashScreen extends Core {
    int SSTime = 3000; //Time the SS shows up for


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler SSHandle = new Handler();


        SSHandle.postDelayed(new Runnable() //Tell the method run to be delayed......
        {

            @Override
            public void run() {
                finish();
                if (!BBP) {
                    if (app.dbManager.getAll().isEmpty()) //if db is empty
                    {
                        Intent ss2ar = new Intent(SplashScreen.this, AddReviewActivity.class); //Tell app to change from SS to Add Review
                        SplashScreen.this.startActivity(ss2ar); //Run the intent
                    } else {
                        Intent ss2mm = new Intent(SplashScreen.this, MainMenuActivity.class); //Tell app to change from SS to Main Menu
                        SplashScreen.this.startActivity(ss2mm); //Run the intent
                    }
                }
            }

        }, SSTime);//....By the amount of time set previously
    }

    }

