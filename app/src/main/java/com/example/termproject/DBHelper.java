package com.example.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static java.sql.DriverManager.println;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MySongs.db";
    public static final String SONGS_TABLE_NAME = "songs";
    public static final String SONGS_COLUMN_ID = "id";
    public static final String SONGS_COLUMN_NAME = "name";
    public static final String SONGS_COLUMN_YEAR = "year";
    public static final String SONGS_COLUMN_LYRIC = "lyric";
    public static final String SONGS_COLUMN_COMPOSER = "composer";
    public static final String SONGS_COLUMN_ALBUM = "album";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table songs " + "(id integer primary key, name text, year text, lyric text, composer text, album text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SONGS");
        onCreate(db);
    }

    public boolean insertSong(String name, String year, String lyric, String composer, String album) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("year", year);
        contentValues.put("lyric", lyric);
        contentValues.put("composer", composer);
        contentValues.put("album", album);

        db.insert("songs", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from songs where id =" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SONGS_TABLE_NAME);
        return numRows;
    }

    public boolean updateSong(Integer id, String name, String year, String lyric, String composer, String album) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("year", year);
        contentValues.put("lyric", lyric);
        contentValues.put("composer", composer);
        contentValues.put("album", album);
        db.update("songs", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteSong(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("songs", "id = ? ", new String[]{Integer.toString(id)});
    }

    int s_id = 0;
    String s_name,s_year, s_lyric, s_composer,s_album = "";
    public void searchSong(String searchName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from songs where name='" + searchName + "';", null);
        while (c.moveToNext()) {
            s_id= c.getInt(c.getColumnIndex("id"));
            s_name = c.getString(c.getColumnIndex("name"));
            s_year = c.getString(c.getColumnIndex("year"));
            s_lyric = c.getString(c.getColumnIndex("lyric"));
            s_composer = c.getString(c.getColumnIndex("composer"));
            s_album = c.getString(c.getColumnIndex("album"));
        }
    }


    public ArrayList getAllSongs() {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from songs", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(SONGS_COLUMN_ID)) + " " +
                    res.getString(res.getColumnIndex(SONGS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

}
