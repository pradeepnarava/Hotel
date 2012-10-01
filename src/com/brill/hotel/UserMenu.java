package com.brill.hotel;

import java.util.ArrayList;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class UserMenu extends Activity {
	 DataBase db;
	 DataUser data;
	 DataAdmin da;
		Bitmap b;
		DataLogo dl;
		 Dialog d;
		 String Logo;
		 DataMenuImage dm;
		  RatingBar ratingbar;
          TextView ratings;
          static ArrayList<Object> myArr = new ArrayList<Object>();
          String Name,Price,Categ;
          String adname,adpass;
		public static String ctg,Cate;
		CharSequence category;
		public static String phone,mobile,email,address;
		Button menu,contact,gallery,out;
		String nv="Non_Vag";
		public ListView lv;
		 public static List<String>contactsList= new ArrayList<String>();
		 public static List<String>stringList= new ArrayList<String>();
		 public static List<String>List= new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usermenu);
		stringList.clear();
		contactsList.clear();
		db=new DataBase(this);
		data=new DataUser(this);
		dm=new DataMenuImage(this);
		da=new DataAdmin(this);
		dl=new DataLogo(this);
		menu=(Button)findViewById(R.id.menu);
		contact=(Button)findViewById(R.id.contact);
		gallery=(Button)findViewById(R.id.gallery);
		out=(Button)findViewById(R.id.logout);
		 ratings = (TextView) findViewById(R.id.rating);
         ratingbar = (RatingBar) findViewById(R.id.feedback);
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
    		 /*Log.d("AddLogo.LogoPath.get(0)",""+AddLogo.LogoPath.get(0));
    		 String pathName = AddLogo.LogoPath.get(0);
    		 Log.d("pathName",""+pathName);*/
    		
    	        Resources res = getResources();
    	        Bitmap bitmap = BitmapFactory.decodeFile(Logo);
    	        BitmapDrawable bd = new BitmapDrawable(res, bitmap);
    	        View view = findViewById(R.id.usermenu);
    	        view.setBackgroundDrawable(bd);
    		 }
         db.open();
			Cursor getdetails=db.getlistitems();
			Log.d("getdetails",""+getdetails);
			Log.d("no of rows",""+getdetails.getCount());
			Log.d("getdetails.moveToFirst()",""+getdetails.moveToFirst());
			Log.d("db.getlistitems()",""+db.getlistitems());
			db.close();
			
			if(getdetails.moveToFirst())
			  {
			   do{
				
				   ctg=getdetails.getString(1);
			    Log.d("categories",""+ctg);
			    
		        System.out.println("cat::::::::::"+ctg);
		        stringList.add(ctg);
		        Log.d("stringList",""+ stringList);
		      
				
			   }while(getdetails.moveToNext());
			   getdetails.close();
			  
			}
         
			 /*if(getcontacts.moveToFirst())
			  {
			   do{
				
				   phone=getcontacts.getString(0);
				  mobile=getcontacts.getString(1);
				   email=getcontacts.getString(2);
				   address=getcontacts.getString(3);
			    Log.d(" phone",""+ phone);
			    Log.d("  email",""+  email);
			    
		        System.out.println("cat::::::::::"+ctg);
		        contactsList.add(phone+","+mobile+","+email+","+address);
		        Log.d("contactsList",""+ contactsList);
		      
				
			   }while(getcontacts.moveToNext());
			   getcontacts.close();
			}*/
		
		
		menu.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					
					
					if( ctg==null){
						 Toast.makeText(UserMenu.this,"Please Create Categories", Toast.LENGTH_LONG).show();
					 }
				
					else{
					Runnable showWaitDialog = new Runnable() {

						public void run() {
					
					 
								while (ctg== null) {
							}
								Intent add_menu = new Intent(UserMenu.this,
										UserCategory.class);
					    	  startActivity(add_menu);
								// After receiving first GPS Fix dismiss the Progress Dialog
								d.dismiss();
								Log.d("dismiss","dismiss");
								// find.setEnabled(true);
							}
						};
						d = ProgressDialog.show(UserMenu.this, "Please wait...",
				 				"Please wait ...",
				 				true);
				 		d.setCancelable(true);
				 		Thread t = new Thread(showWaitDialog);
				 		t.start();
				 		Log.d("start","start");
					
						
					}
					
					/*final CharSequence[] items = stringList.toArray(new CharSequence[ stringList.size()]);;
					Log.d("items",""+items);
					AlertDialog.Builder builder = new AlertDialog.Builder(UserMenu.this);
					builder.setTitle("All Categories");
					builder.setIcon(R.drawable.ic_launcher);
					builder.setItems(items, new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog, int item) {
					       
					    	//Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
					    	 category.setText(items[item]);
							    Log.d("select category",""+category);
					    	//category
					    	Log.d("items[item]",""+items[item]);
					    	category=items[item];
					    	  Log.d("select category",""+category);
					    	  Cate=category.toString();
					    	  Intent add_menu = new Intent(UserMenu.this,
										UsermenuItems.class);
					    	  startActivity(add_menu);
					    }
					});
					AlertDialog alert = builder.create();

					alert.show();*/
		          
				}
			});
		
		contact.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				data.open();
				Cursor getcontacts=data.getlistitems();
				data.close();
				if(getcontacts.getCount()>0){
				Intent cont=new  Intent(UserMenu.this,
						Contacts.class);
		    	  startActivity(cont);
				}
				else{
					 Toast.makeText(UserMenu.this,"Contact detailes are not available", Toast.LENGTH_LONG).show();

				}
				
	          
			}
		});
		
		 ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener()
		 {
		                    public void onRatingChanged(RatingBar ratingBar, float rating,
		                                                boolean fromUser)
		 {
		                          // TODO Auto-generated method stub
		                                ratings.setText("FeedBack is " + rating);
		      }
		    });
		 gallery.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					
					Intent gal=new  Intent(UserMenu.this,
							Showimages.class);
			    	  startActivity(gal);
		          
				}
			});
		 
		 
		 out.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					 da.open();
					  Cursor getadmin=da.getlistitems();
					  da.close();

					Context x=v.getContext();
					 Log.d("x",""+x);
			 		d=new Dialog(x);
			 		d.setContentView(R.layout.logout);
			 		d.setTitle("ENTER ADMIN LOGIN DETAILS");
			 		d.show();
			 		final EditText t3=(EditText)d.findViewById(R.id.name);
			 		final EditText t2=(EditText)d.findViewById(R.id.pass);
			 		Button ok=(Button)d.findViewById(R.id.okbutton);
			 		Button can=(Button)d.findViewById(R.id.cancel);
			 		ok.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						adname=t3.getText().toString();
						 adpass=t2.getText().toString();
						if(adname.length() > 0 && adpass.length() >0)
						{
							
							
							da.open();
							if(da.Login(adname,adpass))
							{
								Intent menu = new Intent(UserMenu.this,
										User.class);
						    	startActivity(menu);
								
								
						    	Toast.makeText(UserMenu.this,"Success", Toast.LENGTH_LONG).show();
						    	
						    	
						    	d.cancel();
							
							
							}
							else{
								Toast.makeText(UserMenu.this,"please check admin login details", Toast.LENGTH_LONG).show();
							t3.setText("");
							t2.setText("");
							}
							da.close();
							
						}
						else{
							Toast.makeText(UserMenu.this,"please enter details", Toast.LENGTH_LONG).show();
						}
						
						}
					});
			 		can.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						 
						data.close();
						d.cancel();
						}
					});
					
					/*Intent logout=new  Intent(UserMenu.this,
							User.class);
			    	  startActivity(logout);*/
		          
				}
			});
		
		 
	}
	
	
/*	 public boolean onCreateOptionsMenu(Menu menu) {
	    	MenuInflater inflater = getMenuInflater();
	    	inflater.inflate(R.menu.options_menu, menu);
	    	return true;
	    }
	    
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
	    	case R.id.next:
	    		Intent gal=new Intent(getApplicationContext(),UsermenuItems.class);    
			     startActivity(gal);
	    		Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.next) + " menu option",
	            		Toast.LENGTH_SHORT).show();
	    		return true;
	    	case R.id.previous:
	    		Intent in=new Intent(getApplicationContext(),UserCategory.class);    
			     startActivity(in);
	    		Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.previous) + " menu option",
	            		Toast.LENGTH_SHORT).show();
	    		return true;
	    	case R.id.list:
	    		Intent menu=new Intent(getApplicationContext(),UserMenu.class);    
			     startActivity(menu);
	    		Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.list) + " menu option",
	            		Toast.LENGTH_SHORT).show();
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
	    	}
	    } */ 
	  @Override
	    public void onBackPressed() {
	    	Log.d("back","back");
	           // Do as you please
	    	 /*Intent in=new Intent(getApplicationContext(),Order.class);    
		     startActivity(in);*/ 
	    }
}
