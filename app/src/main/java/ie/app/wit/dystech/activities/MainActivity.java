package ie.app.wit.dystech.activities;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import ie.app.wit.dystech.R;
import ie.app.wit.dystech.db.DBManager;
import ie.app.wit.dystech.models.ReviewMod;


public class MainActivity extends Core
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    { //When Activity is opened run code in this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewslist); //Sets the XML file to be linked to the activity
        final ListView ListV = (ListView) findViewById(R.id.reviewList); //Assigning the listview in the XML to ListV
        final ReviewAdapter adapter = new ReviewAdapter(this, app.dbManager.getAll()); //Creates ArrayAdapter that contains everything within DB
        ListV.setAdapter(adapter);



        ListV.setOnItemClickListener(new AdapterView.OnItemClickListener()
        { //When you click on item in DB the code on this method will run
            @Override
            public void onItemClick(final AdapterView<?> parent, final View View, final int position,
                                    long ID)
            { //get parent, view, position of item in DB and the unassigned ID

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //Creates AlertDialog when Item is pressed
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete the item with Details: " + parent.getItemAtPosition(position).toString()); //Asks the user do they want to delete item with the data displayed in box
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()


                { //When positive button (yes) is clicked run code in this method
                    public void onClick(DialogInterface dialog, int choose)
                    {
                        Long IDfind2 = app.dbManager.getAll().get(position).ID; //Finding ID of item clicked in DB
                        adapter.remove(adapter.getItem(position)); //Removes the item in ArrayAdapter
                        app.dbManager.delete(IDfind2); //calls method in  dbManager to delete the item in DB
                        Toast.makeText(getApplicationContext(), "Item Successfully Deleted.", Toast.LENGTH_SHORT).show(); //Confirm to User delete was successful
                        ListV.removeViewInLayout(View); //Remove item in listview
                        ListV.deferNotifyDataSetChanged();
                    }

                }
                );


                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        { //If user clicks negative button (Cancel)
                            public void onClick(DialogInterface dialog, int choose)
                            {
                                dialog.dismiss(); //Dismiss box and keep data
                            }
                        }
                );
                builder.show(); //Dialog displayed when the criteria for it to show is met

            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu); //Make Menu Appear in Activity
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuHome: //Set visible set as default in Core
                startActivity(new Intent(this, MainMenuActivity.class));
                break;

            case R.id.menuList: startActivity (new Intent(this, MainActivity.class));
                break;

            case R.id.menuInfo: //When menuInfo is clicked....
                startActivity(new Intent(this, AppInfoActivity.class));//....Jump to AppInfoActivity
                break;

            case R.id.menuAddArticle:
                startActivity(new Intent(this, AddReviewActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }




    public void reset(MenuItem item) //When menu Item reset is clicked
    {
        app.dbManager.getAll().clear(); //Get everything in db and clear it
        app.dbManager.reset(); //Reset the db
    }

    



    class ReviewAdapter extends ArrayAdapter<ReviewMod>
    {
        private Context context;
        public List<ReviewMod> rViews; //Creating a List of all items in the object class (ReviewMod)




        public ReviewAdapter(Context context, List<ReviewMod> rViews)
        { //Adding List into ArrayAdapter
            super(context, R.layout.row_review, rViews);
            this.context = context;
            this.rViews = rViews;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.row_review, null); //Show row_review.xml
            ReviewMod review = rViews.get(position); //get the position in db
            TextView rDayView = (TextView) view.findViewById(R.id.row_day); //Assigning in row_day to textview
            TextView rMonthView = (TextView) view.findViewById(R.id.row_month);
            TextView rYearView = (TextView) view.findViewById(R.id.row_year);
            TextView rHeadView = (TextView) view.findViewById(R.id.row_head);
            TextView rMainView = (TextView) view.findViewById(R.id.row_main);
            TextView rAuthView = (TextView) view.findViewById(R.id.row_auth);
            TextView rBarView = (TextView) view.findViewById(R.id.row_score);

            rDayView.setText("Day Published: " + String.valueOf(review.rDay)); //Assigns the Day Published that was entered by User in Add Review Activity
            rMonthView.setText("Month Published: " + String.valueOf(review.rMonth));
            rYearView.setText("Year Published: " + String.valueOf(review.rYear) + System.getProperty("line.separator"));
            rHeadView.setText(review.rHead + System.getProperty("line.separator")); //No need to convert data to string as it is already a string, line separator to add a space below data
            rMainView.setText(review.rMain + System.getProperty("line.separator"));
            rAuthView.setText("Written By: " + review.rAuth + System.getProperty("line.separator"));
            rBarView.setText("Final Rating: " + String.valueOf(review.rBar2) + " out of 5.0" + System.getProperty("line.separator") + "--------------------------------------------------------------");

            return view;
        }


        @Override
        public int getCount()
        {

            return rViews.size(); //Get the no of items in the list

        }



    }
}
