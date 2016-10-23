package com.example.contentprovider;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationHelper extends SQLiteOpenHelper {
	
	private final static String DATABASE_NAME="LOCATION_MANAGER";
	private final static int DATABASE_VERSION=1;
    private final static String TABLE_NAME="LOCATIONS";
    private static String KEY_ID="_ID";
	private static String LONGITUDE="_LONGITUDE";
	private static String LATITUDE="_LATITUDE";
	private static String TIMESTAMP="_TIMESTAMP";
	private   final static String query="CREATE TABLE "+ TABLE_NAME +" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+LONGITUDE+" REAL, "+LATITUDE+" REAL, "+ TIMESTAMP+ "  LONG); ";
	
	public LocationHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public Cursor query(String[] columns,String selection ,String[] selectionArgs,String orderBy ){
		return 	this.getReadableDatabase().query(TABLE_NAME, columns, selection, selectionArgs, null, null, orderBy);
	}
	
	public long insert(ContentValues values){
		return this.getWritableDatabase().insert(TABLE_NAME, null, values);
	}
	
	
	//η συνάρτηση επιστρέφει ένα arraylist με όλα τα καταγραμμένα σημεία(longitude,latitude,timestamp)
	
	public ArrayList<Location> getLocations(){
		ArrayList<Location> locations=new ArrayList<Location>();
		Cursor cursor=this.getReadableDatabase().query(TABLE_NAME,null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				int id=cursor.getInt(0);
				double longitude=cursor.getDouble(1);
				double latitude=cursor.getDouble(2);
				int timestamp=cursor.getInt(3);
				Location location=new Location(id,longitude,latitude,timestamp);
				locations.add(location);
			}while(cursor.moveToNext());
		}
		
		return locations;
	}
	

}
