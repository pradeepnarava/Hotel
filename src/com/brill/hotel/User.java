package com.brill.hotel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User extends Activity {
	
	EditText name,pass;
	Button log,canc;
	String Name,Pass,Role;
	String user,passw;
	DataLogin data;
	DataAdmin db;
	DataLogo dl;
	String Logo;
	public static List<String>nameList= new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);
		
		data=new DataLogin(this);
		db=new DataAdmin(this);
		dl=new DataLogo(this);
		name=(EditText)findViewById(R.id.user_name);
		pass=(EditText)findViewById(R.id.pass);
		log=(Button)findViewById(R.id.login);
		canc=(Button)findViewById(R.id.cancel);
		data.open();
		  Cursor getdetails=data.getlistitems();
		  data.close();
		  db.open();
		  Cursor getadmin=db.getlistitems();
		  db.close();

		  /*if(getdetails.moveToFirst())
		  {
		   do{
		   user=getdetails.getString(0);
		  passw=getdetails.getString(1);
		 Role=getdetails.getString(2);
	        System.out.println("name::::::::::"+Name);
	      
	        Log.d("Name",""+user);
			  Log.d("Pass",""+passw);
			  Log.d("Role",""+Role);
		   }while(getdetails.moveToNext());
		  }

		 getdetails.close();*/
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
			        View view = findViewById(R.id.ulogin);
			        view.setBackgroundDrawable(bd);
				  }
		log.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				 OrderList.DisplayData.clear();
				 Order.orderList.clear();
				 Order.qunList.clear();
				 Log.d("OrderList.DisplayData","OrderList.DisplayData");//Order.orderList.clear();
			Name=name.getText().toString();	
			Pass=pass.getText().toString();	
			
			try{
				if(name.getText().toString().equalsIgnoreCase("") ){
					//name.setError("please enter name");
					
					Toast.makeText(getApplicationContext(),"please enter name",Toast.LENGTH_SHORT).show();
				}
				else if(pass.getText().toString().equalsIgnoreCase("")){
					
					//pass.setError("please enter password");
					Toast.makeText(getApplicationContext(),"please enter password",Toast.LENGTH_SHORT).show();
				}
				else{
				if(Name.length() > 0 && Pass.length() >0)
				{
					
					db.open();
					data.open();
					if(db.Login(Name, Pass))
					{
						Intent menu = new Intent(User.this,
								Menu.class);
				    	startActivity(menu);
				    	finish();
						
				    	Toast.makeText(User.this,"Successfully Admin Logged In", Toast.LENGTH_LONG).show();
					
					name.setText("");
					pass.setText("");
					
					}
					
					else if(data.Login(Name, Pass))
					{
						Intent menu = new Intent(User.this,
								UserMenu.class);
				    	startActivity(menu);
						
						
				    	Toast.makeText(User.this,"Successfully User Logged In", Toast.LENGTH_LONG).show();
					
					name.setText("");
					pass.setText("");
					
					}
					
					else{
						Toast.makeText(User.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();
					
						
						//name.setError("enter name");
						//pass.setError("enter password");
					}
					db.close();
					data.close();
					
				}
				
				
				
				else{
					Toast.makeText(User.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();
					//name.setError("enter name");
					//pass.setError("enter password");
					
				}
				}
			}catch(Exception e)
			{
				Toast.makeText(User.this,e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
			
			
	
				
			}
	});
	canc.setOnClickListener(new View.OnClickListener() {

		public void onClick(View v) {
			name.setText("");
			pass.setText("");
			
			Intent m = new Intent(User.this,
					AdminReg.class);
	    	startActivity(m);
		
		}
});
	}
	/* @Override
	    public void onBackPressed() {
	    	Log.d("back","back");
	    	User.this.finish();
			System.exit(0);
	           // Do as you please
	    	 Intent in=new Intent(getApplicationContext(),.class);    
		     startActivity(in);
	    }*/
}
