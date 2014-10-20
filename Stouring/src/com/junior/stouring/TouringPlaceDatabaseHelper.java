package com.junior.stouring;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TouringPlaceDatabaseHelper extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 2;
	// Database Name
	private static final String DATABASE_NAME = "TP_database.db";
	// Table name
	private static final String TABLE_TOURING_PLACE = "TP_list";
	private static final String TABLE_USER = "User_list";

	// Common Column names
	private static final String COLUMN_ID = "id";

	// TABLE_TOURING_PLACE Column names
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_RATING = "rating";
	private static final String COLUMN_TYPE = "type";
	private static final String COLUMN_LATITUDE = "latitude";
	private static final String COLUMN_LONGITUDE = "longitude";

	// TABLE_USER
	private static final String COLUMN_FIRST_NAME = "firstname";
	private static final String COLUMN_LAST_NAME = "lastname";
	private static final String COLUMN_EMAIL = "email";
	private static final String COLUMN_PASSWORD = "password";

	// Table Create Statements
	// TABLE_TOURING_PLACE table create statement
	private static final String CREATE_TABLE_TOURING_PLACE = "CREATE TABLE "
			+ TABLE_TOURING_PLACE + "(" + COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
			+ " TEXT UNIQUE NOT NULL," + COLUMN_RATING + " FLOAT NOT NULL,"
			+ COLUMN_TYPE + " STRING NOT NULL," + COLUMN_LATITUDE
			+ " DOUBLE NOT NULL," + COLUMN_LONGITUDE + " DOUBLE NOT NULL" + ")";

	// TABLE_USER table create statement
	private static final String CREATE_TABLE_USER = "CREATE TABLE "
			+ TABLE_USER + "(" + COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRST_NAME
			+ " STRING NOT NULL," + COLUMN_LAST_NAME + " STRING NOT NULL,"
			+ COLUMN_EMAIL + " STRING NOT NULL," + COLUMN_PASSWORD
			+ " STRING NOT NULL" + ")";

	public TouringPlaceDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_TOURING_PLACE);
		db.execSQL(CREATE_TABLE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// simple database upgrade operation:
		// 1) drop the old table
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOURING_PLACE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		// 2) create a new database
		onCreate(db);
	}

	/*
	 * public void addPlace(TouringPlace TP){ //for logging //Log.d("addBook",
	 * TP.toString());
	 * 
	 * // 1. get reference to writable DB SQLiteDatabase db =
	 * this.getWritableDatabase();
	 * 
	 * // 2. create ContentValues to add key "column"/value ContentValues values
	 * = new ContentValues(); values.put(KEY_TITLE, book.getTitle()); // get
	 * title values.put(KEY_AUTHOR, book.getAuthor()); // get author
	 * 
	 * // 3. insert db.insert(TABLE_BOOKS, // table null, //nullColumnHack
	 * values); // key/value -> keys = column names/ values = column values
	 * 
	 * // 4. close db.close(); }
	 */

	/**
	 * retrieve all items from the database
	 */
	public List<TouringPlace> getAllItems() {
		// initialize the list
		List<TouringPlace> items = new ArrayList<TouringPlace>();
		// obtain a readable database
		SQLiteDatabase db = getReadableDatabase();
		// send query
		Cursor cursor = db.query(TABLE_TOURING_PLACE, new String[] {
				COLUMN_NAME, COLUMN_RATING, COLUMN_TYPE, COLUMN_LATITUDE,
				COLUMN_LONGITUDE }, null, null, null, null, null, null); // get
																			// all
																			// rows
		if (cursor != null) {
			// add items to the list
			for (cursor.moveToFirst(); cursor.isAfterLast() == false; cursor
					.moveToNext()) {
				items.add(new TouringPlace(cursor.getString(0), Float
						.parseFloat(cursor.getString(1)), cursor.getString(2),
						Double.parseDouble(cursor.getString(3)), Double
								.parseDouble(cursor.getString(4))));
			}
			// close the cursor
			cursor.close();
		}
		// close the database connection
		db.close();
		// return the list
		return items;
	}

	// PAR MATHIEU
	public List<TouringPlace> getPlaceType(String sType) {
		// initialize the list
		List<TouringPlace> items = new ArrayList<TouringPlace>();
		// obtain a readable database
		SQLiteDatabase db = getReadableDatabase();
		// send query
		Cursor cursor = db.rawQuery("select * from TP_List where type = ?",
				new String[] { sType });

		if (cursor != null) {
			// add items to the list
			for (cursor.moveToFirst(); cursor.isAfterLast() == false; cursor
					.moveToNext()) {
				items.add(new TouringPlace(cursor.getString(1), Float
						.parseFloat(cursor.getString(2)), cursor.getString(3),
						Double.parseDouble(cursor.getString(4)), Double
								.parseDouble(cursor.getString(5))));
			}
			// close the cursor
			cursor.close();
		}

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
		values.put(COLUMN_TYPE, item.getType());
		values.put(COLUMN_LATITUDE, item.getLatitude());
		values.put(COLUMN_LONGITUDE, item.getLongitude());
		// add the row
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TABLE_TOURING_PLACE, null, values);
	}
	
	/**
	 * Get TouringPlace from name
	 */
	public TouringPlace getTouringPlaceFromDB(String pName) {
		
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT  * FROM " + TABLE_TOURING_PLACE + " WHERE "
	            + COLUMN_NAME + " = '" + pName + "'";
		
		Log.i("Query", selectQuery);
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c != null)
	        c.moveToFirst();
	 
	    TouringPlace tp = new TouringPlace("", 0, "", 0, 0);
	    tp.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
	    tp.setMark(c.getFloat(c.getColumnIndex(COLUMN_RATING)));
	    tp.setType(c.getString(c.getColumnIndex(COLUMN_TYPE)));
	    tp.setLatitude(c.getDouble(c.getColumnIndex(COLUMN_LATITUDE)));
	    tp.setLongitude(c.getDouble(c.getColumnIndex(COLUMN_LONGITUDE)));
	    
	    return tp;
	}
	
	/**
	 * Check if a Tourist Place exists from name
	 */
	public Boolean checkIfTouringPlaceExists(String pName) {
			
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT  * FROM " + TABLE_TOURING_PLACE + " WHERE "
	            + COLUMN_NAME + " = '" + pName + "'";
		
		Log.i("Query", selectQuery);
		
		Cursor c = db.rawQuery(selectQuery, null);
		c.moveToLast();
		int count = c.getCount();
		
		if (count != 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Add a new user
	 */
	public void addUser(User pUser) {
		// prepare values
		ContentValues values = new ContentValues();
		values.put(COLUMN_FIRST_NAME, pUser.getFirstName());
		values.put(COLUMN_LAST_NAME, pUser.getLastName());
		values.put(COLUMN_EMAIL, pUser.getEmail());
		values.put(COLUMN_PASSWORD, pUser.getPassword());
		// add the row
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TABLE_USER, null, values);
	}

	/**
	 * Add items to the list
	 */
	public void addItems(List<TouringPlace> items) {
		if (items != null && items.size() > 0) {
			// obtain a readable database
			SQLiteDatabase db = getWritableDatabase();
			for (TouringPlace item : items) {
				addItem(item);
			}
			// close the database connection
			db.close();
		}
	}

	public void deleteItem(TouringPlace pTP) {
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		// 2. delete
		db.delete(TABLE_TOURING_PLACE, // table name
				COLUMN_NAME + " = ?", // selections
				new String[] { pTP.getName() }); // selections args

		// 3. close
		db.close();
	}

	public void deleteAllItem() {
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		// 2. delete
		db.delete(TABLE_TOURING_PLACE, // table name
				null, // All rows
				null); // selections args

		// 3. close
		db.close();
	}

	public List<User> getAllUsers() {
		// initialize the list
				List<User> items = new ArrayList<User>();
				// obtain a readable database
				SQLiteDatabase db = getReadableDatabase();
				// send query
				Cursor cursor = db.query(TABLE_USER, new String[] {
						COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_EMAIL,
						COLUMN_PASSWORD }, null, null, null, null, null, null); // get
																					// all
																					// rows
				if (cursor != null) {
					// add items to the list
					for (cursor.moveToFirst(); cursor.isAfterLast() == false; cursor
							.moveToNext()) {
						items.add(new User(cursor.getString(0), cursor.getString(1), cursor.getString(2),
								cursor.getString(3)));
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
	 * update an existing item
	 */
	/*
	 * public void updateItem(TouringPlace item) { if(item != null) { // obtain
	 * a readable database SQLiteDatabase db = getWritableDatabase(); // prepare
	 * values ContentValues values = new ContentValues();
	 * values.put(COLUMN_NUM_CLICKS, item.getNumClicks()); // send query for the
	 * row id Cursor cursor = db.query(SOCIAL_ITEMS, new String[] {COLUMN_ID},
	 * COLUMN_TITLE + "=?", new String[] {item.title}, null, null, null, null);
	 * if(cursor != null) { if(cursor.moveToFirst()) { // update the row
	 * db.update(SOCIAL_ITEMS, values, COLUMN_ID + "=?", new String[]
	 * {cursor.getString(0)}); } cursor.close(); } // close the database
	 * connection db.close(); } }
	 */

}
