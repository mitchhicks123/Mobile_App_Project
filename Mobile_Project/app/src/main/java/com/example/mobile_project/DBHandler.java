package com.example.mobile_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Locale;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    private static final String DB_NAME = "projectDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "userAuthentication";

    //initialize column values
    private static final String ID_COL = "id";
    private static final String USERNAME_COL = "username";
    private static final String PASSWORD_COL = "password";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";

        db.execSQL(query);
    }


    // this method is use to add new course to our sqlite database.
    public String register(String username, String password) {

        SQLiteDatabase dbRead = this.getReadableDatabase();
        Cursor cursor = dbRead.query(TABLE_NAME, new String[]{USERNAME_COL}, USERNAME_COL + "=?", new String[]{username},null, null, null);
        //check to see if
        if(cursor.moveToFirst()){
            dbRead.close();
            return "failed";

        }else{ //empty query
            SQLiteDatabase dbWrite = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            //first check db for existing user
            // passing all values with their key value
            values.put(USERNAME_COL, username);
            values.put(PASSWORD_COL, password);
            dbWrite.insert(TABLE_NAME, null, values);

            // close db connection
            dbWrite.close();
            dbRead.close();
            return "success";
        }
    }
    // this method is use to add new course to our sqlite database.
    public String login(String username, String password) {

        SQLiteDatabase dbRead = this.getReadableDatabase();
        Cursor cursor = dbRead.query(TABLE_NAME, new String[]{USERNAME_COL}, USERNAME_COL + "=?" + " AND " + PASSWORD_COL + "=?", new String[]{username, password},null, null, null);//check to see if
        String user;
        if(cursor.moveToFirst()){
            user = cursor.getString(0);
            dbRead.close();
            return user;

        }else{ //empty query
            dbRead.close();
            return "failed";
        }
    }
    //this method is used to search for an address in the database
    public double[] readLocationAddress(String address) {

        address = address.toLowerCase(Locale.ROOT);
        //query database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{PASSWORD_COL}, USERNAME_COL + "=?", new String[]{address},null, null, null);

        double[] latLong = new double[2];

        //checks to see if there are values in the cursor... otherwise empty query
        if(cursor.moveToFirst()){
            latLong[0] = cursor.getDouble(0);
            latLong[1] = cursor.getDouble(1);
            db.close();
            return latLong;

        }else{ //empty query
            latLong[0] = 200;
            db.close();
            return latLong;
        }
    }
    //this method is used to update an address in the database
    public void updateLocationAddress(String newAddress, String oldAddress) {

        oldAddress = oldAddress.toLowerCase(Locale.ROOT);
        newAddress = newAddress.toLowerCase(Locale.ROOT);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COL, newAddress);
        db.update(TABLE_NAME, values, USERNAME_COL + "=?", new String[]{oldAddress});
        db.close();
    }
    //this method is used to delete an address in the database
    public void deleteLocationAddress(String address) {

        address = address.toLowerCase(Locale.ROOT);
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME, USERNAME_COL + "=?", new String[]{address});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
