package com.brill.hotel;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryList extends Activity  {
	DataBase data;
	 String cat,id;
	ListView contacts;
	 Dialog d;
	public static int i,p;
	public static String str;
	 public DataAdapter notes;
	 ArrayList<Constructor> DisplayData = new ArrayList<Constructor>();

	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.cat_list);
	  data=new DataBase(this);
	 
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
		 
	    cat=getdetails.getString(0);
	   
        System.out.println("name::::::::::"+cat);
       
        DisplayData.add(new Constructor(cat));
	   }while(getdetails.moveToNext());
	  }

	 getdetails.close();
	 
	 
	
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
	            
	            final TextView nametext = (TextView)v.findViewById( R.id.cattext);
	       
	     
	        nametext.setText(row.Cat());
	       
	        
	  nametext.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
				
					i=1+contacts.getPositionForView(v);
					 Log.d("i",""+i);
					p=i+1;
					 Context x=v.getContext();
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
						data.updateTitle(p, str);
						data.close();
						d.cancel();
						}
					});
			 		del.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						 
						data.open();
						data.deleteTitle(p);
						data.close();
						d.cancel();
						}
					});
				}
			});
	  
	  
	  
	        return v;
	        } 
	    }
	}

