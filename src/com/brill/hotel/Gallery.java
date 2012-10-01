package com.brill.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class Gallery extends Activity {
	
	GridView safe;
	Button show;
	    public int SELECT_PICTURE;
	    int imageNum = 0;
	    public static final String PREFS_NAME = "MyApp";

	    private String selectedImagePath;
	    private ImageView img;
	  
	    private List<String> imageUrls = new ArrayList<String>();
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.photos);
	        

	        img=(ImageView)findViewById(R.id.image);
	        safe = (GridView) findViewById(R.id.list);
	        show=(Button)findViewById(R.id.Button02);
	      
	        
	         
	        ((Button) findViewById(R.id.Button01))
	                .setOnClickListener(new OnClickListener() {
	                    public void onClick(View arg0) {
	                    	Intent intent = new Intent();
	                    	
	                        intent.setType("image/*");
	                        intent.setAction(Intent.ACTION_GET_CONTENT);
	                    
	                        Log.d("Gallery displaying....!",""+intent);
	                       
	                       final File imagesFolder = new File(Environment.getExternalStorageDirectory(), "Rasturant");
	        			     
	                       
	                       imagesFolder.mkdirs(); // <----
	        			      
	        					
	        				
	        			       
	        				String fileName = "image_" + String.valueOf(imageNum) + ".jpg";
	        				
	        				
	        			
	        				startActivityForResult(intent,0);
	                        
	                    }
	                });
	        show.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View arg0) {
	            	
	            Intent	in=new Intent(getApplicationContext(),Showimages.class);    
	        	     startActivity(in);
	        	     Toast.makeText(getApplicationContext()," Show Images", Toast.LENGTH_SHORT).show();
	            }
	        });
	    }
	    private Bitmap decodeFile(String f) {
			Bitmap b = null;
			try {
				
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
	              
	        	 
	        		
	        		File file = new File("/mnt/sdcard/Rasturant/"+fileName);
	        		Context ctx = getBaseContext();
				       File folder = ctx.getDir("Rasturant", Context.MODE_PRIVATE);
	        		

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
	        	            Log.i("ExternalStorage", "Scanned " + path + ":");
	        	            Log.i("ExternalStorage", "-> uri=" + uri);
	        	        }
	        	    });
	            	}
	            	catch(IOException e) {
	            		
	            	}
	                imageUrls.add(selectedImagePath);
	           	    Log.d("imagePath displaying....!",""+selectedImagePath);
	           	 
	                        
	                safe.setAdapter(new CustomGallery(this));
	                safe.invalidate();
	            		return ;
	                
	             
	              
	                
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
	    
	    class CustomGallery  extends BaseAdapter
	    {
	    	LayoutInflater inflater;
	    	public CustomGallery(Context ctx) {
	    		inflater = LayoutInflater.from(ctx);
			}

			public int getCount() {
				// TODO Auto-generated method stub
				return imageUrls.size();
			}

			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				Holder holder = null;
				if(convertView == null){
					holder = new Holder();
					convertView = inflater.inflate(R.layout.gallery, null);
					holder.img = (ImageView) convertView.findViewById(R.id.image);
					convertView.setTag(holder);
					
				}else{
					holder = (Holder) convertView.getTag();
				}
				
				holder.img.setImageBitmap(decodeFile(imageUrls.get(position)));
				//img.setLayoutParams(new Gallery.LayoutParams(200, 150));
				 Log.d("imageUrls displaying....!",""+imageUrls);
		
				return convertView;
				
			}
	    	
			class Holder
			{
				ImageView img;
				
			}
	    }

	    public void onBackPressed() {
	    	Log.d("back","back");
	           // Do as you please
	    	Intent in=new Intent(getApplicationContext(),Menu.class);    
		     startActivity(in);
	    }
}