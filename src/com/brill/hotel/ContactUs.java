package com.brill.hotel;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUs extends Activity {
	Button sub,edit;
	 DataUser data;
		
	 public static String phoneno,mobileno,emailid,address;
	EditText phone,mobile,email,addre;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conts);
		data=new DataUser(this);
		phone=(EditText)findViewById(R.id.phno);
		mobile=(EditText)findViewById(R.id.no);
		email=(EditText)findViewById(R.id.email);
		addre=(EditText)findViewById(R.id.address);
		sub=(Button)findViewById(R.id.submit);
		edit=(Button)findViewById(R.id.edit);
		edit.setVisibility(View.GONE);
		data.open();
		  Cursor getdetails=data.getlistitems();
		  
		  data.close();
		 if(getdetails.getCount()>0){
			 Intent in=new Intent(getApplicationContext(),Edit_contacts.class);    
		     startActivity(in);
		     finish();
				Toast.makeText(getApplicationContext(),"Already contact details are saved", Toast.LENGTH_SHORT).show();

			 
			 // DisplayData.add(new ConstructorTax(ListTax));
		  }
		
		edit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				Intent in=new Intent(getApplicationContext(),Edit_contacts.class);    
			     startActivity(in);
			}
			});
		sub.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				if(phone.getText().toString().equalsIgnoreCase(""))
				{
 					Toast.makeText(getApplicationContext(),"Enter phone number", Toast.LENGTH_SHORT).show();
					//name.setError("Enter name");
					
				}
				else if(mobile.getText().toString().equalsIgnoreCase(""))
				{
 					Toast.makeText(getApplicationContext(),"Enter mobile number", Toast.LENGTH_SHORT).show();
					//name.setError("Enter name");
					
				}
				
				else if(email.getText().toString().equalsIgnoreCase(""))
				{
 					Toast.makeText(getApplicationContext(),"Enter Correct email address", Toast.LENGTH_SHORT).show();
					//name.setError("Enter name");
					
				}
				else if(addre.getText().toString().equalsIgnoreCase(""))
				{
 					Toast.makeText(getApplicationContext(),"Enter address", Toast.LENGTH_SHORT).show();
					//name.setError("Enter name");
					
				}
				else{
				
				 if(email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
					{
					 phoneno=phone.getText().toString();
						mobileno=mobile.getText().toString();
						emailid=email.getText().toString();
						address=addre.getText().toString();
					 data.open();
						data.insertval(1,phoneno,mobileno,emailid,address);
						data.close();
				    	Toast.makeText(getApplicationContext(),"Contact details are saved", Toast.LENGTH_SHORT).show();

						phone.setText("");
						mobile.setText("");
						email.setText("");
						addre.setText("");
						Intent add_menu = new Intent(ContactUs.this,
								Contacts.class);
						startActivity(add_menu);
						finish();	
						
					}
				 else{
	 					Toast.makeText(getApplicationContext(),"Enter valid email address", Toast.LENGTH_SHORT).show();

				 }
				
				}
				
			}
			});
	}
	 public void onBackPressed() {
	    	Log.d("back","back");
	           // Do as you please
	    	Intent in=new Intent(getApplicationContext(),Menu.class);    
		     startActivity(in);
	    }
}
