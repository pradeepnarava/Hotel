package com.brill.hotel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Order extends Activity{
	TextView name,price,des;
	Button order,cal;
	 String pathName;
	String Name,Price;
	DataMenuImage data;
	ImageView img;
	public static String position;
	Bitmap b;
	ScrollView l1,l2;
	private Dialog dialog;
	 public static List<String>orderList= new ArrayList<String>();
	 public static List<String>qunList= new ArrayList<String>();
	 public static List<Integer>total= new ArrayList<Integer>();
	// ArrayList<Object> myArr = new ArrayList<Object>();
	protected void onCreate(Bundle savedInstanceState) {
		  // TODO Auto-generated method stub
		
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.order);
		  //orderList.clear();
		//  qunList.clear();
		  data=new DataMenuImage(this);
		  name=(TextView)findViewById(R.id.name);
		  price=(TextView)findViewById(R.id.price);
		 des=(TextView)findViewById(R.id.des);
		  order=(Button)findViewById(R.id.order);
		  cal=(Button)findViewById(R.id.cal);
		  img=(ImageView)findViewById(R.id.image);
		  l1=(ScrollView)findViewById(R.id.l1);
			
			l2=(ScrollView)findViewById(R.id.l2);
			 l1.setOnTouchListener(new View.OnTouchListener() {

	                public boolean onTouch(View v, MotionEvent event) {
	                  
	                    findViewById(R.id.l2).getParent().requestDisallowInterceptTouchEvent(false);
	                    //findViewById(R.id.hscroll).getParent().requestDisallowInterceptTouchEvent(false);
	                    return false;
	                }
	            });
	           l2.setOnTouchListener(new View.OnTouchListener() {

	                public boolean onTouch(View v, MotionEvent event)
	                {
	                   
	                                        // Disallow the touch request for parent scroll on touch of child view
	                    v.getParent().requestDisallowInterceptTouchEvent(true);
	                    return false;
	                }
	            });
		  int p=UsermenuItems.i;
		  Log.d("p",""+p);
		  String stri=UsermenuItems.desList.get(p);
		  Log.d("des",""+stri);
		  des.setText(stri);
		  Log.d("des",""+des);
		  
		  String row=UsermenuItems.RowList.get(p);
		  Log.d("row",""+row);
		  int r=Integer.parseInt(row);
		  Log.d("r",""+r);
		  int ri=r-1;
		  Log.d("ri",""+ri);
          Log.d("images size",""+AddMenu.ImageList.size());
		 pathName = UsermenuItems.ImageList.get(p);
			 Log.d("pathName",""+pathName);
			 if(pathName==null){
			    	Toast.makeText(getApplicationContext(),"Plese check Icon", Toast.LENGTH_SHORT).show();

			 }else{
		        Resources res = getResources();
		        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
		       // BitmapDrawable bd = new BitmapDrawable(res, bitmap);
		        
		  img.setImageBitmap(bitmap);
			 }
		  String st = UsermenuItems.menuList.get(p);
          String str[] = st.split(":");
          for (int s = 0; s < str.length; s++) {

              String lat=str[s];
              Log.d("split lat",""+lat);
              String longi=str[++s];
              Log.d("split long",""+longi);
              name.setText(lat);
              price.setText(longi);
              
              
          }
          
          final Spinner spinner = (Spinner) findViewById(R.id.testSpinner);
          
          ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, quantity);
          
 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
          spinner.setAdapter(adapter);
          
          spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Log.d("postion",""+spinner.getPositionForView(arg1));
				int pos=spinner.getPositionForView(arg1);
				Log.d("pos",""+pos);
				position=quantity[pos];
				Log.d("p",""+position);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	  
          });
          
          order.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Name=name.getText().toString();
		              Price=price.getText().toString();
		              int p=Integer.parseInt(Price);
		              orderList.add(Name+":"+Price);
		              Log.d("orderList",""+orderList);
		              total.add(p);
		              Log.d("total",""+total);
		              qunList.add(position);
		              Log.d("qunList",""+qunList.size());
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
		       		dialog = ProgressDialog.show(Order.this, "Please wait...",
		       				"Order processing",
		       				true);
		       		dialog.setCancelable(true);
		       		Thread t = new Thread(showWaitDialog);
		       		t.start();

						// Toast.makeText(getApplicationContext(),"Please insert the image", Toast.LENGTH_SHORT).show();

				}
				
          });
          cal.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					int tal = 0;
					for (Integer i : total) { // assuming list is of type List<Integer>
					    tal = tal + i;
					    Log.d("",""+tal);
					    Intent in=new Intent(getApplicationContext(),OrderList.class);    
					     startActivity(in);  
					    //Toast.makeText(getApplicationContext(),tal, Toast.LENGTH_SHORT).show();
					}
				}
				});
	}
	static final String[] quantity = new String[]{
       "1","2","3","4","5","6","7","8","9","10"
        };
	 public boolean onCreateOptionsMenu(Menu menu) {
	    	MenuInflater inflater = getMenuInflater();
	    	inflater.inflate(R.menu.options_menu, menu);
	    	return true;
	    }
	    
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
	    	case R.id.next:
	    		Intent gal=new Intent(getApplicationContext(),UsermenuItems.class);    
			     startActivity(gal);
	    		/*Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.next) + " menu option",
	            		Toast.LENGTH_SHORT).show();*/
	    		return true;
	    	case R.id.previous:
	    		Intent in=new Intent(getApplicationContext(),UserCategory.class);    
			     startActivity(in);
	    		/*Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.previous) + " menu option",
	            		Toast.LENGTH_SHORT).show();*/
	    		return true;
	    	case R.id.list:
	    		Intent menu=new Intent(getApplicationContext(),UserMenu.class);    
			     startActivity(menu);
	    		/*Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.list) + " menu option",
	            		Toast.LENGTH_SHORT).show();*/
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
	    	}
	    }
	    @Override
	    public void onBackPressed() {
	    	Log.d("back","back");
	           // Do as you please
	    	 Intent in=new Intent(getApplicationContext(),UsermenuItems.class);    
		     startActivity(in); 
	    }
}
