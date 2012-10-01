package com.brill.hotel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuView extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuview);
		Button add = (Button) findViewById(R.id.add_menu);
		Button view = (Button) findViewById(R.id.menu_view);
		add.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent add_menu = new Intent(MenuView.this,
						AddMenu.class);
				startActivity(add_menu);
				//finish();

			}
		});
		view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent add_menu = new Intent(MenuView.this,
						AddmenuList.class);
				startActivity(add_menu);
				//finish();

			}
		});
	}
}
