package ie.app.wit.dystech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.R;

import ie.app.wit.dystech.SplashScreen;
import ie.app.wit.dystech.main.ReviewApp;

public class Core extends AppCompatActivity
{
    public ReviewApp app;
    public boolean BBP;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        app = (ReviewApp) getApplication(); //start app
        app.dbManager.open(); //open db when app is started
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy(); //when app is closed
        app.dbManager.close(); //close the db
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(ie.app.wit.dystech.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    { //prepares for the menu to be displayed

        app.dbManager.open(); //reopens db when prepping menus, this is so trying to open menu after going back in app doesn't crash
        super.onPrepareOptionsMenu(menu); //prepare menu
        MenuItem menuHome = menu.findItem(ie.app.wit.dystech.R.id.menuHome);
        MenuItem menuList = menu.findItem(ie.app.wit.dystech.R.id.menuList);
        //Intent in = new Intent(this, MainMenuActivity.class);
        //menuList.setIntent(in);
        MenuItem menuReview = menu.findItem(ie.app.wit.dystech.R.id.menuAddArticle);
        MenuItem menuInfo = menu.findItem(ie.app.wit.dystech.R.id.menuInfo);
        MenuItem menuReset = menu.findItem(ie.app.wit.dystech.R.id.menuReset);

        if (app.dbManager.getAll().isEmpty())
        {
            menuList.setEnabled(false);
            menuHome.setEnabled(false); //if db is empty do not let user onto db screen (ghosted out)
            menuReset.setEnabled(false); //if db is empty do not allow user to reset (ghosted out)
        }

        else
        {
            menuHome.setEnabled(true); //otherwise allow user access
            menuReset.setEnabled(true);
            menuList.setEnabled(true);

        }

        if (this instanceof AddReviewActivity)
        {
            menuReview.setVisible(false); //if in Add Review Activity don't show user the Add Review menu option in menu
        }

        if (this instanceof AppInfoActivity)
        {
            menuInfo.setVisible(false);
        }

        if (this instanceof MainActivity)
        {
            menuList.setVisible(false);
        }
        if (this instanceof MainMenuActivity)
        {
            menuHome.setVisible(false);
            menuReview.setVisible(false);
            menuList.setVisible(false);
        }

        return true;
    }



    @Override
    public void onBackPressed()
    {
        BBP = true; //for splashscreen, telling that back button was pressed so cancel the transfer to next activity
        super.onBackPressed(); //allow the back button to bring back user to any activity in the application
        app.dbManager.close(); //close db on backbutton pressed to avoid complications with db
    }

}