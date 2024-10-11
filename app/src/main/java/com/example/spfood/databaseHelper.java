package com.example.spfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SPFOODS";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "FOODS";
    private static final String COLUMN_ID = "food_id";
    private static final String COLUMN_NAME = "food_name";
    private static final String COLUMN_TYPE = "food_type";
    private static final String COLUMN_PRICE = "food_price";
    private static final String COLUMN_STORE = "store_name";

    public databaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + COLUMN_NAME + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_PRICE + " INTEGER, " + COLUMN_STORE + " TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);

        onCreate(db);
    }

    public void addFood (String name, String type, int price, String store){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_STORE, store);


        db.insert(TABLE_NAME,null,values);

    }

}
