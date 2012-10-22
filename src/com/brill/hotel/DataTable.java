package com.brill.hotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataTable {
	
	public static final String KEY_ROWID = "_id";
	//public static final String KEY_TABLENO= "table_no";
	public static final String KEY_TABLEID= "table_id";
   
    
    private static final String DATABASE_NAME = "Table";
    private static final String DATABASE_TABLE = "tableinformation";
    private static final int DATABASE_VERSION = 100;
    
    private static final String DATABASE_CREATE =
        "create table tableinformation(_id integer primary key autoincrement ,"+ "table_id text not null)";
	
    private final Context context; 
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
	

    public DataTable(Context ctx) 
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
    public DataTable open() throws SQLException 
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
    public long insertval(int _id,String tableid) 
    {
       	
    	
    	ContentValues initialValues = new ContentValues();
        
 
        //initialValues.put(KEY_TABLENO, tableno);
        initialValues.put(KEY_TABLEID, tableid);
       
        //System.out.println("name::::::::"+tax);
        
        
        
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
        		 
        		 KEY_ROWID,KEY_TABLEID
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
    public Cursor getTitle(long rowId) throws SQLException 
    {
        Cursor mCursor =  db.query(true, DATABASE_TABLE, new String[] {
        		KEY_ROWID,KEY_TABLEID
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
    public boolean updateTitle(long rowId, String tableid)
    
    {
        ContentValues args = new ContentValues();
        args.put(KEY_TABLEID, tableid);
       
        return db.update(DATABASE_TABLE, args, 
                         KEY_ROWID + "=" + rowId, null) > 0;
    }


	  

}
