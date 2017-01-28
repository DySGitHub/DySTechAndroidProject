package ie.app.wit.dystech.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import ie.app.wit.dystech.activities.Core;
import ie.app.wit.dystech.db.DBDesigner;
import ie.app.wit.dystech.models.ReviewMod;


public class DBManager
{

    public SQLiteDatabase database; //assigning Name to db
    public DBDesigner dbHelper; //Assiging name to dbDesigner class

    public DBManager(Context context)
    {
        dbHelper = new DBDesigner(context);
    }

    public void open() throws SQLException //open db and throw exception if failed
    {
        database = dbHelper.getWritableDatabase(); //Gets a database which i am able to edit and change (hence get writable)
    }




    public void close()
    {
        database.close(); // Closes the DB
    }



    public void add(ReviewMod rm)
    { //The data from object class being added

        ContentValues inputs = new ContentValues();
        inputs.put("Day", rm.rDay); //The data in object class for the day is added into db
        inputs.put("Month", rm.rMonth);
        inputs.put("Year", rm.rYear);
        inputs.put("Heading", rm.rHead);
        inputs.put("Main", rm.rMain);
        inputs.put("Author", rm.rAuth);
        inputs.put("Rating", rm.rBar2);
        database.insert("reviewmod", null, inputs); //inputs all data above from object into db
    }




    public List<ReviewMod> getAll() //A list of all items within object
    {
        List<ReviewMod> reviews = new ArrayList<>(); //creates arraylist with all items in db
        Cursor cursor = database.rawQuery("SELECT * FROM reviewmod", null); //gets all the data in the db
        cursor.moveToFirst();


        while (!cursor.isAfterLast()) //while the items in db is not the last item
        {
            ReviewMod r = toReview(cursor);
            reviews.add(r); //add item
            cursor.moveToNext(); //continue to next item
        }

        cursor.close(); //when that is complete close the cursor
        return reviews;
    }




    public ReviewMod toReview(Cursor cursor)
    {

        ReviewMod pojo = new ReviewMod();
        pojo.ID = cursor.getInt(0); //sorting the data from the object that is being added to database
        pojo.rDay = cursor.getInt(1);
        pojo.rMonth = cursor.getInt(2);
        pojo.rYear = cursor.getInt(3);
        pojo.rHead = cursor.getString(4);
        pojo.rMain = cursor.getString(5);
        pojo.rAuth = cursor.getString(6);
        pojo.rBar2 = cursor.getInt(7);
        return pojo;
    }



    public void delete(Long IDfind2)   // delete the selected item you clicked
    {
        database.delete("reviewmod", "ID" + "=?", new String[]{String.valueOf(IDfind2)}); //sql code that deletes item in db where ID is IDfind2
    }




    public void reset()
    {
        database.delete("reviewmod", null, null); //deletes all items in db
    }
}
