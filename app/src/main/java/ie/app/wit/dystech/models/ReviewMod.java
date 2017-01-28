package ie.app.wit.dystech.models;

/**
 * Created by dylan on 23/02/2016.
 */
public class ReviewMod
{

    public long ID; //Setting Datatypes for each variable to be entered to DB
    public int rDay;
    public int rMonth;
    public int rYear;
    public String rHead;
    public String rMain;
    public String rAuth;
    public double rBar2;


    public ReviewMod (int rDay, int rMonth, int rYear, String rHead, String rMain, String rAuth, double rBar2) //Assign the variables to object
    {
        this.rDay = rDay;
        this.rMonth = rMonth;
        this.rYear = rYear;
        this.rHead = rHead;
        this.rMain = rMain;
        this.rAuth = rAuth;
        this.rBar2 = rBar2;
    }

    public ReviewMod ()
    {
        this.rDay = 0; //Default values for data
        this.rMonth = 0;
        this.rYear = 0;
        this.rHead = "";
        this.rMain = "";
        this.rAuth = "";
        this.rBar2 = 0;
    }

    public String toString()
    {
        return (ID + ", " + rDay + ", " + rMonth + ", " + rYear + ", " + rHead + ", " + rMain + ", " + rAuth +", " + rBar2); //When toString is called the following data will be shown
    }

}
