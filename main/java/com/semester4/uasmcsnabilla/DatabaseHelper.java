package com.semester4.uasmcsnabilla;

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
    private static final String DATABASE_NAME = "Dictionary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "saved_words";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "word";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void saveWord(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
            cv.put(COLUMN_NAME, word);

            long result = db.insert(TABLE_NAME, null, cv);
            if(result == -1) {
                Toast.makeText(ctx, "Failed to save!",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(ctx, "Word saved successfully!",
                        Toast.LENGTH_SHORT).show();
            }
    }

    Cursor readWord(String word) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE word='" + word + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllWords() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void deleteWord(String id, String word) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, COLUMN_NAME + "='" + word + "'", null);
        if(result == -1) {
            Toast.makeText(ctx, "Oops, please try again!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ctx, "Removed successfully!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}