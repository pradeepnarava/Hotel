package com.brill.hotel;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class UserFunctions {
private JSONParser jsonParser;
	
	private static String OrderURL = "http://192.168.2.15:3000/hotelsessions/create.json";
	private static String CheckOutURL = "http://192.168.2.15:3000/hotelsessions/checkout.json";
	//private static String registerURL = "http://dev.thesmartcampus.com/users/sign_up";
	
	//private static String register_tag = "register";
	
	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	

	public JSONObject UserOrder(String nameList,String price, String quantity,String table_name,String category,String total){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Log.d("item_name",""+nameList);
		Log.d("price",""+price);
		Log.d("quantity",""+quantity);
		Log.d("table_name",""+table_name);
		Log.d("category",""+category);
		Log.d("total",""+total);
		
		params.add(new BasicNameValuePair("items[item_name]", nameList));
		params.add(new BasicNameValuePair("items[price]",price));
		params.add(new BasicNameValuePair("items[quantity]", quantity));
		params.add(new BasicNameValuePair("hotelsession[table_name]",table_name));
		
		params.add(new BasicNameValuePair("items[category]", category));
		params.add(new BasicNameValuePair("hotelsession[total]",total));
		
		JSONObject json = jsonParser.getJSONFromUrl(OrderURL, params);
		// return json
		// Log.e("JSON", json.toString());
		


		
		
		
		return json;
	}
	public JSONObject CheckOut(String id){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Log.d("id",""+id);
		
		
		params.add(new BasicNameValuePair("id", id));
		
		
		JSONObject json = jsonParser.getJSONFromUrl(CheckOutURL,params);

		
		
		
		return json;
	}
	/*public JSONObject registerUser(String Email, String Password,String Conformation){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
	    params.add(new BasicNameValuePair("conformation",Conformation));
		params.add(new BasicNameValuePair("email", Email));
		params.add(new BasicNameValuePair("password", Password));
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		// return json
		return json;
	}*/
	public boolean isUserLoggedIn(Context context){
		/*Database db = new Database(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}*/
		return false;
	}
	
	
	
	/**
	 * Function get Login status
	 * */
	
	
}
