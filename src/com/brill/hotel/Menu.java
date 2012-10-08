package com.brill.hotel;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;




public class Menu extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		
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
		
	}
	/*public void onBackPressed() {
    	Log.d("back","back");
           // Do as you please
    	 Intent in=new Intent(getApplicationContext(),.class);    
	     startActivity(in);
    }*/
}



