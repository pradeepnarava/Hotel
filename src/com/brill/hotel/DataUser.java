package com.brill.hotel;

import java.io.ByteArrayOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

public class DataUser {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_MOBILE = "mobile";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_ADDRESS = "address";
	
	private static final String DATABASE_NAME = "Contacts Us";
	private static final String DATABASE_TABLE = "contacts";
	private static final int DATABASE_VERSION = 100;

	private static final String DATABASE_CREATE =
	    "create table contacts(_id integer primary key autoincrement , "+ "phone text not null,"+"mobile text not null,"+"email text not null,"+"address text not null)";

	private final Context context; 
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;


	public DataUser(Context ctx) 
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
	        /*db.execSQL("DROP TABLE IF EXISTS tableone");
	        onCreate(db);*/
	    	  db.execSQL(DATABASE_CREATE);
	    }
	}    

	//---opens the database---
	public DataUser open() throws SQLException 
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
	public long insertval(int _id,String phone,String mobile,String email,String address) 
	{
		
		
		ContentValues initialValues = new ContentValues();
	    

	    initialValues.put(KEY_PHONE, phone);
	   
	    initialValues.put(KEY_MOBILE, mobile);
	    initialValues.put(KEY_EMAIL, email);
	    initialValues.put(KEY_ADDRESS,address);
	   
	    System.out.println("name::::::::"+phone);
	    
	    
	    
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
	    		 
	    		 KEY_PHONE,KEY_MOBILE,KEY_EMAIL,KEY_ADDRESS
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
	 public boolean updateTitle(long rowId, String phone,String mobile,String email,String address)
	    
	    {
	        ContentValues args = new ContentValues();
	        args.put(KEY_PHONE, phone);
	        args.put(KEY_MOBILE, mobile);
	        args.put(KEY_EMAIL, email);
	        args.put(KEY_ADDRESS, address);
	       
	        return db.update(DATABASE_TABLE, args, 
	                         KEY_ROWID + "=" + rowId, null) > 0;
	    }

	}