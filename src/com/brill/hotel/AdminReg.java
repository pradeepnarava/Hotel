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
import android.widget.TextView;
import android.widget.Toast;

public class AdminReg extends Activity {
	EditText name,pass,conf_pass,table_no;
	Button log,canc;
	TextView alrdy;
	 String Name,Pass,Conf;
	
	 DataAdmin data;
	
		String Roll="admin";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_admin);
		data=new DataAdmin(this);
		data.open();
		Cursor getdetails=data.getlistitems();
		data.close();
		if(getdetails.getCount()>0){
			Intent admin = new Intent(AdminReg.this,
					User.class);
	    	startActivity(admin);
	    	finish();
		}
		else{
			Toast.makeText(getApplicationContext(),"Please Enter AdminDetails",Toast.LENGTH_SHORT).show();

		}
		Log.d("",""+Roll);
		name=(EditText)findViewById(R.id.admin_name);
		pass=(EditText)findViewById(R.id.pass);
		conf_pass=(EditText)findViewById(R.id.conf_pass);
		
		log=(Button)findViewById(R.id.login);
		canc=(Button)findViewById(R.id.cancel);
		 
		
		
		
		log.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
				
				Name=name.getText().toString();	
				Pass=pass.getText().toString();	
				
				Conf=conf_pass.getText().toString();
				Log.d("",""+Roll);
				
				
				
				
					if(name.getText().toString().equalsIgnoreCase("") ){
						//name.setError("please enter name");
						
						Toast.makeText(getApplicationContext(),"please enter username",Toast.LENGTH_SHORT).show();
					}
					else if(pass.getText().toString().equalsIgnoreCase("")){
						
						//pass.setError("please enter password");
						Toast.makeText(getApplicationContext(),"please enter password",Toast.LENGTH_SHORT).show();
					}
                    else if(conf_pass.getText().toString().equalsIgnoreCase("")){
						
						//pass.setError("please enter password");
						Toast.makeText(getApplicationContext(),"please enter Confirmpassword",Toast.LENGTH_SHORT).show();
					}
					else{
						if(Pass.equals(Conf))
					{
					Log.d("",""+Roll);
			
				data.open();
				data.insertval(1,Name,Pass,Roll);
			//data.insertvaluser(1, Name, Pass, Roll, b);
				data.close();
				
				
				Intent add_menu = new Intent(AdminReg.this,
						Menu.class);
				startActivity(add_menu);
				finish();
				Toast.makeText(getApplicationContext(),"Account created",Toast.LENGTH_SHORT).show();
				
				name.setText("");
				pass.setText("");
				conf_pass.setText("");
					}
					else{
						
						Toast.makeText(getApplicationContext(),"password & ConfirmPassword  don't match. Try again?",Toast.LENGTH_SHORT).show();

					}
				}
				
				}
		});
		canc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				name.setText("");
				pass.setText("");
				conf_pass.setText("");
				Toast.makeText(getApplicationContext(),"Enter valid information",Toast.LENGTH_SHORT).show();
				
				/*Intent admin = new Intent(AdminReg.this,
						User.class);
		    	startActivity(admin);*/
			
			}
	});
	
	}

}
