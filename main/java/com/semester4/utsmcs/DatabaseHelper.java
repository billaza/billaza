package com.semester4.utsmcs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

// NABILLA DRIESANDIA AZARINE
// 2301846383

class DatabaseHelper extends SQLiteOpenHelper {

    private Context ctx;
    private static final String DATABASE_NAME = "Spending.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_spending";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "Nama";
    private static final String COLUMN_DATE = "Tanggal";
    private static final String COLUMN_COST = "Nominal";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_DATE + " TEXT, "
                + COLUMN_COST + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addItem(String itemName, String date, String itemCost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, itemName);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_COST, itemCost);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(ctx, "Oops, please try again!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ctx, "Data added successfully!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllItem() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateItem(String row_id, String itemName, String date, String itemCost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, itemName);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_COST, itemCost);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(ctx, "Oops, please try again!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ctx, "Data updated successfully!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
