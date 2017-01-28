package ie.app.wit.dystech.main;

/**
 * Created by dylan on 23/02/2016.
 */

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;
import ie.app.wit.dystech.models.ReviewMod;
import ie.app.wit.dystech.db.DBManager;


public class ReviewApp extends Application
{
    public ie.app.wit.dystech.db.DBManager dbManager;


    @Override
    public void onCreate()
    {
        super.onCreate(); //when app is started
        Log.v("Review", "Review App Started"); //Confirm in log file start was successful
        dbManager = new ie.app.wit.dystech.db.DBManager(this); //When new db is created on startup
        Log.v("Review", "Database Created"); //Inform in log file that it was sucessful
    }
}
