package com.brill.hotel;

import java.io.ByteArrayOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

public class DataOrder {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
	
	public static final String KEY_PRICE = "price";
	
	private static final String DATABASE_NAME = "Order";
	private static final String DATABASE_TABLE = "orders";
	private static final int DATABASE_VERSION = 100;

	private static final String DATABASE_CREATE =
	    "create table orders(_id integer primary key autoincrement , "+ "name text not null,"+"price number not null)";

	private final Context context; 
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;


	public DataOrder(Context ctx) 
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
	       // db.execSQL("DROP TABLE IF EXISTS tableone");
	        //onCreate(db);
	    	  db.execSQL(DATABASE_CREATE);
	    }
	}    

	//---opens the database---
	public DataOrder open() throws SQLException 
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
	public long insertval(int _id,String name,String price,Bitmap b) 
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    b.compress(Bitmap.CompressFormat.PNG, 10, out);
		
		
		ContentValues initialValues = new ContentValues();
	    

	    initialValues.put(KEY_NAME, name);
	    initialValues.put(KEY_PRICE, price);
	   
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
	    		 
	    		KEY_NAME,KEY_PRICE
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
	 public boolean updateTitle(long rowId, String name)
	    
	    {
	        ContentValues args = new ContentValues();
	        args.put(KEY_NAME, name);
	       
	        return db.update(DATABASE_TABLE, args, 
	                         KEY_ROWID + "=" + rowId, null) > 0;
	    }

}
