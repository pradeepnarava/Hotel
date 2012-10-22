package com.brill.hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Recall extends ListActivity {
	JSONObject json,json1;
	JSONArray results = null;
	String up_name,up_price,up_qun;
	TimerTask mTimerTask;
	final Handler handler = new Handler();
	Timer t = new Timer();
	private int nCounter = 0;
	DataMenuImage dmi;
	//public  static final String TAG_CANCEL_RESULTS = "cancel_items";
	public  static final String TAG_RESULTS = "items";
	 private static final String TAG_UPDATEID = "updateid";
	 private static final String TAG_HOTEL_ID = "id";
	 private static final String TAG_QUANTITY = "quantity";
	 private static final String TAG_PRICE = "price";
	 private static final String TAG_ITEM_NAME = "item_id";
	 Button cancel_item;
	 public static String Up_id;
	  Dialog dialog ;
	  public static List<String>TableIdList= new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.recall_list);
		 dmi=new DataMenuImage(this);
		 Intent in = getIntent();
		  dialog = new Dialog(this);
		 dialog.setTitle("Please Wait");
		 dialog.show();
		
		 /*dmi.open();
		 Cursor getitems=dmi.getlistitems();
		 dmi.close();*/
	        // Get JSON values from previous intent
		 Up_id= in.getStringExtra(TAG_UPDATEID);
		 ArrayList<HashMap<String, String>> resultlist = new ArrayList<HashMap<String, String>>();
		 resultlist.clear();
		UserFunctions userFunction = new UserFunctions();
		
		 json = userFunction.Recall(Up_id);
		 Log.d("id",""+ Up_id);
		Log.d("json",""+json);
		
			try {
				if(json!=null){
				results = json.getJSONArray(TAG_RESULTS);
				Log.d("results",""+results);
			
				for(int i = 0; i < results.length(); i++){
					
					Log.d("length",""+results.length());
					JSONObject c = results.getJSONObject(i);
					Log.d("c",""+c);
				String hotelid = c.getString(TAG_HOTEL_ID);
				Log.d("hotelid",""+hotelid);
				String updatequantity=c.getString(TAG_QUANTITY);
				Log.d("updatequantity",""+updatequantity);
				String updateitem=c.getString(TAG_ITEM_NAME);
				Log.d("updateitem",""+updateitem);
				TableIdList.add(updateitem);
				for (int s = 0; s < OrderList.ItemNameList.size(); s++) {
	  			    if( updateitem.equals(OrderList.ItemIdList.get(s))) {
	  			    	updateitem=OrderList.ItemNameList.get(s);
	  			    	Log.d("Order_itemid",""+updateitem);
	  			        
	  			    }
	  			}
				String updateprice=c.getString(TAG_PRICE);
				Log.d("updateprice",""+updateprice);
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				
				map.put(TAG_QUANTITY, updatequantity);
				map.put(TAG_ITEM_NAME, updateitem);
				map.put(TAG_PRICE, updateprice);
				resultlist.add(map);
				Log.d("list",""+resultlist);
				}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			ListAdapter adapter = new SimpleAdapter(this, resultlist,
					R.layout.recall,
					new String[] { TAG_ITEM_NAME,TAG_QUANTITY, TAG_PRICE }, new int[] {
							R.id.up_name, R.id.up_qun, R.id.up_price });

			setListAdapter(adapter);
             dialog.cancel();
			// selecting single ListView item
			final ListView lv = getListView();

			// Launching new screen on Selecting Single ListItem
			lv.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String pos=lv.getItemAtPosition(position).toString();
					Log.d("pos",""+pos);
					int p=lv.getPositionForView(view);
					Log.d("p",""+p);
					// getting values from selected ListItem
					 up_name =TableIdList.get(p).toString();
					Log.d("up_name",""+up_name);
					 up_qun = ((TextView) view.findViewById(R.id.up_qun)).getText().toString();
					Log.d("up_qun",""+up_qun);
					 up_price = ((TextView) view.findViewById(R.id.up_price)).getText().toString();
					Log.d("up_price",""+up_price);
					
							// TODO Auto-generated method stub
					        dialog.setContentView(R.layout.cancelation);
					        dialog.setTitle("Item cancelation");
							Log.d("cancel","cancel");
							dialog.show();
							Button no=(Button)dialog.findViewById(R.id.no);
							Button yes=(Button)dialog.findViewById(R.id.yes);
							
							no.setOnClickListener(new View.OnClickListener() {
								
								public void onClick(View v) {
									// TODO Auto-generated method stub
									dialog.dismiss();
								}
							});
                           yes.setOnClickListener(new View.OnClickListener() {
								
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Log.d("up_name",""+up_name);
									Log.d("up_qun",""+up_qun);
									Log.d("up_price",""+up_price);
									 Log.d("id",""+ Up_id);
									UserFunctions userfun=new UserFunctions();
									JSONObject json=userfun.OrderCancel(Up_id,up_name,up_qun);
								Log.d("json",""+json);
									doTimerTask();
								/*try {
									
									String y_n=json.getString(TAG_CANCEL_RESULTS);
									Log.d("y_n",""+y_n);
									if(y_n=="yes"){
								    	Toast.makeText(getApplicationContext(),"Successfully Canceled", Toast.LENGTH_LONG).show();
		                                dialog.dismiss();
									}
									else{
								    	Toast.makeText(getApplicationContext(),"Sorry,cancelation is not accepted", Toast.LENGTH_LONG).show();
								    	 dialog.dismiss();
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/
								}
							});
							
						
					
				}
			});
		}
	public void doTimerTask(){
   	 
    	mTimerTask = new TimerTask() {
    	        public void run() {
    	                handler.post(new Runnable() {
    	                        public void run() {
    	                        	nCounter++;
    	                        	 Log.d("count",""+nCounter);
if(nCounter==2){
	Log.d("recall","recallll");
	Intent back=new Intent(getApplicationContext(),Recall.class);
	startActivity(back);
	finish();
	dialog.dismiss();
	
}
                                       
    	                        	Log.d("TIMER", "TimerTask run"+mTimerTask);
    	                        }
    	               });
    	        }};
    	        t.schedule(mTimerTask,10000,60000);
    }  
		
		}



