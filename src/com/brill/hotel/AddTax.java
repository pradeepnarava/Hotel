package com.brill.hotel;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddTax extends Activity{
	EditText tax;
	Button okay;
	DataTax data;
	public static int i,p;
	 Dialog d;
String Tax,ListTax,row_id;
ListView contacts;
TextView et;
 String str;
 JSONObject json;
 UserFunctions userfunctions;
public DataAdapter notes;
ArrayList<ConstructorTax> DisplayData = new ArrayList<ConstructorTax>();
public static List<String>taxList= new ArrayList<String>();
public static List<String>rowList= new ArrayList<String>();
	@TargetApi(9)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_tax);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		DisplayData.clear();
		data=new DataTax(this);
	 userfunctions=new UserFunctions();
		et=(TextView)findViewById(R.id.entertax);
		tax=(EditText)findViewById(R.id.tax);
		okay=(Button)findViewById(R.id.ok);
		notes=new DataAdapter(this,R.layout.button, DisplayData);
		  contacts=(ListView)findViewById(R.id.list);
		 try{
			  contacts.setAdapter(notes);
		  }catch(NullPointerException e){
			  
		  }
		 TaxsevedData();
		  
		 okay.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
              Tax=tax.getText().toString();
              taxList.clear();
			data.open();
			Cursor getdetails=data.getlistitems();
			data.close();
			 if(getdetails.moveToFirst())
			  {
			   do{
			 String ccat=getdetails.getString(getdetails.getColumnIndex("tax"));

			   Log.d("ccat",""+ccat);
		        //System.out.println("name::::::::::"+cat);
		       taxList.add(ccat);
		        
			   }while(getdetails.moveToNext());
			  }
			 Log.d(" catList",""+ taxList);
			 
			 int index = taxList.indexOf(Tax);
			    if(index == -1){
			    	data.open();
			    	data.insertval(1, Tax);
			    	data.close();
			    	rowList.clear();
			    	contacts.setAdapter(notes);
			    	TaxsevedData();
			    	 json=userfunctions.TaxApi(Tax);
			    	Log.d("json",""+json);
			    	Toast.makeText(getApplicationContext(),"Tax saved", Toast.LENGTH_SHORT).show();
			      //System.out.println("ArrayList does not contain 4");
			    }
			    else{
			    	Toast.makeText(getApplicationContext(),"check your previous Categories", Toast.LENGTH_SHORT).show();
			    	//System.out.println("ArrayList contains 4 at index :" + index);
			   tax.setError("check it");
			    
			    }
			      
			
			
			tax.setText("");
				/*Intent add_menu = new Intent(Menu.this,
						AddMenu.class);
				startActivity(add_menu);*/
				//finish();

			}
		});
	}
 private void TaxsevedData() {
		// TODO Auto-generated method stub
	 DisplayData.clear();
	 data.open();
	  Cursor getdetails=data.getlistitems();
	 
	  data.close();
	  
	  
	  if(getdetails.moveToFirst())
	  {
	   do{
		 row_id=getdetails.getString(0);
		   ListTax=getdetails.getString(1);
	   
       System.out.println("name::::::::::"+ListTax);
      rowList.add(row_id);
       DisplayData.add(new ConstructorTax(ListTax));
	   }while(getdetails.moveToNext());
	  }
	  if(getdetails.getCount()>0){
		  et.setVisibility(View.GONE);
		  okay.setVisibility(View.GONE);
		  tax.setVisibility(View.GONE);
		 // DisplayData.add(new ConstructorTax(ListTax));
	  }
	 getdetails.close();
	
	}
public class DataAdapter extends BaseAdapter{
	     
	     private Context context;
	        private List<ConstructorTax> buttonList;
	     //private int rowResID;
	     public DataAdapter(Context context, int rowResID,
	       List<ConstructorTax> buttonList ) { 
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
	        	
	            final ConstructorTax row = buttonList.get(position);
	            Log.d("positions",""+buttonList.get(position));
	            /*Cursor cursor = (Cursor) ((AdapterView<ListAdapter>) convertView).getItemAtPosition(position);
	            final String item_content = cursor.getString(cursor.getColumnIndex(DataBase.KEY_CAT ));
	            Log.d("item",""+item_content);*/
	            Log.d("0",""+buttonList.get(0));
	            LayoutInflater inflater = LayoutInflater.from( context );
	            View v = inflater.inflate( R.layout.button, parent, false );
	            
	            final Button nametext = (Button)v.findViewById( R.id.cattext);
	       
	     
	        nametext.setText(row.Tax());
	       
	        
	  nametext.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
				
					 i=contacts.getPositionForView(v);
					 Log.d("i",""+i);
					
					 Context x=v.getContext();
					 Log.d("x",""+x);
			 		d=new Dialog(x);
			 		d.setContentView(R.layout.modifytax);
			 		d.setTitle("EDIT TAX ");
			 		d.show();
			 		final EditText t3=(EditText)d.findViewById(R.id.edit);
			 		t3.setText(row.Tax());
			 		Button ok=(Button)d.findViewById(R.id.okbutton);
			 		Button del=(Button)d.findViewById(R.id.delete);
			 		ok.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						 str=t3.getText().toString();
						 Log.d("str",""+str);
						System.out.println("name::::::"+str);
						nametext.setText(str);
						String rowid=rowList.get(i);
						Log.d("id",""+rowid);
						int p=Integer.parseInt(rowid);
						Log.d("p",""+p);
						data.open();
						data.updateTitle(p, str);
						contacts.setAdapter(notes); 
						DisplayData.remove(i);
						 DisplayData.add(new ConstructorTax(str));
						data.close();
						json=userfunctions.TaxApi(str);
				    	Log.d("json",""+json);
						  
						d.cancel();
						}
					});
			 		del.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							str=t3.getText().toString();
							 Log.d("str",""+str);
							System.out.println("name::::::"+str);
							nametext.setText(str);
							String rowid=rowList.get(i);
							Log.d("id",""+rowid);
							int p=Integer.parseInt(rowid);
							Log.d("p",""+p);
						data.open();
						data.deleteTitle(p);
						contacts.setAdapter(notes);
						
						data.close();
						DisplayData.remove(i);
						et.setVisibility(View.VISIBLE);
						  okay.setVisibility(View.VISIBLE);
						  tax.setVisibility(View.VISIBLE);
						d.cancel();
						}
					});
				}
			});
	  
	  
	  
	        return v;
	        } 
	    }
 /*public void onBackPressed() {
 	Log.d("back","back");
        // Do as you please
 	Intent in=new Intent(getApplicationContext(),Menu.class);    
	     startActivity(in);
 }*/
}