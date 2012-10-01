package com.brill.hotel;






import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

public class Category extends Activity{

	EditText edit_cat;
	 Dialog d;
	 DataBase data;
		
 String cat,row_id;
 ListView contacts;

public static String str;
 public DataAdapter notes;
 ArrayList<Constructor> DisplayData = new ArrayList<Constructor>();
 public static List<String>catList= new ArrayList<String>();
 public static List<String>rowList= new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cat);
		data=new DataBase(this);
		notes=new DataAdapter(this,R.layout.button, DisplayData);
		  contacts=(ListView)findViewById(R.id.list);
		 try{
			  contacts.setAdapter(notes);
		  }catch(NullPointerException e){
			  
		  }
	 edit_cat = (EditText)findViewById(R.id.edit_cat);
	CategorySaved();
	  
	 
	 
	 
	  //cate.setAdapter(notes);
	//	mainListView = (ListView)findViewById(R.id.listView1);
		
	        Button save_cat = (Button) findViewById(R.id.save_cat);
	        //Button show = (Button) findViewById(R.id.show_cat);
	        save_cat.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					
					if(edit_cat.getText().toString().equalsIgnoreCase(""))
					{
						//edit_cat.setError("Enter name");
				    	Toast.makeText(getApplicationContext(),"Enter Category", Toast.LENGTH_SHORT).show();

					}
					else{
						cat = edit_cat.getText().toString();
						Log.d("cat",""+cat);
						catList.clear();
						data.open();
						Cursor getdetails=data.getlistitems();
						data.close();
						 if(getdetails.moveToFirst())
						  {
						   do{
						 String ccat=getdetails.getString(getdetails.getColumnIndex("cat"));

						   Log.d("ccat",""+ccat);
					        //System.out.println("name::::::::::"+cat);
					       catList.add(ccat);
					        
						   }while(getdetails.moveToNext());
						  }
						 Log.d(" catList",""+ catList);
						 //catList.contains(cat);
						// Log.d(" catList",""+catList.contains(cat));
					 int index = catList.indexOf(cat);
                    Log.d("",""+index);
						    if(index == -1){
						    	data.open();
						    	data.insertval(1, cat);
						    	data.close();
						    	rowList.clear();
						    	//DisplayData.clear();
						    	contacts.setAdapter(notes);
						    	CategorySaved();
						  
						    	//DisplayData.add(new Constructor(cat));
						    	Toast.makeText(getApplicationContext(),"Data saved", Toast.LENGTH_SHORT).show();
						      //System.out.println("ArrayList does not contain 4");
						    }
						    else{
						    	Toast.makeText(getApplicationContext(),"check your previous Categories", Toast.LENGTH_SHORT).show();
						    	//System.out.println("ArrayList contains 4 at index :" + index);
						    }
						      
						
						//data.close();
						edit_cat.setText("");
				
					}
						
						
					
				
           			 
				       
					
				}
			});
	       /* show.setOnClickListener(new View.OnClickListener(){

				public void onClick(View v) {
					Intent in=new Intent(getApplicationContext(),CategoryList.class);    
				     startActivity(in);
					
				}
	        });*/
	        
		
	}
	
 private void CategorySaved() {
		// TODO Auto-generated method stub
	 DisplayData.clear();
	 data.open();
	  Cursor getdetails=data.getlistitems();
	  
	  data.close();
	  if(getdetails.moveToFirst())
	  {
	   do{
		 row_id=getdetails.getString(0);
	    cat=getdetails.getString(1);
	   Log.d("cat",""+cat);
       System.out.println("name::::::::::"+cat);
      rowList.add(row_id);
      Log.d("rowlist size",""+rowList.size());
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
	            
	            final Button nametext = (Button)v.findViewById( R.id.cattext);
	       
	     
	        nametext.setText(row.Cat());
	       
	        
	  nametext.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
				
					 final int i=contacts.getPositionForView(v);
					 Log.d("i",""+i);
					
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
						String rowid=rowList.get(i);
						Log.d("id",""+rowid);
						int p=Integer.parseInt(rowid);
						Log.d("p",""+p);
						data.open();
						data.updateTitle(p, str);
						Log.d("str",""+str);
						contacts.setAdapter(notes);
						
						 DisplayData.add(new Constructor(str));
						data.close();
						DisplayData.remove(i);

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
						
						d.cancel();
						}
					});
				}
			});
	  
	  
	  
	        return v;
	        } 
	    }
}
	
