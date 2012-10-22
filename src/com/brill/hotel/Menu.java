package com.brill.hotel;





import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class Menu extends Activity{
	public static String TableId; 
	DataTable dt;
	JSONArray tablearray;
	public static List<String>TableIDList= new ArrayList<String>();
	@TargetApi(9)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy); 
			dt=new DataTable(this);
		final Dialog dialog = new Dialog(this);
		final UserFunctions userfunctions=new UserFunctions();
		Button signin = (Button) findViewById(R.id.add_menu);
		signin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent add_menu = new Intent(Menu.this,
					MenuView.class);
				startActivity(add_menu);
				//finish();

			}
		});
		Button cat = (Button) findViewById(R.id.cat);
		cat.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent add_cat = new Intent(Menu.this,
						Category.class);
				startActivity(add_cat);
				//finish();

			}
		});
		Button newuser = (Button) findViewById(R.id.adduser);
		newuser.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent add_user = new Intent(Menu.this,
					AddUser.class);
				startActivity(add_user);
				

			}
		});
		Button logout = (Button) findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent addcat = new Intent(Menu.this,
						User.class);
				startActivity(addcat);
				finish();

			}
		});
		Button logo = (Button) findViewById(R.id.add_logo);
		logo.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent addlogo = new Intent(Menu.this,
						AddLogo.class);
				startActivity(addlogo);
			
			}
		});
		Button tax = (Button) findViewById(R.id.add_tax);
		tax.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent addtax = new Intent(Menu.this,
						AddTax.class);
				startActivity(addtax);
				
			}
		});
		Button contact = (Button) findViewById(R.id.add_contacts);
		contact.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent addtax = new Intent(Menu.this,
						ContactUs.class);
				startActivity(addtax);

			}
		});
		Button gal = (Button) findViewById(R.id.gallery);
		gal.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent addgal = new Intent(Menu.this,
						Gallery.class);
				startActivity(addgal);

			}
		});
		Button table_no = (Button) findViewById(R.id.tableno);
		table_no.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				

				 dialog.setContentView(R.layout.table);
				 dialog.setTitle("Table Number");
				 dialog.show();
				 final EditText table_no=(EditText)dialog.findViewById(R.id.table_num);
				 Button enter=(Button)dialog.findViewById(R.id.enter_table);
				 enter.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String Table_num=table_no.getText().toString();
						Log.d("Table_num",""+Table_num);
						int tn=Integer.parseInt(Table_num);
						Log.d("tn",""+tn);
						 JSONObject  json = userfunctions.Tablenumber(Table_num);
					      Log.d("json",""+json);
					     try {
							tablearray=json.getJSONArray("id");
							Log.d("tableid",""+tablearray);
							for(int i = 0; i < tablearray.length(); i++){
								TableId=tablearray.getString(i);
								dt.open();
								
								dt.insertval(1,TableId);
								dt.close();
								TableIDList.add(TableId);
								//Log.d("TableList",""+ TableIDList.get(1));
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					      dialog.cancel();
					}
				});

			}
		});
		
	}
	/*public void onBackPressed() {
    	Log.d("back","back");
           // Do as you please
    	 Intent in=new Intent(getApplicationContext(),.class);    
	     startActivity(in);
    }*/
}



