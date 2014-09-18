package com.junior.stouring;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TouringPlaceDatabaseHelper extends SQLiteOpenHelper{

	 // Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "TP_database.db";
	// Table name
	private static final String TOURING_PLACE = "TP_list";
	// Column names
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_RATING = "rating";
	private static final String COLUMN_LATITUDE = "latitude";
	private static final String COLUMN_LONGITUDE = "longitude";
	
	public TouringPlaceDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	 @Override
	 public void onCreate(SQLiteDatabase db) {
		 db.execSQL("CREATE TABLE " + TOURING_PLACE + "("
		 + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
		 + COLUMN_NAME + " TEXT UNIQUE NOT NULL,"
		 + COLUMN_RATING + " FLOAT NOT NULL,"
		 + COLUMN_LATITUDE + " DOUBLE NOT NULL,"
		 + COLUMN_LONGITUDE + " DOUBLE NOT NULL"+ ")");
	 }
	  
	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 // simple database upgrade operation:
		 // 1) drop the old table
		 db.execSQL("DROP TABLE IF EXISTS " + TOURING_PLACE);
		 // 2) create a new database
		 onCreate(db);
	 }
	 
	 /*public void addPlace(TouringPlace TP){
		         //for logging
		 //Log.d("addBook", TP.toString());
		
		 // 1. get reference to writable DB
		 SQLiteDatabase db = this.getWritableDatabase();
		
		 // 2. create ContentValues to add key "column"/value
		 ContentValues values = new ContentValues();
		 values.put(KEY_TITLE, book.getTitle()); // get title
		 values.put(KEY_AUTHOR, book.getAuthor()); // get author
		
		 // 3. insert
		 db.insert(TABLE_BOOKS, // table
		         null, //nullColumnHack
		         values); // key/value -> keys = column names/ values = column values
		
		 // 4. close
		 db.close();
	 }*/
	 
	 /**
	 * retrieve all items from the database
	 */
	 public List<TouringPlace> getAllItems() {
		 // initialize the list
		 List<TouringPlace> items = new ArrayList<TouringPlace>();
		 // obtain a readable database
		 SQLiteDatabase db = getReadableDatabase();
		 // send query
		 Cursor cursor = db.query(TOURING_PLACE, new String[] {
		 COLUMN_NAME,
		 COLUMN_RATING,
		 COLUMN_LATITUDE,
		 COLUMN_LONGITUDE},
		 null, null, null, null, null, null); // get all rows
		 if (cursor != null) {
			 // add items to the list
			 for(cursor.moveToFirst(); cursor.isAfterLast() == false; cursor.moveToNext()) {
				 items.add(new TouringPlace(cursor.getString(0), Float.parseFloat(cursor.getString(1)), Double.parseDouble(cursor.getString(2)), Double.parseDouble(cursor.getString(3))));
		 }
		 // close the cursor
		 cursor.close();
		 }
		 // close the database connection
		 db.close();
		 // return the list
		 return items;
	 }
	 
	 /**
	 * Add a new item
	 */
	 public void addItem(TouringPlace item) {
		 // prepare values
		 ContentValues values = new ContentValues();
		 values.put(COLUMN_NAME, item.getName());
		 values.put(COLUMN_RATING, item.getMark());
		 values.put(COLUMN_LATITUDE, item.getLatitude());
		 values.put(COLUMN_LONGITUDE, item.getLongitude());
		 // add the row
		 SQLiteDatabase db = getWritableDatabase();
		 db.insert(TOURING_PLACE, null, values);
	 }
	 
	 /**
	 * Add items to the list
	 */
	 public void addItems(List<TouringPlace> items) {
		 if(items != null && items.size() > 0) {
		 // obtain a readable database
		 SQLiteDatabase db = getWritableDatabase();
		 for(TouringPlace item : items) {
			 addItem(item);
		 }
		 // close the database connection
		 db.close();
		 }
	 }
	
	 /**
	 * update an existing item
	 */
	 /*public void updateItem(TouringPlace item) {
		 if(item != null) {
			 // obtain a readable database
			 SQLiteDatabase db = getWritableDatabase();
			 // prepare values
			 ContentValues values = new ContentValues();
			 values.put(COLUMN_NUM_CLICKS, item.getNumClicks());
			 // send query for the row id
			 Cursor cursor = db.query(SOCIAL_ITEMS,
					 new String[] {COLUMN_ID},
					 COLUMN_TITLE + "=?",
					 new String[] {item.title},
					 null, null, null, null);
			 if(cursor != null) {
				 if(cursor.moveToFirst()) {
					 // update the row
					 db.update(SOCIAL_ITEMS, values,
							 COLUMN_ID + "=?",
							 new String[] {cursor.getString(0)});
				 }
				 cursor.close();
			 }
			 // close the database connection
			 db.close();
		 }
	 }*/
	 
}

