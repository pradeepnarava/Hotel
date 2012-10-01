package com.brill.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class AddLogo extends Activity{
	GridView safe;
	DataLogo dl;
public static String logopath;
	Button show,edit;;
	    public int SELECT_PICTURE;
	    int imageNum = 0;
	    public static final String PREFS_NAME = "MyApp";
	
	    private String selectedImagePath;
	    private ImageView img;
	   public static List<String> LogoPath = new ArrayList<String>();
	    private List<String> imageUrls = new ArrayList<String>();
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       
	        setContentView(R.layout.add_logo);
	        dl=new DataLogo(this);
	        Log.d("size",""+LogoPath.size());
	        dl.open();
			  Cursor getdetails=dl.getlistitems();
			  
			  dl.close();
			  Log.d("count",""+getdetails.getCount());
			  if(getdetails.getCount()>0){
				 

				  Intent	in=new Intent(getApplicationContext(),ShowLogos.class);    
	        	     startActivity(in);
	        	     Toast.makeText(getApplicationContext()," Show Images", Toast.LENGTH_SHORT).show();
				 // DisplayData.add(new ConstructorTax(ListTax));
			  }
			 getdetails.close();
	        /*if(LogoPath.size()>0){
	        	
	        	 Intent	in=new Intent(getApplicationContext(),ShowLogos.class);    
        	     startActivity(in);
        	     Toast.makeText(getApplicationContext()," Show Images", Toast.LENGTH_SHORT).show();
	        	
	        }*/
         edit=(Button)findViewById(R.id.B2);
	        img=(ImageView)findViewById(R.id.imag);
	        edit.setVisibility(View.GONE);
	       
	       //safe.setAdapter(new CustomGallery(this));
	        
	         
	        ((Button) findViewById(R.id.Button01))
	                .setOnClickListener(new OnClickListener() {
	                    public void onClick(View arg0) {
	                    	 edit.setVisibility(View.VISIBLE);
	                    	Intent intent = new Intent();
	                    	
	                        intent.setType("image/*");
	                        intent.setAction(Intent.ACTION_GET_CONTENT);
	                    
	                        Log.d("Gallery displaying....!",""+intent);
	                       
	                       final File imagesFolder = new File(Environment.getExternalStorageDirectory(), "Logo");
	        			      imagesFolder.mkdirs(); // <----
	        			      
	        					
	        				
	        			       
	        				String fileName = "image_" + String.valueOf(imageNum) + ".jpg";
	        				
	        			
	        				startActivityForResult(intent,0);
	                        
	                    }
	                });
	        
	        edit.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					
					Intent menu = new Intent(AddLogo.this,
							ShowLogos.class);
					startActivity(menu);
					finish();
					
					
				}
				});
	        
	       
	    }
	    private Bitmap decodeFile(String f) {
			Bitmap b = null;
			try {
				// Decode image size
				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inJustDecodeBounds = true;

				FileInputStream fis = new FileInputStream(f);
				BitmapFactory.decodeStream(fis, null, o);
				fis.close();

				int scale = 1;
				if (o.outHeight > 100 || o.outWidth > 100) {
					scale = (int) Math.pow(2, (int) Math.round(Math
							.log(100 / (double) Math.max(o.outHeight, o.outWidth))
							/ Math.log(0.5)));
				}

				// Decode with inSampleSize
				BitmapFactory.Options o2 = new BitmapFactory.Options();
				o2.inSampleSize = scale;
				fis = new FileInputStream(f);
				b = BitmapFactory.decodeStream(fis, null, o2);
				fis.close();
			} catch (IOException e) {
			}
			return b;
		}
	    
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == RESULT_OK) {
	            if (requestCode == SELECT_PICTURE) {
	            	try{
	            	Runtime.getRuntime().gc();
	            	Uri selectedImageUri = data.getData();
	                selectedImagePath = getPath(selectedImageUri);
	                System.out.println("Image Path : " + selectedImagePath);
	                Bitmap yourSelectedImage = decodeFile(selectedImagePath);
	                img.setImageBitmap(yourSelectedImage);
	                Drawable d =new BitmapDrawable(yourSelectedImage);
	                File path = Environment.getExternalStoragePublicDirectory(
	        		        Environment.DIRECTORY_PICTURES);
	                SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
	                //Log.d("imagenum displaying....!",""+imageNum);
	               imageNum =  settings.getInt("imageNum", 0);
	                
	        		String fileName = "image_" + String.valueOf(imageNum) + ".jpg";
	        		Log.d("imagenum displaying....!",""+imageNum);
	        	   imageNum=++imageNum;
	        	   Editor editor1 = settings.edit();
	               editor1.putInt("imageNum",imageNum);
	               editor1.commit();
	              Log.d("imagenum displaying....!",""+imageNum);
	              //String value = settings.getString("imageNum", "");
	              
	        	 
	        		
	        		File file = new File("/mnt/sdcard/Logo/"+fileName);
	        		Log.d("logopath",""+file);
	        		
	        		//logopath=file;
	        		Context ctx = getBaseContext();
				       File folder = ctx.getDir("Logo", Context.MODE_PRIVATE);
	        		

	           	    InputStream is =  new FileInputStream(selectedImagePath);
	           	
	        	    OutputStream os = new FileOutputStream(file);
	        	    
	        	    byte[] data1 = new byte[is.available()];
	        	    is.read(data1);
	        	    os.write(data1);
	        	    is.close();
	        	    os.close();	 
	        	    MediaScannerConnection.scanFile(this,
	        	            new String[] { file.toString() }, null,
	        	            new MediaScannerConnection.OnScanCompletedListener() {
	        	        public void onScanCompleted(String path, Uri uri) {
	        	        	logopath=path;
	        	        	dl.open();
	        	        	dl.insertval(1, logopath);
	        	        	Log.d("logopath",""+logopath);
	        	        	dl.close();
	        	        	LogoPath.add(logopath);
	        	        	Log.d("logopath",""+LogoPath.size());
	        	            Log.i("ExternalStorage", "Scanned " + path + ":");
	        	            Log.i("ExternalStorage", "-> uri=" + uri);
	        	        }
	        	    });
	            	}
	            	catch(IOException e) {
	            		
	            	}
	                imageUrls.add(selectedImagePath);
	           	    Log.d("imagePath displaying....!",""+selectedImagePath);
	           	 
	                         //img.setImageURI(selectedImageUri);
	               
	                
	             
	              
	                
	            }
	            
	        }
	    }

	    public String getPath(Uri uri) {
	        String[] projection = { MediaStore.Images.Media.DATA };
	        Cursor cursor = managedQuery(uri, projection, null, null, null);
	        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	        cursor.moveToFirst();
	       
	        return cursor.getString(column_index);
	    }
	    
	  
	    public void onBackPressed() {
	    	Log.d("back","back");
	           // Do as you please
	    	Intent in=new Intent(getApplicationContext(),Menu.class);    
		     startActivity(in);
		     finish();
	    }
		
}