package com.brill.hotel;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit_contacts extends Activity{
	EditText phone,mobile,email,addre;
	TextView alrdy;
	Button sub;
	DataUser data;
	String phoneno,mobileno,emailid,address;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_contacts);
		data=new DataUser(this);
		phone=(EditText)findViewById(R.id.phno);
		mobile=(EditText)findViewById(R.id.no);
		email=(EditText)findViewById(R.id.email);
		addre=(EditText)findViewById(R.id.address);
		alrdy=(TextView)findViewById(R.id.al);
		sub=(Button)findViewById(R.id.submit);
		data.open();
		Cursor getcontacts=data.getlistitems();
		data.close();
		if(getcontacts.getCount()>0){
		 if(getcontacts.moveToFirst())
		  {
		   do{
			
			   phoneno=getcontacts.getString(0);
			   mobileno=getcontacts.getString(1);
			   emailid=getcontacts.getString(2);
			   address=getcontacts.getString(3);
		    Log.d(" phone",""+ phone);
		    Log.d("  email",""+  email);
		    
	        
	      
			
		   }while(getcontacts.moveToNext());
		   getcontacts.close();
		}
		}
		
		phone.setText(phoneno);
		mobile.setText(mobileno);
		email.setText(emailid);
		addre.setText(address);
		//alrdy.setText("Contact details are alredy saved if we want to change details fill the above fields");
		sub.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				phoneno=phone.getText().toString();
				mobileno=mobile.getText().toString();
				emailid=email.getText().toString();
				address=addre.getText().toString();
				data.open();
				
				data.updateTitle(1,phoneno,mobileno,emailid,address);
				data.close();
		    	Toast.makeText(getApplicationContext(),"Contact details are saved", Toast.LENGTH_SHORT).show();
		    	
		    	phone.setText("");
				mobile.setText("");
				email.setText("");
				addre.setText("");
			}
			});
	}

}
