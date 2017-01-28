package ie.app.wit.dystech.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dylan on 23/02/2016.
 */
public class DBDesigner extends SQLiteOpenHelper
{
    private static final String DB_NAME = "ReviewMod.db"; //name of db
    private static final int DB_VERSION = 2;
    private static final String DB_CREATE_TABLE_REVIEWMOD = "create table reviewmod "
            + "(ID integer primary key autoincrement," + "Day integer not null, " + "Month integer not null, "
            + "Year integer not null, " +"Heading text not null, " + "Main text not null, " + "Author text not null, "
            + "Rating integer not null);"; //creating the table reviewmod with ID that is pk and autoincrements

    public DBDesigner(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(DB_CREATE_TABLE_REVIEWMOD); //when creating db execute sql to create the table also
    }

    @Override
    public void onUpgrade(SQLiteDatabase RMDB, int oldV, int newV)
    { //when updating versions

        Log.w(DBDesigner.class.getName(), "Upgrade database from Version " + oldV + " to "
                + newV + ", this will delete the previous data."); //in log tell user that this will delete data
        RMDB.execSQL("DROP TABLE IF EXISTS ReviewMod"); //delete the old table
        onCreate(RMDB);//Create new table
    }
}