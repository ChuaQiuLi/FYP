package sg.edu.rp.c346.id20007649.fyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class FavDB extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "fav_keyword_sign.db";
    private static final int DATABASE_VERSION = 6;
    private static final String TABLE_FAV = "fav";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FAV_NAME = "fav_name";
    private static final String COLUMN_FAV_VIDEO = "fav_video";
    private static final String COLUMN_FAV_DESCRIPTION = "fav_description";


    public FavDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_FAV + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FAV_NAME  + " TEXT UNIQUE, " + COLUMN_FAV_VIDEO + " TEXT UNIQUE, " +  COLUMN_FAV_DESCRIPTION  + " TEXT UNIQUE ) ";
        db.execSQL(createSongTableSql);



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV);
        onCreate(db);

    }

    public long insertFav(String name, String video, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAV_NAME, name);
        values.put(COLUMN_FAV_VIDEO, video);
        values.put(COLUMN_FAV_DESCRIPTION, description);


        long result = db.insert(TABLE_FAV, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;


    }



    public ArrayList<Video> getAllKeywordSign() {

        ArrayList<Video> fav = new ArrayList<Video>();

        String selectQuery = "SELECT " + COLUMN_ID + "," + COLUMN_FAV_NAME + "," + COLUMN_FAV_VIDEO +  "," + COLUMN_FAV_DESCRIPTION  + " FROM " + TABLE_FAV;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String video = cursor.getString(2);
                String description = cursor.getString(3);
                Video favouriteVideo = new Video(id, name, video, description);

                fav.add(favouriteVideo);


            }

            while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return fav;

    }


    public int deleteKeywordSign(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_FAV, condition, args);
        db.close();
        return result;

    }


}
