package ie.app.wit.dystech.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import ie.app.wit.dystech.R;

/**
 * Created by dylan on 07/02/2016.
 */
public class AppInfoActivity extends Core
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);
        final Button ratingSubmit = (Button) findViewById(R.id.RatingSubmitButton);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View SubmitText) { //When Submit Button is Clicked
                Toast.makeText(getApplicationContext(), "Thank you for submitting feedback on this app!" + "You have rated it: " + ratingBar.getRating() + " stars", Toast.LENGTH_SHORT).show(); //Thanks and informs user of rating
                float rating = ratingBar.getRating();
                ratingBar.setRating(rating);
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    @Override
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


    public void reset(MenuItem item)
    {
        app.dbManager.getAll().clear();
        app.dbManager.reset();
    }


}

