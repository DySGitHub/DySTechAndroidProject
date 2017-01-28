package ie.app.wit.dystech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import ie.app.wit.dystech.R;

/**
 * Created by dylan on 07/02/2016.
 */
public class MainMenuActivity extends Core
{


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menumain_activity);
        ImageButton AR  = (ImageButton) findViewById(R.id.imgButton1);

        ImageButton SL = (ImageButton) findViewById(R.id.imgButton1);

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



    public void ARPressed(View view)
{
    Intent in = new Intent(MainMenuActivity.this, AddReviewActivity.class);
    MainMenuActivity.this.startActivity(in);
}

    public void SLPressed(View view)
    {
        Intent in2 = new Intent(MainMenuActivity.this, MainActivity.class);
        MainMenuActivity.this.startActivity(in2);
    }


    public void reset(MenuItem item)
    {
        app.dbManager.getAll().clear();
        app.dbManager.reset();
    }


}

