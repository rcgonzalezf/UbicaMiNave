package com.dev52.model.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "UbicaMiNaveDB";
	
	private static final int version = 1;
	
	private static final CursorFactory cursorFactory = null;
	
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	private static final String DATABASE_CREATE_SCRIPT = "CREATE TABLE "
			+ CarLocations.TABLE_NAME + " (" + CarLocations.ID
			+ " INTEGER primary key autoincrement, "
			+ CarLocations.LONGITUDE + " REAL not null, "
			+ CarLocations.LATITUDE + " REAL not null, "
			+ CarLocations.ALTITUDE + " REAL not null, "
			+ CarLocations.CREATED_AT + "created TEXT not null, "
			+ CarLocations.ADDRESS + " TEXT null);";

	
	public SQLiteDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, cursorFactory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_SCRIPT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CarLocations.TABLE_NAME);

		onCreate(db);
	}

}
