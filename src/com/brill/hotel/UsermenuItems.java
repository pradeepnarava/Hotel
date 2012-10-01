package com.brill.hotel;

import java.io.ByteArrayInputStream;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class UsermenuItems extends Activity  {
	DataMenuImage data;
	String Name,Price,Category,Des,Categ,RowId,Image;
	ListView menulist;
	DataLogo dl;
	 public DataAdapter menu;
	 String Logo;
		private Dialog dialog;
		HashMap<String, String>hashList = new HashMap<String, String>();
	 static ArrayList<Object> myArr = new ArrayList<Object>();
	 public static List<String>menuList= new ArrayList<String>();
	 public static List<String>desList= new ArrayList<String>();
	 public static List<String>RowList= new ArrayList<String>();
	 public static List<String>ImageList= new ArrayList<String>();
	 ArrayList<Constructorusermenu> DisplayData = new ArrayList<Constructorusermenu>();

	 public static int i;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.list_of_cat);
	  Categ=UserCategory.namcat;
	  Log.d("category",""+Categ);
	  data=new DataMenuImage(this);
	  dl=new DataLogo(this);
	  menu=new DataAdapter(this,R.layout.useritems, DisplayData);
	  menulist=(ListView)findViewById(R.id.list);
	  menulist.setAdapter(menu);
	  menuList.clear();
	  desList.clear();
	  RowList.clear();
	  ImageList.clear();
	  
	  
	  data.open();
	  Cursor getdetails=data.getlistcat(Categ);
	  Log.d("category",""+Categ);
	
	  if(getdetails.moveToFirst())
	  {
	   do{
		  
		   RowId=getdetails.getString(0);
	    Name=getdetails.getString(1);
	  Price=getdetails.getString(2);
	// Category=getdetails.getString(3);
	  Des=getdetails.getString(4);
	 Image=getdetails.getString(5);
	 
	
	  Log.d("selected RowId",""+RowId);
	  Log.d("selected Name",""+Name);
	  Log.d("selected price",""+Price);
	  Log.d("selected Category",""+Category);
	  Log.d("selected des",""+Des);
/*	  myArr.add((Object)bitmap);
*/	  Log.d("image array size",""+myArr.size());
        System.out.println("name::::::::::"+Name);
       menuList.add(Name+":"+Price);
         Log.d("menulist",""+menuList);
         desList.add(Des);
         Log.d("desList",""+desList);
         RowList.add(RowId);
         Log.d(" RowList",""+ RowList);
         ImageList.add(Image);
         Log.d(" ImageList",""+ ImageList);
        DisplayData.add(new Constructorusermenu(Name));
		   /*}
		   else{
				 Toast.makeText(UsermenuItems.this,Categ+"Not avilable", Toast.LENGTH_LONG).show();
			 }*/
	   }while(getdetails.moveToNext());
	  }

	 getdetails.close();
	
	 data.close();
	 
	if(getdetails.getCount()==0){
		Toast.makeText(UsermenuItems.this,Categ+"  "+"Items are not avilable", Toast.LENGTH_LONG).show();
	}
	
	menulist.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			i=menulist.getPositionForView(view);
			
			 Log.d("i",""+i);
			Runnable showWaitDialog = new Runnable() {

       			public void run() {
       				while (Name== null) {
       					// Wait for first GPS Fix (do nothing until loc != null)
       				}
       				// After receiving first GPS Fix dismiss the Progress Dialog
       				dialog.dismiss();
       				// find.setEnabled(true);
       			}
       		};

       		// Create a Dialog to let the User know that we're waiting for a GPS Fix
       		dialog = ProgressDialog.show(UsermenuItems.this, "Please wait...",
       				"Order processing",
       				true);
       		dialog.setCancelable(true);
       		Thread t = new Thread(showWaitDialog);
       		t.start();

			
			
		 		Intent in=new Intent(getApplicationContext(),Order.class);    
			     startActivity(in);
		}
		});
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
        View view = findViewById(R.id.usercatlist);
        view.setBackgroundDrawable(bd);
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
	    		Intent menu=new Intent(getApplicationContext(),UserMenu.class);    
			     startActivity(menu);
	    		/*Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.next) + " menu option",
	            		Toast.LENGTH_SHORT).show();*/
	    		return true;
	    	case R.id.previous:
	    		Intent in=new Intent(getApplicationContext(),UserCategory.class);    
			     startActivity(in);
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
 public class DataAdapter extends BaseAdapter{
	     
	     private Context context;
	        private List<Constructorusermenu> buttonList;
	     //private int rowResID;
	     public DataAdapter(Context context, int rowResID,
	       List<Constructorusermenu> buttonList ) { 
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
	         
	            final Constructorusermenu row = buttonList.get(position);
	            LayoutInflater inflater = LayoutInflater.from( context );
	            View v = inflater.inflate( R.layout.useritems, parent, false );
	            
	            final Button nametext = (Button)v.findViewById( R.id.nametext);
	           
	            //final TextView pricetext = (TextView)v.findViewById( R.id.pricetext);
	            //final TextView categorytext = (TextView)v.findViewById( R.id.categorytext);
	        nametext.setText(row.Name());
	        //pricetext .setText(row.Price());
	       //categorytext .setText(row.Category());
	    
	       nametext.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					i=menulist.getPositionForView(v);
					
					 Log.d("i",""+i);
					
				 		Intent in=new Intent(getApplicationContext(),Order.class);    
					     startActivity(in);
					/*i=menulist.getPositionForView(v);
					
					 Log.d("i",""+i);
					 Runnable showWaitDialog = new Runnable() {

							public void run() {
								while (nametext == null) {
																	}
								// After receiving first GPS Fix dismiss the Progress Dialog
								dialog.dismiss();
								Log.d("dismiss","dismiss");
								// find.setEnabled(true);
							}
						};
						dialog = ProgressDialog.show(UsermenuItems.this, "Please wait...",
				 				"Please wait...",
				 				true);
				 		dialog.setCancelable(true);
				 		Thread t = new Thread(showWaitDialog);
				 		t.start();
				 		Log.d("start","start");
				 		Intent in=new Intent(getApplicationContext(),Order.class);    
					     startActivity(in);*/
					 
				     /* Context x=v.getContext();
					 Log.d("x",""+x);
			 		d=new Dialog(x);
			 		d.setContentView(R.layout.modifiedcat);
			 		d.setTitle("ENTER MODIFIED DATA");
			 		d.show();
			 		final EditText t3=(EditText)d.findViewById(R.id.edit);
			 		t3.setText(row.Name());
			 		Button ok=(Button)d.findViewById(R.id.okbutton);
			 		Button del=(Button)d.findViewById(R.id.delete);
			 		ok.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						Name=t3.getText().toString();
						
						System.out.println("name::::::"+Name);
						nametext.setText(Name);
						data.open();
						data.updateTitle(i, Name);
						data.close();
						d.cancel();
						}
					});
			 		del.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						 Name=t3.getText().toString();
						
						System.out.println("name::::::"+Name);
						nametext.setText(Name);
						data.open();
						data.deleteTitle(i);
						data.close();
						d.cancel();
						}
					});*/
				}
			});
	
	      
	        return v;
	        } 
	    }
 
 //@Override
 /*public void onBackPressed() {
 	Log.d("back","back");
        // Do as you please
 	 Intent in=new Intent(getApplicationContext(),UserCategory.class);    
	     startActivity(in); 
 }*/
}