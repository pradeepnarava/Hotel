package com.brill.hotel;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderList extends Activity{
	 String aString;
	ListView orderlist;
	String Name,Price,Qun,Pricetext;
	 Dialog d;
	 DataLogo dl;
	 String Logo;
	// Button total;
	 TextView tp,totaltax,total;
	 String tax;
	 int t,q;
	 int aInt;
	 public static int i,p;
	 int s;
	 String id;
	 DataTax data;
	 private static final String TAG_ID = "id";
	 private static String KEY_SUCCESS = "status";
	public DataAdapter order;
	 public static List<Integer>orderList= new ArrayList<Integer>();
	 public static List<String>NameList= new ArrayList<String>();
	 public static List<String>PriceList= new ArrayList<String>();
	 public static List<Integer>qunorderList= new ArrayList<Integer>();
	 static ArrayList<Constructororder> DisplayData = new ArrayList<Constructororder>();
	@TargetApi(9)
	protected void onCreate(Bundle savedInstanceState) {
		  // TODO Auto-generated method stub
		
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.orderlist);
		  
		  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy); 
		  data=new DataTax(this);
		  dl=new DataLogo(this);
		  orderList.clear();
		  DisplayData.clear();
		  NameList.clear();
		  PriceList.clear();
		  total=(TextView)findViewById(R.id.total);
		  tp=(TextView)findViewById(R.id.totalprice);
		  totaltax=(TextView)findViewById(R.id.totaltax);
		 order=new DataAdapter(this,R.layout.orderitems, DisplayData);
		 orderlist=(ListView)findViewById(R.id.list);
		  orderlist.setAdapter(order);
		 
		  data.open();
		  Cursor getdetails=data.getlistitems();
		  data.close();
		  if(getdetails.moveToFirst())
		  {
		   do{
			 
		    tax=getdetails.getString(1);
		    aInt = Integer.parseInt(tax);
	        System.out.println(" tax::::::::::"+ tax);
	       Log.d("",""+tax);
	       Log.d("",""+aInt);
		   }while(getdetails.moveToNext());
		  }

		 getdetails.close();
		  getOrder();
		
		 /*Qun=Order.position;
		 Log.d("qqq",""+Qun);
		 q=Integer.parseInt(Qun);
		 Log.d("q",""+q);*/
		 
		
		/*
		total.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if(tax==null){
						Toast.makeText(getApplicationContext(), "Please check ur tax details", Toast.LENGTH_SHORT).show();
					}
					else{
					int tal = 0;
					for (Integer i : orderList) { // assuming list is of type List<Integer>
					    tal = (tal + i);
					    Log.d("",""+tal);
					    float tt=tal+(tal/aInt);
					    String ttax = Float.toString(tal/aInt);
					    String aString = Float.toString(tt);
					     tp.setText(aString+" With"+tax+"%of Tax");
					     Log.d("tp",""+tp);
					     totaltax.setText(ttax);
					     Log.d("tp",""+tp);
					}
					}
				}
				});*/
		  Button finalorder=(Button)findViewById(R.id.finalorder);
		  finalorder.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				   
					String[] arr = NameList.toArray(new String[NameList.size()]);
					//String item_name = Arrays.toString(arr);
					/*Log.d("Stringgggggggggg",""+item_name);
					//String item_name=NameList.get(r);
		         Log.d("item_name;",""+item_name);*/
		         String item_name = "";
		         String separator=",";
				    if (arr.length > 0) {
				    	item_name = arr[0];    // start with the first element
				        for (int i=1; i<arr.length; i++) {
				        	item_name =item_name + separator + arr[i];
				        }
				    }
		         Log.d("result",""+item_name);
		         
		         
		         
		         
		         String[] pricearr = PriceList.toArray(new String[PriceList.size()]);
		     // String Orderprice=Arrays.toString(pricearr);
		      String Orderprice = "";
		        // String separator=",";
				    if (pricearr.length > 0) {
				    	Orderprice = pricearr[0];    // start with the first element
				        for (int i=1; i<pricearr.length; i++) {
				        	Orderprice =Orderprice + separator + pricearr[i];
				        }
				    }
		         Log.d("Orderprice",""+Orderprice);
		     Log.d("Orderprice;",""+Orderprice);
		     
		     String[] qunarr = Order.qunList.toArray(new String[Order.qunList.size()]);
		     //String OrderQun=Arrays.toString(qunarr);
		     String OrderQun = "";
		        // String separator=",";
				    if ( qunarr.length > 0) {
				    	OrderQun =  qunarr[0];    // start with the first element
				        for (int i=1; i< qunarr.length; i++) {
				        	OrderQun =OrderQun + separator +  qunarr[i];
				        }
				    }
		         Log.d("OrderQun",""+OrderQun);
			Log.d("OrderQun",""+OrderQun);
			
			
			String[] categoryarr = Order.CatList.toArray(new String[Order.CatList.size()]);
			//String Category=Arrays.toString(categoryarr);
			 String Category = "";
		        // String separator=",";
				    if ( categoryarr.length > 0) {
				    	Category =  categoryarr[0];    // start with the first element
				        for (int i=1; i< categoryarr.length; i++) {
				        	Category =Category + separator +  categoryarr[i];
				        }
				    }
		         Log.d("Category",""+Category);
			Log.d("Category",""+Category);
			
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.UserOrder(item_name,Orderprice,OrderQun,"Jeg",Category,aString);
			Log.d("json",""+json);
			
			try {
				id = json.getString(TAG_ID);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//"id":1
			Log.d("id",""+id);
			/*try {
				if (json.getString(KEY_SUCCESS) != null) {
					
					String res = json.getString(KEY_SUCCESS); 
					if(Integer.parseInt(res) == 1){
						
						JSONObject json_user = json.getJSONObject("HotelApp");
						
						
					
						
					}else
					{
						
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}*/
				
				
				
				
			}
		});
		  Button bill=(Button)findViewById(R.id.checkOut);
		  bill.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(id==null){
					Toast.makeText(OrderList.this, "First Order the Items", Toast.LENGTH_SHORT).show();

				}
				else{
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.CheckOut(id);
				Log.d("json",""+json);
				Log.d("id",""+id);
				
				}
				
			}
		});
		  
		orderlist.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				tp.setText("");
			 s=orderlist.getPositionForView(view);
				Log.d("s",""+s);
				
			    Context x=view.getContext();
				 Log.d("x",""+x);
		 		d=new Dialog(x);
		 		d.setContentView(R.layout.edit_orderlist);
		 		d.setTitle("ENTER MODIFIED DATA");
		 		d.show();
		 		final EditText t1=(EditText)d.findViewById(R.id.qun);
		 		t1.setText(Order.qunList.get(s));
		 		Log.d("Qun",""+Qun);
		 		//final EditText t2=(EditText)d.findViewById(R.id.item);
		 		//t2.setText(Name);
		 		//t2.setVisibility(View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
		 		Log.d("Name",""+Name);
		 		final EditText t3=(EditText)d.findViewById(R.id.price);
		 	   // t3.setText(Price);
		 		//t3.setVisibility(View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
		 		Log.d("Price",""+Price);
		 		Button ok=(Button)d.findViewById(R.id.edit);
		 		Button del=(Button)d.findViewById(R.id.del);
		 		
		 		ok.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						//orderList.clear();
						Qun=t1.getText().toString();
						Log.d("Qun",""+Qun);
					System.out.println("Pricetext::::::"+Pricetext);
					Log.d("name list",""+NameList.get(s));
					Log.d("price list",""+PriceList.get(s));
					q=Integer.parseInt(Qun);
					 Log.d("q",""+q);
					
					 t=Integer.parseInt(PriceList.get(s));
					 orderList.set(s,t*q);
			 		 Log.d("t*q",""+t*q);
			 		 Order.qunList.set(s,Qun);
			 		//DisplayData.remove(s);
                    //getOrder();
			 		orderlist.setAdapter(order);
			 		  DisplayData.set(s,new Constructororder(NameList.get(s),PriceList.get(s),Qun)); 
					TotalOrderPrice();
					d.cancel();
					}
				});
		 		del.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						
						DisplayData.remove(s);
						 orderList.remove(s);
						 orderlist.setAdapter(order);
						 Order.orderList.remove(s);
						 Order.qunList.remove(s);
						 TotalOrderPrice();
						// getOrder();
						 Log.d("TotalOrderPrice","TotalOrderPrice");
						Toast.makeText(OrderList.this, "Selected Item deleted", Toast.LENGTH_SHORT).show();
						
						
						
						d.cancel();
						
					
					}
				});
				 
			}
			});
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
	        View view = findViewById(R.id.userorderlist);
	        view.setBackgroundDrawable(bd);
		}
		 
	}
	
	private void TotalOrderPrice() {
		// TODO Auto-generated method stub
		 if(tax==null){
				Toast.makeText(getApplicationContext(), "Please check ur tax details", Toast.LENGTH_SHORT).show();
			}
			else{
			int tal = 0;
			for (Integer i : orderList) { // assuming list is of type List<Integer>
			    tal = (tal + i);
			    Log.d("",""+tal);
			    float tt=tal+(tal/aInt);
			    String ttax = Float.toString(tal/aInt);
			     aString = Float.toString(tt);
			     tp.setText(aString+" With"+tax+"%of Tax");
			     Log.d("tp",""+tp);
			     totaltax.setText(ttax);
			     Log.d("tp",""+tp);
			}
			}
		
	}
	public class DataAdapter extends BaseAdapter{
	     
	     private Context context;
	        private List<Constructororder> buttonList;
	     //private int rowResID;
	     public DataAdapter(Context context, int rowResID,
	       List<Constructororder> buttonList ) { 
	       this.context = context;
	       //this.rowResID = rowResID;
	       this.buttonList = buttonList;
	     }
	        public int getCount() {                        
	            return buttonList.size();
	        }
	        public Object getItem(int position) {     
	            return buttonList.get(position);
	        }
	        public long getItemId(int position) {  
	            return position;
	        }
	        public View getView(final int position, View convertView, ViewGroup parent) { 
	         
	            final Constructororder row = buttonList.get(position);
	            LayoutInflater inflater = LayoutInflater.from( context );
	            View v = inflater.inflate( R.layout.orderitems, parent, false );
	            
	            final TextView nametext = (TextView)v.findViewById( R.id.nametext);
	           
	            final TextView pricetext = (TextView)v.findViewById( R.id.pricetext);
	            final TextView quntext = (TextView)v.findViewById( R.id.quntext);
	        nametext.setText(row.Name());
	        pricetext .setText(row.Price());
	        quntext.setText(row.Qun());
	      
	    
	     /* quntext .setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					 i= orderlist.getPositionForView(v);
					
					 Log.d("i",""+i);
					 p=i+1;
					 Intent in=new Intent(getApplicationContext(),Order.class);    
				     startActivity(in);
				    Context x=v.getContext();
					 Log.d("x",""+x);
			 		d=new Dialog(x);
			 		d.setContentView(R.layout.modifiedcat);
			 		d.setTitle("ENTER MODIFIED DATA");
			 		d.show();
			 		final EditText t3=(EditText)d.findViewById(R.id.edit);
			 		t3.setText(row.Qun());
			 		Button ok=(Button)d.findViewById(R.id.okbutton);
			 		Button del=(Button)d.findViewById(R.id.delete);
			 		del.setText("Cancel");
			 		ok.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							//orderList.clear();
							Qun=t3.getText().toString();
						
						System.out.println("Pricetext::::::"+Pricetext);
						quntext.setText(Qun);
						q=Integer.parseInt(Qun);
						 Log.d("q",""+q);
						
						 t=Integer.parseInt(Price);
						 orderList.set(i,t*q);
				 		 Log.d("t*q",""+t*q);
				 		 Order.qunList.set(i,Qun);
				 		DisplayData.remove(i);

				 		orderlist.setAdapter(order);
				 		  DisplayData.add(new Constructororder(Name,Price,Qun)); 
						TotalOrderPrice();
						d.cancel();
						}
					});
			 		del.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
														
							d.cancel();
							
						
						}
					});
				}
			});
	*/
	        return v;
	        } 
	    }
	 public boolean onCreateOptionsMenu(Menu menu) {
	    	MenuInflater inflater = getMenuInflater();
	    	inflater.inflate(R.menu.options_menu, menu);
	    	return true;
	    }
	    
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
	    	case R.id.next:
	    		Intent gal=new Intent(getApplicationContext(),UsermenuItems.class);    
			     startActivity(gal);
	    		/*Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.next) + " menu option",
	            		Toast.LENGTH_SHORT).show();*/
	    		return true;
	    	case R.id.previous:
	    		Intent in=new Intent(getApplicationContext(),UserCategory.class);    
			     startActivity(in);
	    		/*Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.previous) + " menu option",
	            		Toast.LENGTH_SHORT).show();*/
	    		return true;
	    	case R.id.list:
	    		Intent menu=new Intent(getApplicationContext(),UserMenu.class);    
			     startActivity(menu);
	    		/*Toast.makeText(this, "You have chosen the " + getResources().getString(R.string.list) + " menu option",
	            		Toast.LENGTH_SHORT).show();*/
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
	    	}
	    }
	   /* @Override
	    public void onBackPressed() {
	    	Log.d("back","back");
	           // Do as you please
	    	 Intent in=new Intent(getApplicationContext(),Order.class);    
		     startActivity(in); 
	    }*/
	    private void getOrder() {
			// TODO Auto-generated method stub
			 for(int p=0;p<Order.orderList.size();p++) {
				 Log.d("Order.orderList.size()",""+Order.orderList.size());
			  String st = Order.orderList.get(p);
			  Qun=Order.qunList.get(p);
			  Log.d("qqq",""+Qun);
				 q=Integer.parseInt(Qun);
				 Log.d("q",""+q);
			  Log.d("Order.orderList.get(p)",""+Order.orderList.get(p));
			  Log.d("st",""+st);
	          String str[] = st.split(":");
	          Log.d("str[]",""+str);
	          for (int s = 0; s < str.length; s++) {

	           Name=str[s];
	              Log.d("split lat",""+Name);
	              NameList.add(Name);
	              Log.d("name list",""+NameList.size());
	           Price=str[++s];
	              Log.d("split long",""+Price);
	              PriceList.add(Price);
	              Log.d("price list",""+PriceList.size());
	             t=Integer.parseInt(Price);
	              
	              Log.d("orderList",""+orderList);
	              
	              
	              
	          }
	          orderList.add(t*q);
	 		 Log.d("t*q",""+t*q);
	         DisplayData.add(new Constructororder(Name,Price,Qun)); 
			 TotalOrderPrice();
			 Log.d("TotalOrderPrice","TotalOrderPrice");
			 Log.d("displaydata",""+DisplayData);
	         Log.d("displaydata",""+DisplayData.size());
	         Log.d("displaydata",""+DisplayData.get(0));
			 }
		}

}
