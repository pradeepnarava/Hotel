package com.brill.hotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter 
{
	public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASS = "password";
    public static final String KEY_PATTERN = "pattern";
    public static final String KEY_PATTERN_SITES = "patternsites";
    public static final String KEY_PATTERN_SITES_FOR_DATA = "pattern_sites";
    public static final String KEY_PATTERN_SITES_FOR_DATAID = "pattern_sites_id";
    public static final String KEY_PATTERN_STATUS = "status";
	private static final String KEY_VALUE = "value";
	private static final String KEY_QUESTION = "question";
	private static final String KEY_ANSWER = "answer";
   // private static final String TAG = "DBAdapter";
    static final String patternsites="pattern_sites_id";
    private static final String DATABASE_NAME = "hotels";
    private static final String TABLE_NAME_CATEGORY = "categories";
    
    
    
    
    private static final String DATABASE_TABLE = "addwebsites";
    private static final String DATABASE_TABLE_CREDENTIALS = "credentials";
    private static final String DATABASE_TABLE_BOOKMARKS = "bookmarks";
    private static final String DATABASE_TABLE_HISTORY = "history";
    private static final String DATABASE_TABLE_PATTERN = "pattern";
    private static final String DATABASE_TABLE_PATTERN_SITES = "pattern_sites";
    private static final String DATABASE_TABLE_PATTERN_ICONS = "icons";
    private static final String DATABASE_TABLE_PATTERN_SITES_DATA = "pattern_sites_data";
    private static final String DATABASE_TABLE_QUESTIONS_ANSWERS = "questions_answers";

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_CREATE_CATEGORY =
            "create table categories(_id integer primary key autoincrement, "+ "name text not null );";
       
    
    
    
    
    
    
    
    private static final String DATABASE_CREATE_WEBSITES =
        "create table addwebsites(_id integer primary key autoincrement, "+ "name text not null );";
   
    private static final String DATABASE_CREATE_CREDENTIALS =
            "create table credentials(_id integer primary key autoincrement, "+ "username text not null,"+"password text not null,"+"question text not null,"+"answer text not null );";
   
    private static final String DATABASE_CREATE_BOOKMARKS =
            "create table bookmarks(_id integer primary key autoincrement, "+ "title text not null,"+"url text not null );";
    private static final String DATABASE_CREATE_HISTORY =
            "create table history(_id integer primary key autoincrement, "+ "title text not null,"+"url text not null );";
   
    private static final String DATABASE_CREATE_PATTERN =
            "create table pattern(_id integer primary key autoincrement, "+ "pattern text not null );";
    
    private static final String DATABASE_CREATE_PATTERN_SITES =
            "create table pattern_sites(_id integer primary key autoincrement, "+ "patternsites text not null,"+"status text not null );";
    private static final String DATABASE_CREATE_ICONS =
            "create table icons(_id integer primary key autoincrement, "+ "path text not null,"+"status text not null,"+"category text not null,"+"name text not null);";
	private static String Table="pattern_sites";
	private static String colID="_id";
    private static final String DATABASE_CREATE_PATTERN_SITES_DATA =
            "create table pattern_sites_data(_id integer primary key autoincrement, "+ "type text not null,"+"value text not null,"+"name text not null,"+patternsites+" INTEGER NOT NULL,FOREIGN KEY ("+patternsites+") REFERENCES "+Table+" ("+colID+"));";
    private static final String DATABASE_CREATE_QUESTIONS_ANSWERS =
            "create table questions_answers(_id integer primary key autoincrement, "+"question text not null,"+ "answer text not null,"+"status text not null);";
    private final Context context; 
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
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
        	db.execSQL(DATABASE_CREATE_CATEGORY);
          /*db.execSQL(DATABASE_CREATE_WEBSITES);
            db.execSQL(DATABASE_CREATE_CREDENTIALS);
            db.execSQL(DATABASE_CREATE_BOOKMARKS);
            db.execSQL(DATABASE_CREATE_HISTORY);
           db.execSQL(DATABASE_CREATE_PATTERN);
            db.execSQL(DATABASE_CREATE_PATTERN_SITES);
             db.execSQL(DATABASE_CREATE_ICONS);
             db.execSQL(DATABASE_CREATE_PATTERN_SITES_DATA);
             db.execSQL(DATABASE_CREATE_QUESTIONS_ANSWERS);
            Log.d("create....!","create....!");*/
            /*db.inserticon("icon_mug","false", "animal","Beer"); 
     	   db.inserticon("icon_plane","false", "animal","Plane"); 
     	   db.inserticon("icon_car","false", "animal","Car");*/
            ContentValues initialValues = new ContentValues();
            
            //initialValues.put(KEY_NO, no);
            initialValues.put("path","btn_code_lock_default");
            initialValues.put("status", "true");
            initialValues.put("category", "animal");
            initialValues.put("name", "Default");
            
            db.insert(DATABASE_TABLE_PATTERN_ICONS, null, initialValues);
           /* initialValues.clear();
            initialValues.put("path","icon_plane");
            initialValues.put("status", "false");
            initialValues.put("category", "animal");
            initialValues.put("name", "Plane");
            
            db.insert(DATABASE_TABLE_PATTERN_ICONS, null, initialValues);
            
            initialValues.clear();
            initialValues.put("path","icon_car");
            initialValues.put("status", "false");
            initialValues.put("category", "animal");
            initialValues.put("name", "Car");
            
            db.insert(DATABASE_TABLE_PATTERN_ICONS, null, initialValues);
            */
            new_version_update_icons(db);
            
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
            Log.d("Upgrading database", "Upgrading database from version " + oldVersion+ " to "+ newVersion + ", which will destroy all old data");
        	
            db.execSQL(DATABASE_CREATE_CATEGORY);
           
            
            /* db.execSQL(DATABASE_CREATE_WEBSITES);
             db.execSQL(DATABASE_CREATE_CREDENTIALS);
             db.execSQL(DATABASE_CREATE_BOOKMARKS);
             db.execSQL(DATABASE_CREATE_HISTORY);
             db.execSQL(DATABASE_CREATE_PATTERN);
             db.execSQL(DATABASE_CREATE_PATTERN_SITES);
        	 db.execSQL(DATABASE_CREATE_ICONS);
        	db.execSQL(DATABASE_CREATE_PATTERN_SITES_DATA);
        	db.execSQL(DATABASE_CREATE_PATTERN_SITES_DATA);
        	 db.execSQL(DATABASE_CREATE_QUESTIONS_ANSWERS);*/
        	
            new_version_update_icons(db);
        	 
            //onCreate(db);
        }
    }    
    
   //---opens the database---
    public DBAdapter open() throws SQLException 
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
    public long insertTitle(String name) 
    {
        ContentValues initialValues = new ContentValues();
        
        //initialValues.put(KEY_NO, no);
        initialValues.put(KEY_NAME, name);
        //initialValues.put(KEY_DESG, desg);
       // initialValues.put(KEY_EMAIL, email);
       // initialValues.put(KEY_PHNO, phno);
        
        return db.insert(TABLE_NAME_CATEGORY, null, initialValues);
    }
    
    
    
    public long insertPatternSite(String site) 
    {
        ContentValues initialValues = new ContentValues();
        
        //initialValues.put(KEY_NO, no);
        initialValues.put(KEY_PATTERN_SITES, site);
        initialValues.put(KEY_PATTERN_STATUS, true);
       // initialValues.put(KEY_EMAIL, email);
       // initialValues.put(KEY_PHNO, phno);
        
        return db.insert(DATABASE_TABLE_PATTERN_SITES, null, initialValues);
    }
    
    
    
    
    
    public long insertBookmark(String title,String url) 
    {
        ContentValues initialValues = new ContentValues();
        
        //initialValues.put(KEY_NO, no);
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_URL, url);
        //initialValues.put(KEY_DESG, desg);
       // initialValues.put(KEY_EMAIL, email);
       // initialValues.put(KEY_PHNO, phno);
        
        return db.insert(DATABASE_TABLE_BOOKMARKS, null, initialValues);
    }
    
    
    public long insertHistory(String title,String url) 
    {
    	if(title==null)
    	{
    		title = "";
    	}
        ContentValues initialValues = new ContentValues();
        
        //initialValues.put(KEY_NO, no);
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_URL, url);
        //initialValues.put(KEY_DESG, desg);
       // initialValues.put(KEY_EMAIL, email);
       // initialValues.put(KEY_PHNO, phno);
        
        return db.insert(DATABASE_TABLE_HISTORY, null, initialValues);
    }
    
    
    
  //---insert a title into the database---
    public long insertusernamepassword(String name,String pass,String question,String answer) 
    {
        ContentValues initialValues = new ContentValues();
        
        //initialValues.put(KEY_NO, no);
        initialValues.put(KEY_USERNAME, name);
        initialValues.put(KEY_PASS, pass);
        initialValues.put(KEY_QUESTION, question);
        initialValues.put(KEY_ANSWER, answer);
        
        return db.insert(DATABASE_TABLE_CREDENTIALS, null, initialValues);
    }
    
    //insert into pattern
    
    public long insertpattern(String pattern) 
    {
        ContentValues initialValues = new ContentValues();
        
        //initialValues.put(KEY_NO, no);
        initialValues.put(KEY_PATTERN, pattern);
        //initialValues.put(KEY_PASS, pass);
       // initialValues.put(KEY_EMAIL, email);
       // initialValues.put(KEY_PHNO, phno);
        
        return db.insert(DATABASE_TABLE_PATTERN, null, initialValues);
    }
    
    public long inserticon(String path,String status,String category,String name) 
    {
        ContentValues initialValues = new ContentValues();
        
        //initialValues.put(KEY_NO, no);
        initialValues.put("path",path);
        initialValues.put("status", status);
        initialValues.put("category", category);
        initialValues.put("name",name );
        
        return db.insert(DATABASE_TABLE_PATTERN_ICONS, null, initialValues);
    }
    
    
    
    
    //---deletes a particular title---
  /*  public boolean deleteTitle(long rowId) 
    {
        //return db.delete(DATABASE_TABLE, KEY_NO +"=" + rowId, null) > 0;
    }*/

    //---retrieves all the titles---
    public Cursor getAllTitles() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		//KEY_NO, 
        		KEY_NAME,
        		//KEY_DESG,
               // KEY_EMAIL,KEY_PHNO
                }, 
                null, 
                null, 
                null, 
                null, 
                null);
    }
    
    public Cursor getAllpatternsites() 
    {
        return db.query(DATABASE_TABLE_PATTERN_SITES, new String[] {
        		KEY_ID, 
        		KEY_PATTERN_SITES,
        		//KEY_DESG,
               // KEY_EMAIL,KEY_PHNO
                }, 
                null, 
                null, 
                null, 
                null, 
                null);
    }
    
    
    
    
    public Cursor getAllBookmarks() 
    {
        return db.query(DATABASE_TABLE_BOOKMARKS, new String[] {
        		KEY_ID, 
        		KEY_URL,
        		//KEY_DESG,
               // KEY_EMAIL,KEY_PHNO
                }, 
                null, 
                null, 
                null, 
                null, 
                "_id DESC");
    }
    public Cursor getAllIcons() 
    {
        return db.query(DATABASE_TABLE_PATTERN_ICONS, new String[] {
        		KEY_ID, 
        		"path",
        		"status",
        		"category",
        		"name",
               // KEY_EMAIL,KEY_PHNO
                }, 
                null, 
                null, 
                null, 
                null, 
                null);
    }
	public Cursor getIcon() {
		return db.query(DATABASE_TABLE_PATTERN_ICONS, new String[] { KEY_ID,
				"path", "status", "category", "name",
		// KEY_EMAIL,KEY_PHNO
				}, "status" + "=" + "'true'", null, null, null, null,null);
	}

    public Cursor geturlforhistory(String n) throws SQLException 
    {
    	Log.d("n....!",""+n);
        Cursor mCursor =  db.query(true, DATABASE_TABLE_HISTORY, new String[] {
                		//KEY_ID,
                		KEY_URL, 
                		//KEY_DESG,
                		//KEY_EMAIL,KEY_PHNO
                		}, 
                		"_id" + "=" + n, 
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
    
    public Cursor getHistoryCursor(String n) throws SQLException
    {  
    
    	
     String sqlQuery = "";
     Cursor result = null;
        
     sqlQuery  = " SELECT _id" + ", url ";
     sqlQuery += " FROM history ";
     sqlQuery += " WHERE url LIKE " + DatabaseUtils.sqlEscapeString("%"+n.toString()+"%") ;
     sqlQuery += " GROUP BY url";
     sqlQuery += " ORDER BY url";
        
             
      result = db.rawQuery(sqlQuery, null);
     
      return result;
    }
    
    //get pattern first
    
    public Cursor getpatterncodefirst() throws SQLException 
    {
    	//Log.d("n....!",""+n);
       Cursor mCursor =  db.query(true, DATABASE_TABLE_PATTERN, new String[] {
                		//KEY_ID,
                		KEY_PATTERN, 
                		//KEY_DESG,
                		//KEY_EMAIL,KEY_PHNO
                		}, 
                		KEY_ID + "="+"'" + 1+"'", 
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
    
    
    
    
    //get pattern code
    
    public Cursor getpatterncode(String n) throws SQLException 
    {
    	Log.d("n....!",""+n);
       Cursor mCursor =  db.query(true, DATABASE_TABLE_PATTERN, new String[] {
                		//KEY_ID,
                		KEY_PATTERN, 
                		//KEY_DESG,
                		//KEY_EMAIL,KEY_PHNO
                		}, 
                		KEY_PATTERN + "=" + DatabaseUtils.sqlEscapeString(n.toString()), 
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
    
    
    
    
    
    //get pattern url
    
    public Cursor geturlforpattern(String n) throws SQLException 
    {
    	Log.d("n....!",""+n);
       Cursor mCursor =  db.query(true, DATABASE_TABLE_PATTERN_SITES, new String[] {
                		//KEY_ID,
                		KEY_PATTERN_SITES, 
                		KEY_PATTERN_STATUS,
                		//KEY_EMAIL,KEY_PHNO
                		}, 
                		KEY_PATTERN_SITES + "=" + DatabaseUtils.sqlEscapeString(n.toString()), 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
      // 01-16 18:11:58.164: E/AndroidRuntime(14634): android.database.sqlite.SQLiteException: unrecognized token: ":": , while compiling: SELECT DISTINCT patternsites FROM pattern_sites WHERE patternsitesINhttp://www.google.co.in/
    //   01-16 18:13:53.814: E/AndroidRuntime(15356): android.database.sqlite.SQLiteException: unrecognized token: ":": , while compiling: SELECT DISTINCT patternsites FROM pattern_sites WHERE patternsites=http://www.google.co.in/


    	
    	 // Cursor mCursor = db.rawQuery("select * from "+DATABASE_TABLE_PATTERN_SITES+" where " + n.toString(), null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
 //get pattern url
    
    public Cursor geturlforpatternwithid(String n) throws SQLException 
    {
    	Log.d("n....!",""+n);
       Cursor mCursor =  db.query(true, DATABASE_TABLE_PATTERN_SITES, new String[] {
                		//KEY_ID,
                		KEY_PATTERN_SITES, 
                		//KEY_PATTERN_STATUS,
                		//KEY_EMAIL,KEY_PHNO
                		}, 
                		"_id" + "="+"'" + n.toString()+"'", 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
      // 01-16 18:11:58.164: E/AndroidRuntime(14634): android.database.sqlite.SQLiteException: unrecognized token: ":": , while compiling: SELECT DISTINCT patternsites FROM pattern_sites WHERE patternsitesINhttp://www.google.co.in/
    //   01-16 18:13:53.814: E/AndroidRuntime(15356): android.database.sqlite.SQLiteException: unrecognized token: ":": , while compiling: SELECT DISTINCT patternsites FROM pattern_sites WHERE patternsites=http://www.google.co.in/


    	
    	 // Cursor mCursor = db.rawQuery("select * from "+DATABASE_TABLE_PATTERN_SITES+" where " + n.toString(), null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor geturlforbookmark(String n) throws SQLException 
    {
        Cursor mCursor =  db.query(true, DATABASE_TABLE_BOOKMARKS, new String[] {
                		//KEY_ID,
                		KEY_URL, 
                		//KEY_DESG,
                		//KEY_EMAIL,KEY_PHNO
                		}, 
                		"_id" + "=" + n, 
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
    public Cursor getAllHistory() 
    {
        return db.query(DATABASE_TABLE_HISTORY, new String[] {
        		KEY_ID, 
        		KEY_URL,
        		//KEY_DESG,
               // KEY_EMAIL,KEY_PHNO
                }, 
                null, 
                null, 
                null, 
                null, 
                "_id DESC");
    }
    
    
    
//get all credentials like username,password
    public Cursor getAllCredentials() 
    {
        return db.query(DATABASE_TABLE_CREDENTIALS, new String[] {
        		//KEY_NO, 
        		KEY_USERNAME,
        		KEY_PASS,
               // KEY_EMAIL,KEY_PHNO
                }, 
                null, 
                null, 
                null, 
                null, 
                null);
    }
    
    public Cursor getQuestionAnswerCredentials() 
    {
        return db.query(DATABASE_TABLE_CREDENTIALS, new String[] {
        		//KEY_NO, 
        		KEY_QUESTION,
        		KEY_ANSWER,
               // KEY_EMAIL,KEY_PHNO
                }, 
                null, 
                null, 
                null, 
                null, 
                null);
    }    
    
    
    
    //---retrieves a particular title---
    public Cursor getTitle(String n) throws SQLException 
    {
        Cursor mCursor =  db.query(true, DATABASE_TABLE, new String[] {
                		//KEY_NO,
                		KEY_NAME, 
                		//KEY_DESG,
                		//KEY_EMAIL,KEY_PHNO
                		}, 
                		KEY_NAME + "=" + n, 
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
    

    
    
    
 
    public boolean updatePatternStatus(String url) 
    {
    	Log.d("dsadsa","fdafdsf..!");
        ContentValues args = new ContentValues();
       // args.put("_id", no);
       // args.put(KEY_USERNAME, username);
        args.put(KEY_PATTERN_STATUS, 1);
      //  args.put(KEY_EMAIL, email);
      //  args.put(KEY_PHNO, phno);
        return db.update(DATABASE_TABLE_PATTERN_SITES, args,KEY_PATTERN_SITES + "=" + DatabaseUtils.sqlEscapeString(url) , null) > 0;
      
    }

    //---updates credentials---
    public boolean updateCred(long no, String username,String pass) 
    {
    	Log.d("dsadsa","fdafdsf..!");
        ContentValues args = new ContentValues();
        args.put("_id", no);
        args.put(KEY_USERNAME, username);
        args.put(KEY_PASS, pass);
      //  args.put(KEY_EMAIL, email);
      //  args.put(KEY_PHNO, phno);
        return db.update(DATABASE_TABLE_CREDENTIALS, args,"_id" + "=" + no , null) > 0;
      
    }

	public boolean updateIcon_selected(Integer id) {
		ContentValues args = new ContentValues();

		args.put(KEY_PATTERN_STATUS, "false");

		db.update(DATABASE_TABLE_PATTERN_ICONS, args, null, null);

		ContentValues args1 = new ContentValues();

		args1.put(KEY_PATTERN_STATUS, "true");

		return db.update(DATABASE_TABLE_PATTERN_ICONS, args1, "_id" + "=" + id,
				null) > 0;
	}

	public Cursor getphoneno(String ph) {
		Cursor mCursor =  db.query(true, DATABASE_TABLE, new String[] {
        		//KEY_NO,
        		KEY_NAME, 
        		//KEY_DESG,
        	//	KEY_EMAIL,KEY_PHNO
        		}, 
        		KEY_NAME + "=" + ph, 
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




	public boolean delete_url_from_bookmark(String a) {
		return db.delete(DATABASE_TABLE_BOOKMARKS, KEY_ID +" "+ "IN" + "("+a+")", null) > 0;

		
	}

	public boolean delete_all_url_from_bookmark() {
		// TODO Auto-generated method stub
		return db.delete(DATABASE_TABLE_BOOKMARKS, null, null) > 0;

	}

	public boolean delete_url_from_history(String a) {
		return db.delete(DATABASE_TABLE_HISTORY, KEY_ID +" "+ "IN" + "("+a+")", null) > 0;

		
	}

	public boolean delete_all_url_from_history() {
		// TODO Auto-generated method stub
		return db.delete(DATABASE_TABLE_HISTORY, null, null) > 0;

	}
	
	public boolean delete_url_from_currentsite(String a) {
		db.delete(DATABASE_TABLE_PATTERN_SITES_DATA, "pattern_sites_id "+ "IN" + "("+a+")", null);
		return db.delete(DATABASE_TABLE_PATTERN_SITES, KEY_ID +" "+ "IN" + "("+a+")", null) > 0;

		
	}

	public boolean delete_all_url_from_currentsite() {
		// TODO Auto-generated method stub
		db.delete(DATABASE_TABLE_PATTERN_SITES_DATA, null, null);
		return db.delete(DATABASE_TABLE_PATTERN_SITES, null, null) > 0;

	}


/*	public long insertSiteValues(String name, String type, String value,String id) throws Exception {
ContentValues initialValues = new ContentValues();

		String encript_value = SimpleCrypto.encrypt("swipein", value);
        
        //initialValues.put(KEY_NO, no);
        initialValues.put("type", type);
        initialValues.put("value", encript_value);
        initialValues.put("name", name);
        initialValues.put("pattern_sites_id", id);
        
        return db.insert(DATABASE_TABLE_PATTERN_SITES_DATA, null, initialValues);
		
	}*/

	public Cursor getpatternsite(String add_site) {
		if(add_site==null)
		{
			add_site = "";
		}
		Cursor mCursor =  db.query(true, DATABASE_TABLE_PATTERN_SITES, new String[] {
        		//KEY_NO,
        		KEY_ID, 
        		//KEY_DESG,
        	//	KEY_EMAIL,KEY_PHNO
        		}, 
        		KEY_PATTERN_SITES + "=" +DatabaseUtils.sqlEscapeString(add_site), 
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

	public Cursor getpatternsitevalues(String id)
	{
		Cursor mCursora =  db.query(true, DATABASE_TABLE_PATTERN_SITES_DATA, new String[] {
        		//KEY_NO,
        		KEY_NAME, 
        		KEY_VALUE,
        	//	KEY_EMAIL,KEY_PHNO
        		}, 
        		KEY_PATTERN_SITES_FOR_DATAID + "=" +"'"+id+"'", 
        		null,
        		null, 
        		null, 
        		null, 
        		null);
		return mCursora;

//return mCursora;
	}

	public boolean updatepattern(String p) {
		Log.d("dsadsa","fdafdsf..!");
        ContentValues args = new ContentValues();
        args.put("pattern",p );
        
      //  args.put(KEY_EMAIL, email);
      //  args.put(KEY_PHNO, phno);
        return db.update(DATABASE_TABLE_PATTERN, args,"_id" + "=" + 1 , null) > 0;
      
		
	}
	
/*	public boolean updateSiteValues(String name, String value,String id) throws Exception {
		Log.d("dsadsa","fdafdsf..!");
        ContentValues args = new ContentValues();
    	String encript_value = SimpleCrypto.encrypt("swipein", value);
        args.put("value",encript_value );
        
      //  args.put(KEY_EMAIL, email);
      //  args.put(KEY_PHNO, phno);
        return db.update(DATABASE_TABLE_PATTERN_SITES_DATA, args,"name=" + DatabaseUtils.sqlEscapeString(name) +" and pattern_sites_id='"+id+"'" , null) > 0;
      
		
	}*/
	
	private static void add_new_icon(SQLiteDatabase db, String file_name, String image_name)
	{
		ContentValues initialValues = new ContentValues();
        
        //initialValues.put(KEY_NO, no);
        initialValues.put("path",file_name);
        initialValues.put("status", "false");
        initialValues.put("category", "animal");
        initialValues.put("name", image_name);
        
        db.insert(DATABASE_TABLE_PATTERN_ICONS, null, initialValues);
	}

	
	private static void new_version_update_icons(SQLiteDatabase db)
	{
		
		add_new_icon(db, "icon_mug", "Beer");
   	 	
		add_new_icon(db, "icon_plane", "Plane");
   	 	
   	 	add_new_icon(db, "icon_car", "Car");
	 	
	}


}

