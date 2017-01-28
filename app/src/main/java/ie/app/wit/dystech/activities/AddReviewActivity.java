package ie.app.wit.dystech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ie.app.wit.dystech.R;
import ie.app.wit.dystech.db.DBDesigner;
import ie.app.wit.dystech.models.ReviewMod;

/**
 * Created by dylan on 07/02/2016.
 */
public class AddReviewActivity extends Core
{ //Extends the Base class called Core

    private NumberPicker rDate;
    private NumberPicker rMonth;
    private NumberPicker rYear;
    private EditText rHead;
    private EditText rMain;
    private EditText rAuth;
    private RatingBar rBar2;
    private Button SR;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreview);
        rDate  = (NumberPicker) findViewById(R.id.rDate);
        rMonth  = (NumberPicker) findViewById(R.id.rMonth);
        rYear  = (NumberPicker) findViewById(R.id.rYear);
        rHead  = (EditText) findViewById(R.id.ReviewHeading);
        rMain    = (EditText) findViewById(R.id.ReviewMainText);
        rAuth    = (EditText) findViewById(R.id.ReviewAuthor);
        rBar2    = (RatingBar) findViewById(R.id.ratingBar2);
        rBar2.setStepSize(1);
        rBar2.setRating(1);
        SR = (Button) findViewById(R.id.ReviewSubmitButton);


        rDate.setMinValue(1); //sets min as 1 in numberpicker
        rMonth.setMinValue(1);
        rYear.setMinValue(2016);

        rDate.setMaxValue(31); //sets max as 31 in numberpicker
        rMonth.setMaxValue(12);
        rYear.setMaxValue(2016);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuHome: startActivity (new Intent(this, MainMenuActivity.class));
                break;

            case R.id.menuList: startActivity (new Intent(this, MainActivity.class));
                break;

            case R.id.menuInfo: startActivity (new Intent(this, AppInfoActivity.class));
                break;

            case R.id.menuAddArticle: startActivity (new Intent(this, AddReviewActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void SRPressed(View view)
    {
        app.dbManager.open();
        int rDay = rDate.getValue(); //gets value inputted in numpicker and assigns it to int rDay
        int rMonth1 = rMonth.getValue();
        int rYear1 = rYear.getValue();
        String rHead1 = rHead.getText().toString();
        String rMain1 = rMain.getText().toString();
        String rAuth1 = rAuth.getText().toString();
        double rBar3 = rBar2.getRating();

        Calendar calendar = Calendar.getInstance(); //calls calender class which is built into android
        int i = calendar.get(Calendar.DAY_OF_MONTH); //get day of month using calender
        int ii = calendar.get(Calendar.MONTH);

        if (rHead1.isEmpty() || rMain1.isEmpty() || rAuth1.isEmpty() || ((rDay>i) && (rMonth1>ii) ||
                (rMonth1>ii+1)) || ((rDay>29)&&(rMonth1==2)) || ((rDay>30)&&(rMonth1==4)) || ((rDay>30)&&(rMonth1==6))
                || ((rDay>30)&&(rMonth1==9)) || ((rDay>30)&&(rMonth1==11))) //Checking to see if dates are correct and fields are not empty
        {
            Toast.makeText(getApplicationContext(), "You have either entered a wrong date or left a field empty, Please remedy this.", Toast.LENGTH_LONG).show(); //Informs user if date is wrong or field is empty
        }


        else
        {
            app.dbManager.add(new ReviewMod(rDay, rMonth1, rYear1, rHead1, rMain1, rAuth1, rBar3)); //Add the new object to the db
            int test = app.dbManager.getAll().size();//gets size of db

            if (test > 0)
            {
                Toast.makeText(getApplicationContext(), "Item no." + test + " Added to the Database", Toast.LENGTH_LONG).show(); //tells user what number item is in db
            }

            rDate.setValue(1); //resets value to 1 after add is successful
            rMonth.setValue(1);
            rYear.setValue(2016);
            rHead.setText(null);
            rMain.setText(null);
            rAuth.setText(null);
            rBar2.setRating(1);
        }
    }


    public void reset(MenuItem item)
    {
        app.dbManager.getAll().clear();
        app.dbManager.reset();
    }

}
