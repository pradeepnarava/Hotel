package com.brill.hotel;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends Activity {
	EditText name,pass,conf_pass,roll;
	Button log,canc;
	 String Name,Pass,Conf;
	 DataLogin data;
	 DataAdmin db;
		String Roll="user";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_user);
		data=new DataLogin(this);
		db=new DataAdmin(this);
		Log.d("",""+Roll);
		name=(EditText)findViewById(R.id.user_name);
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
				if(name.getText().toString().matches("[a-zA-Z]")){
					
				
				if(Name.length() > 0 && Pass.length() >0)
				{
				
					db.open();
					if(db.Login(Name, Pass))
					{
						
						
				    	Toast.makeText(AddUser.this,"Change your name", Toast.LENGTH_LONG).show();
					
					
					
					}
					else{
				if(Pass.equals(Conf))
				{
					if(conf_pass.getText().toString().equalsIgnoreCase("") ){
						//name.setError("please enter name");
						
						Toast.makeText(getApplicationContext(),"please enter confirm password",Toast.LENGTH_SHORT).show();
					}
					else if(pass.getText().toString().equalsIgnoreCase("")){
						
						//pass.setError("please enter password");
						Toast.makeText(getApplicationContext(),"please enter password",Toast.LENGTH_SHORT).show();
					}
					else{
					Log.d("",""+Roll);
			
				data.open();
				data.insertval(1,Name,Pass,Roll);
			//data.insertvaluser(1, Name, Pass, Roll, b);
				data.close();
				Intent menu = new Intent(AddUser.this,
						Menu.class);
				startActivity(menu);
				Toast.makeText(getApplicationContext(),"Account created",Toast.LENGTH_SHORT).show();
				
				name.setText("");
				pass.setText("");
				conf_pass.setText("");
					}
				}
				else{
					//name.setText("");
					
					Toast.makeText(getApplicationContext(),"password & ConfirmPassword  don't match. Try again?",Toast.LENGTH_SHORT).show();
				}
				}
				}
				else{
					
					Toast.makeText(getApplicationContext(),"please insert values",Toast.LENGTH_SHORT).show();
				}
				}
				else{
					Toast.makeText(getApplicationContext(),"please enter valid name",Toast.LENGTH_SHORT).show();

				}
				}
		});
		canc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				name.setText("");
				pass.setText("");
				conf_pass.setText("");
				
				Intent add_menu = new Intent(AddUser.this,
				Menu.class);
		startActivity(add_menu);
			}
	});
	
	}

}
