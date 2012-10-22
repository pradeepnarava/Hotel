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
	private static String UpdateURL = "http://192.168.2.15:3000/hotelsessions/update_order.json";
	private static String CheckOutURL = "http://192.168.2.15:3000/hotelsessions/checkout.json";
	private static String recallURL = "http://192.168.2.15:3000/hotelsessions/recall_order.json";
	private static String ItemsURL = "http://192.168.2.15:3000/items/create.json";
	private static String Update_PriceURL = "http://192.168.2.15:3000/items/price_change.json";
	private static String TaxURL = "http://192.168.2.15:3000/hotelsessions/tax.json";
	private static String OrderCancelURL = "http://192.168.2.15:3000/orderlists/cancelrequest.json";
	private static String TableURL = "http://192.168.2.15:3000/tables/create.json";
	
	//private static String register_tag = "register";
	
	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	

	public JSONObject UserOrder(String table_id,String total,String item_id,String item_qun){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Log.d("table_id",""+table_id);
		Log.d("String total",""+total);
		Log.d("item_id",""+item_id);
		Log.d("item_qun",""+item_qun);
		
		
		params.add(new BasicNameValuePair("table_id",table_id));
		params.add(new BasicNameValuePair("total",total));
		params.add(new BasicNameValuePair("items[id]", item_id));
		params.add(new BasicNameValuePair("items[quantity]",item_qun));
		
		
		
		JSONObject json = jsonParser.getJSONFromUrl(OrderURL, params);
		// return json
		// Log.e("JSON", json.toString());
		


		
		
		
		return json;
	}
	public JSONObject UpdateOrder(String UpdatedId,String nameList,String price, String quantity,String category,String total){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Log.d("item_name",""+nameList);
		Log.d("price",""+price);
		Log.d("quantity",""+quantity);
		Log.d("UpdatedId",""+UpdatedId);
		Log.d("category",""+category);
		Log.d("total",""+total);
		params.add(new BasicNameValuePair("id",UpdatedId));
		params.add(new BasicNameValuePair("items[item_name]", nameList));
		params.add(new BasicNameValuePair("items[price]",price));
		params.add(new BasicNameValuePair("items[quantity]", quantity));
		
		
		params.add(new BasicNameValuePair("items[category]", category));
		params.add(new BasicNameValuePair("total",total));
		
		JSONObject json = jsonParser.getJSONFromUrl(UpdateURL, params);
		// return json
		// Log.e("JSON", json.toString());
		


		
		
		
		return json;
	}
	public JSONObject CheckOut(String id){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Log.d("id",""+id);
		
		
		params.add(new BasicNameValuePair("table_id", id));
		
		
		JSONObject json = jsonParser.getJSONFromUrl(CheckOutURL,params);

		
		
		
		return json;
	}
	public JSONObject Recall(String id){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Log.d("id",""+id);
		
		
		params.add(new BasicNameValuePair("table_id", id));
		
		
		JSONObject json = jsonParser.getJSONFromUrl(recallURL,params);

		
		
		
		return json;
	}
	public JSONObject TaxApi(String tax){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Log.d("id",""+tax);
		
		
		params.add(new BasicNameValuePair("tax",tax));
		
		
		JSONObject json = jsonParser.getJSONFromUrl(TaxURL,params);

		
		
		
		return json;
	}
	public JSONObject UpPriceURL(String price,String Item_Id){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Log.d("price",""+price);
		Log.d("Item_Id",""+Item_Id);
		
		params.add(new BasicNameValuePair("price",price));
		params.add(new BasicNameValuePair("id",Item_Id));
		
		
		JSONObject json = jsonParser.getJSONFromUrl(Update_PriceURL,params);

		
		
		
		return json;
	}
	public JSONObject OrderCancel(String tableid,String itemid,String quantity){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	
		
		
		params.add(new BasicNameValuePair("table_id",tableid));
		Log.d("item_name",""+tableid);
		params.add(new BasicNameValuePair("item_id",itemid));
		Log.d("itemid",""+itemid);
		
		params.add(new BasicNameValuePair("quantity",quantity));
		
		Log.d("quantity",""+quantity);
		
		JSONObject json = jsonParser.getJSONFromUrl(OrderCancelURL,params);

		
		
		
		return json;
	}
	public JSONObject ItemsCreate(String Name,String Price,String Category){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		
		
		params.add(new BasicNameValuePair("item[item_name]", Name));
		Log.d("item[item_name]",""+Name);
		
		params.add(new BasicNameValuePair("item[price]", Price));
		Log.d("item[price]",""+Price);
		params.add(new BasicNameValuePair("item[category]", Category));
		Log.d("item[category]",""+Category);
		
		JSONObject json = jsonParser.getJSONFromUrl(ItemsURL,params);

		
		
		
		return json;
	}
	public JSONObject Tablenumber(String no_table){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		
		
		params.add(new BasicNameValuePair("no_of_tables", no_table));
		Log.d("item[item_name]",""+no_table);
		
		
		
		JSONObject json = jsonParser.getJSONFromUrl(TableURL,params);

		
		
		
		return json;
	}
	
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
