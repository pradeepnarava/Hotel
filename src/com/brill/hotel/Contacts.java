package com.brill.hotel;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Contacts extends Activity {
	DataUser data;
	DataLogo dl;
	String Logo;
	public static String phone,mobile,email,address;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contacts);
		data=new DataUser(this);
		dl=new DataLogo(this);
		 TextView t1=(TextView)findViewById(R.id.phone);
 		 TextView t2=(TextView)findViewById(R.id.mobile);
 		 TextView t3=(TextView)findViewById(R.id.email);
 		 TextView t4=(TextView)findViewById(R.id.address);
 		data.open();
		Cursor getcontacts=data.getlistitems();
		data.close();
		if(getcontacts.getCount()>0){
		 if(getcontacts.moveToFirst())
		  {
		   do{
			
			   phone=getcontacts.getString(0);
			  mobile=getcontacts.getString(1);
			   email=getcontacts.getString(2);
			   address=getcontacts.getString(3);
		    Log.d(" phone",""+ phone);
		    Log.d("  email",""+  email);
		    
	        
	      
			
		   }while(getcontacts.moveToNext());
		   getcontacts.close();
		}
		}else{
			 Toast.makeText(Contacts.this,"Contact detailes are not available", Toast.LENGTH_LONG).show();

		}
		 t1.setText(phone);
	 		t2.setText(mobile);
	 		t3.setText(email);
	 		t4.setText(address);
	 		 Log.d(" phone",""+ phone);
			    Log.d("  email",""+  email);
			    Log.d(" t1",""+ t1);
			    Log.d("  t2",""+ t2);
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
			  /*  Log.d("AddLogo.LogoPath.get(0)",""+AddLogo.LogoPath.get(0));
				 String pathName = AddLogo.LogoPath.get(0);
				 Log.d("pathName",""+pathName);*/
			        Resources res = getResources();
			        Bitmap bitmap = BitmapFactory.decodeFile(Logo);
			        BitmapDrawable bd = new BitmapDrawable(res, bitmap);
			        View view = findViewById(R.id.usercontact);
			        view.setBackgroundDrawable(bd);
				  }
 		 
	}

}
