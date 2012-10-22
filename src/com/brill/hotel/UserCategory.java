package com.brill.hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class UserCategory extends Activity  {
	DataBase data;
	DataMenuImage dm;
	String cat,id;
	ListView contacts;
	 Dialog d;
	 String Logo;
	 DataLogo dl;
	public static String str;
	 public DataAdapter notes;
	  public static String namcat;
	 ArrayList<Constructor> DisplayData = new ArrayList<Constructor>();
	 public static List<String>catList= new ArrayList<String>();
	 public static List<String>categoryList= new ArrayList<String>();
	 public static List<String>nameList= new ArrayList<String>();
		HashMap<String, String>hashList = new HashMap<String, String>();
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.cat_list);
	  data=new DataBase(this);
	  
	 dm=new DataMenuImage(this);
	 dl=new DataLogo(this);
	  notes=new DataAdapter(this,R.layout.button, DisplayData);
	  contacts=(ListView)findViewById(R.id.list);
	  try{
		  contacts.setAdapter(notes);
	  }catch(NullPointerException e){
		  
	  }
	 
	  data.open();
	  Cursor getdetails=data.getlistitems();
	  
	  data.close();
	  if(getdetails.moveToFirst())
	  {
	   do{
		 
	    cat=getdetails.getString(1);
	   catList.add(cat);
       System.out.println("name::::::::::"+cat);
      
       DisplayData.add(new Constructor(cat));
	   }while(getdetails.moveToNext());
	  }

	 getdetails.close();
	
