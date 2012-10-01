package com.brill.hotel;

import java.io.ByteArrayOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

public class DataMenuImage {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_PRICE = "price";
	public static final String KEY_IMAGE= "image";
	public static final String KEY_CATEGORY = "category";
	private static final String DATABASE_NAME = "Add MenuImage";
	private static final String DATABASE_TABLE = "menuimage";
	private static final int DATABASE_VERSION = 100;

	private static final String DATABASE_CREATE =
	    "create table menuimage(_id integer primary key autoincrement , "+ "name text not null,"+"price number not null,"+"category text not null,"+"description text not null,"+"image text not null)";

	private final Context context; 
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;


	public DataMenuImage(Context ctx) 
	{
	    this.context = ctx;
	    DBHelper = new DatabaseHelper(context);
	}


	private static class DatabaseHelper extends SQLiteOpenHelper 
	{
	    DatabaseHelper(Context context) 
	    {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) 
	    {
	        db.execSQL(DATABASE_CREATE);
	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	    {
	        //Log.w(TAG, "Upgrading database from version " + oldVersion+ " to "+ newVersion + ", which will destroy all old data");
	        //db.execSQL("DROP TABLE IF EXISTS tableone");
	       // onCreate(db);
	    	  db.execSQL(DATABASE_CREATE);
	    }
	}    

	//---opens the database---
	public DataMenuImage open() throws SQLException 
	{
	    db = DBHelper.getWritableDatabase();
	    return this;
	}

	//---closes the database---    
	public void close() 
	{
	    DBHelper.close();
	}

	//---insert a title into the database---
	public long insertval(int _id,String name,String price,String category,String description,String image) 
	{
		
		
		
		ContentValues initialValues = new ContentValues();
	    

	    initialValues.put(KEY_NAME, name);
	    initialValues.put(KEY_PRICE, price);
	    initialValues.put(KEY_CATEGORY, category);
	    
	    initialValues.put(KEY_DESCRIPTION, description);
	    initialValues.put(KEY_IMAGE, image);
	    System.out.println("name::::::::"+name);
	    
	    
	    
	    return db.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean deleteTitle(long rowId) 
	{
	    return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	public Cursor getlistitems() 
	{
		Cursor mCursor =
	     db.query(DATABASE_TABLE, new String[] {
	    		 
	    		KEY_NAME,KEY_PRICE,KEY_CATEGORY,KEY_DESCRIPTION,KEY_IMAGE
	            },
	            null,
	            null, 
	            null, 
	            null, 
	            null, 
	            null);
		if (mCursor != null) {
	        mCursor.moveToFirst();
	    }
	    return mCursor;
	}
	
	public Cursor getlistcat(String category) throws SQLException 
	{
		if(category.equalsIgnoreCase("")){
			Cursor mCursor =
				     db.query(DATABASE_TABLE, new String[] {
				    		 
				    		KEY_NAME,KEY_PRICE,KEY_CATEGORY,KEY_DESCRIPTION
				            }, 
				            null, 
				            null, 
				            null, 
				            null, 
				            null);
					if (mCursor != null) {
				        mCursor.moveToFirst();
				    }
				    return mCursor;
		}
		
		else
		{
			Cursor mCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE category=? ", new String[]{category});
			if (mCursor != null) {
		        mCursor.moveToFirst();
		    }
		    return mCursor;
		    }
		
	}
	
	public Cursor getTitle(long rowId) throws SQLException 
	{
	    Cursor mCursor =  db.query(true, DATABASE_TABLE, new String[] {
	    		KEY_ROWID ,KEY_NAME,KEY_PRICE,KEY_CATEGORY,KEY_DESCRIPTION,KEY_IMAGE
	            		}, 
	            		KEY_ROWID+ "=" + rowId, 
	            		null,
	            		null, 
	            		null, 
	            		null, 
	            		null);
	    if (mCursor != null) {
	        mCursor.moveToFirst();
	    }
	    return mCursor;
	}
	public boolean updateTitle(long rowId, String name,String price,String category,String description,String image)

	{
	    ContentValues args = new ContentValues();
	    args.put(KEY_NAME, name);
	    args.put(KEY_PRICE, price);
	    args.put(KEY_CATEGORY, category);
	    args.put(KEY_DESCRIPTION, description);
	    args.put(KEY_IMAGE, image);
	    return db.update(DATABASE_TABLE, args, 
	                     KEY_ROWID + "=" + rowId, null) > 0;
	}
	/*public boolean updatePrice(long rowId, String price)

	{
	    ContentValues args = new ContentValues();
	    args.put(KEY_PRICE, price);
	   
	    return db.update(DATABASE_TABLE, args, 
	                     KEY_ROWID + "=" + rowId, null) > 0;
	}
	public boolean updateCategory(long rowId, String category)

	{
	    ContentValues args = new ContentValues();
	    args.put(KEY_CATEGORY, category);
	   
	    return db.update(DATABASE_TABLE, args, 
	                     KEY_ROWID + "=" + rowId, null) > 0;
	}
*/
	public boolean Category(String category) throws SQLException 
	{
		Cursor mCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE category=? ", new String[]{category});
	    if (mCursor != null) {           
	        if(mCursor.getCount() > 0)
	        {
	        	 
	        	return true;
	        }
	    }
	 return false;
	 

	}
	/*public Cursor getcategory(String category) throws SQLException
	{
		Cursor mCursor = db.query("SELECT * FROM " + DATABASE_TABLE + " WHERE category=? ", new String[]{
	    		 
	    		KEY_NAME,KEY_PRICE,KEY_CATEGORY,KEY_DESCRIPTION
	            }, 
	            null, 
	            null, 
	            null, 
	            null, 
	            null);
		if (mCursor != null) {
	        mCursor.moveToFirst();
	    }
	    return mCursor;
	}*/
	}