/*	 for(int c=0;c<catList.size();c++){
		 String cattitle=catList.get(c);
		 Log.d("category",""+cattitle);
		 dm.open();
		  Cursor get=dm.getlistcat(cattitle);
		  Log.d("category",""+cattitle);
		  
		  if(get.moveToFirst())
		  {
		   do{
			  
		 String   Name=get.getString(1);
		  
		  
		  Log.d("selected Name",""+Name);
		  nameList.add(Name);
		  Log.d("nameList",""+nameList.size());
		   }while(get.moveToNext());
		  }

		 get.close();
		 dm.close();
		 hashList.put(catList.get(c), nameList.get(c));
		 Log.d("hash",""+ hashList.put(catList.get(c), nameList.get(c)));
		 Log.d("hash",""+ hashList);
	 }*/
	 dl.open();
	  Cursor getlogo=dl.getlistitems();
	  
	  dl.close();
	  
	  
	  if(getlogo.moveToFirst())
	  {
	   do{
		 
		   Logo=getlogo.getString(0);
	   
       System.out.println("name::::::::::"+Logo);
      
       
	   }while(getlogo.moveToNext());
	  }
	 if(Logo!=null){
	/* Log.d("AddLogo.LogoPath.get(0)",""+AddLogo.LogoPath.get(0));
	 String pathName = AddLogo.LogoPath.get(0);
	 Log.d("pathName",""+pathName);*/
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeFile(Logo);
        BitmapDrawable bd = new BitmapDrawable(res, bitmap);
        View view = findViewById(R.id.usercat);
        view.setBackgroundDrawable(bd);
	 }
	 Button list_of_items=(Button)findViewById(R.id.list_of_items);
	 list_of_items.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent in=new Intent(getApplicationContext(),OrderList.class);
			startActivity(in);
			
		}
	});
	 }
	 
	 public class DataAdapter extends BaseAdapter{
	     
	     private Context context;
	        private List<Constructor> buttonList;
	     //private int rowResID;
	     public DataAdapter(Context context, int rowResID,
	       List<Constructor> buttonList ) { 
	       this.context = context;
	       //this.rowResID = rowResID;
	       this.buttonList = buttonList;
	     }
	        public int getCount() {                        
	            return buttonList.size();
	        }
	        public Object getItem(int position) {     
	            return buttonList.get(position);
	        }
	        public long getItemId(int position) {  
	            return position;
	        }
	        public View getView(final int position, View convertView, ViewGroup parent) { 
	        	
	            final Constructor row = buttonList.get(position);
	            Log.d("positions",""+buttonList.get(position));
	            /*Cursor cursor = (Cursor) ((AdapterView<ListAdapter>) convertView).getItemAtPosition(position);
	            final String item_content = cursor.getString(cursor.getColumnIndex(DataBase.KEY_CAT ));
	            Log.d("item",""+item_content);*/
	            Log.d("0",""+buttonList.get(0));
	            LayoutInflater inflater = LayoutInflater.from( context );
	            View v = inflater.inflate( R.layout.button, parent, false );
	            
	            final Button nametext = (Button)v.findViewById( R.id.cattext);
	       
	     
	        nametext.setText(row.Cat());
	       
	        
	  nametext.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
				
					 final int i=1+contacts.getPositionForView(v);
					 Log.d("i",""+i);
				 namcat=row.Cat();
					 Log.d("namcat",""+namcat);
					 
					 Runnable showWaitDialog = new Runnable() {

							public void run() {
								while (namcat== null) {
																}
								// After receiving first GPS Fix dismiss the Progress Dialog
								d.dismiss();
								Log.d("dismiss","dismiss");
								// find.setEnabled(true);
							}
						};
						d = ProgressDialog.show(UserCategory.this, "Please wait...",
				 				"Please wait ...",
				 				true);
				 		d.setCancelable(true);
				 		Thread t = new Thread(showWaitDialog);
				 		t.start();
				 		Log.d("start","start");
				 		 dm.open();
				 		  Cursor getitems=dm.getlistcat(namcat);
				 		  Log.d("category",""+ namcat);
				 		  //categoryList.add(cat);
				 		  dm.close();
				 		if(getitems.getCount()==0){
				 			Toast.makeText(UserCategory.this,namcat+"  "+"Items are not avilable", Toast.LENGTH_LONG).show();
				 		}
				 		else{
				 		Intent in=new Intent(getApplicationContext(),UsermenuItems.class);    
					     startActivity(in);
				 		}
/*					 Context x=v.getContext();
					 Log.d("x",""+x);
			 		d=new Dialog(x);
			 		d.setContentView(R.layout.modifiedcat);
			 		d.setTitle("ENTER MODIFIED DATA");
			 		d.show();
			 		final EditText t3=(EditText)d.findViewById(R.id.edit);
			 		t3.setText(row.Cat());
			 		Button ok=(Button)d.findViewById(R.id.okbutton);
			 		Button del=(Button)d.findViewById(R.id.delete);
			 		ok.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						 str=t3.getText().toString();
						 Log.d("str",""+str);
						System.out.println("name::::::"+str);
						nametext.setText(str);
						data.open();
						data.updateTitle(i, str);
						data.close();
						d.cancel();
						}
					});
			 		del.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						 str=t3.getText().toString();
						 Log.d("str",""+str);
						System.out.println("name::::::"+str);
						nametext.setText(str);
						data.open();
						data.deleteTitle(i);
						data.close();
						d.cancel();
						}
					});
				*/
					 }
			});
	  
	  
	  
	        return v;
	        } 
	    }
	 public boolean onCreateOptionsMenu(Menu menu) {
	    	MenuInflater inflater = getMenuInflater();
	    	inflater.inflate(R.menu.options_cat, menu);
	    	return true;
	    }
	    
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
	    	case R.id.next:
	    		Intent gal=new Intent(getApplicationContext(),Showimages.class);    
			     startActivity(gal);
			     finish();
	    		/*Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.next) + " menu option",
	            		Toast.LENGTH_SHORT).show();*/
	    		return true;
	    	case R.id.previous:
	    		Intent in=new Intent(getApplicationContext(),Contacts.class);    
			     startActivity(in);
			     finish();
	    		/*Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.previous) + " menu option",
	            		Toast.LENGTH_SHORT).show();*/
	    		return true;
	    	/*case R.id.list:
	    		Intent menu=new Intent(getApplicationContext(),UserMenu.class);    
			     startActivity(menu);
	    		Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.list) + " menu option",
	            		Toast.LENGTH_SHORT).show();
	    		return true;*/
	    	default:
	    		return super.onOptionsItemSelected(item);
	    	}
	    }
	  /*@Override
	    public void onBackPressed() {
	    	Log.d("back","back");
	           // Do as you please
	    	 Intent in=new Intent(getApplicationContext(),UserMenu.class);    
		     startActivity(in); 
	    }*/
	}
