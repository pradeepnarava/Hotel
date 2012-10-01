package com.brill.hotel;




import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.brill.hotel.CategoryList.DataAdapter;




public class AddMenu extends Activity{
	EditText name,price,des,category;
	String Name,Price,Des,Category;
	Button cat_button,sub,image_upload,image_capt;
	private Dialog dialog;
	 DataMenuImage data;
	DataBase db;
	int imageNum = 0;
	 public static final String PREFS_NAME = "MyApp";
	 private String selectedImagePath;
	String saveImage;
	
		private Bitmap image;
		private static final int IMAGE_PICK 	= 1;
		private static final int IMAGE_CAPTURE 	= 2;
		public static String ctg;
		ImageView load;
		 static byte[] byteArray;
		 public DataAdapter notes;
		 Button cate;
		 private static final int CAMERA_REQUEST = 1888; 
		 public static List<String>nameList= new ArrayList<String>();
		 public static List<String>stringList= new ArrayList<String>();
		 public static List<String>ImageList= new ArrayList<String>();
		ArrayList<Constructor> DisplayData = new ArrayList<Constructor>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.items);
		stringList.clear();
		db=new DataBase(this);
		data=new DataMenuImage(this);
		name=(EditText)findViewById(R.id.name);
		price=(EditText)findViewById(R.id.price);
		//category=(EditText)findViewById(R.id.category);
		des=(EditText)findViewById(R.id.description);
		 cate = (Button) findViewById(R.id.catbutton);
	 sub = (Button) findViewById(R.id.sub);
	 load=(ImageView)findViewById(R.id.loadimage);
		this. image_upload = (Button) findViewById(R.id.imageupload);
		 this.image_capt = (Button) findViewById(R.id.capture);
		this.image_upload.setOnClickListener(new ImagePickListener());
        this.image_capt.setOnClickListener(new TakePictureListener());
		db.open();
		Cursor getdetails=db.getlistitems();
		Log.d("getdetails",""+getdetails);
		Log.d("no of rows",""+getdetails.getCount());
		Log.d("getdetails.moveToFirst()",""+getdetails.moveToFirst());
		Log.d("db.getlistitems()",""+db.getlistitems());
		db.close();
		 if(getdetails.moveToFirst())
		  {
		   do{
			
			   ctg=getdetails.getString(1);
		    Log.d("categories",""+ctg);
		    
	        System.out.println("cat::::::::::"+ctg);
	        stringList.add(ctg);
	        Log.d("stringList",""+ stringList);
	      
			
		   }while(getdetails.moveToNext());
		   getdetails.close();
		}
		
		
		
		cate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				final CharSequence[] items = stringList.toArray(new CharSequence[ stringList.size()]);;
				Log.d("items",""+items);
				AlertDialog.Builder builder = new AlertDialog.Builder(AddMenu.this);
				builder.setTitle("Alert Dialog with ListView");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setItems(items, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int item) {
				       
				    	//Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
				   
				    cate.setText(items[item]);
				    Log.d("select category",""+cate);
				    }
				});
				AlertDialog alert = builder.create();

				alert.show();
              
			}
		});
		sub.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
		 				if(name.getText().toString().equalsIgnoreCase(""))
						{
		 					Toast.makeText(getApplicationContext(),"Enter Name", Toast.LENGTH_SHORT).show();
							//name.setError("Enter name");
							
						}
						
						else if(price.getText().toString().equalsIgnoreCase(""))
						{
							Toast.makeText(getApplicationContext(),"Enter Price", Toast.LENGTH_SHORT).show();
						//price.setError("Enter price");
						}
						else if(cate.getText().toString().equalsIgnoreCase("Category"))
						{
							Toast.makeText(getApplicationContext(),"Enter Category", Toast.LENGTH_SHORT).show();
							//category.setError("select category");
						}
						else if(des.getText().toString().equalsIgnoreCase(""))
						{
							Toast.makeText(getApplicationContext(),"Enter description", Toast.LENGTH_SHORT).show();

							//des.setError("Enter description");
						}
						
						else{	
							
						Name=name.getText().toString();
						Price=price.getText().toString();
						Category=cate.getText().toString();
					    Des=des.getText().toString();
						
					
						
						 Log.d("select name",""+Name);
						 Log.d("select price",""+Price);
						
					     Log.d("select category",""+Category);
						 Log.d("select des",""+Des);
						 
					 			
						
				       
				          
				          data.open();
				          Cursor getdetails=data.getlistitems();
				  		data.close();
							 if(getdetails.moveToFirst())
							  {
							   do{
							 String ccat=getdetails.getString(getdetails.getColumnIndex("name"));

							   Log.d("ccat",""+ccat);
						        //System.out.println("name::::::::::"+cat);
						       nameList.add(ccat);
						        
							   }while(getdetails.moveToNext());
							  }
							 getdetails.close();
							 Log.d(" nameList",""+ nameList);
							 int index = nameList.indexOf(Name);
							 if(index == -1){
								 if(saveImage==null){
									 Toast.makeText(getApplicationContext(),"Please insert the image", Toast.LENGTH_SHORT).show();
								 }
								 else{
									 data.open();
							      data.insertval(1,Name,Price,Category,Des,saveImage);
							      data.close();
							    	
							    	Intent back=new Intent(AddMenu.this,AddmenuList.class);
									startActivity(back);
									
							    	Toast.makeText(getApplicationContext(),"Data saved", Toast.LENGTH_SHORT).show();
							    	//
							    	
									name.setText("");
									price.setText("");
									des.setText("");
									cate.setText("Category");
								 }
							    	
							    }
							    else{
							    	
							    	
							    	Toast.makeText(getApplicationContext(),"check your previous Names", Toast.LENGTH_SHORT).show();
							    	
							    	name.setText("");
							    	price.setText("");
									des.setText("");
									cate.setText("Category");
							    	//System.out.println("ArrayList contains 4 at index :" + index);
							    }
							
							
						}
		 				
		 				
			
				
				
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
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	
	    	if (resultCode == Activity.RESULT_OK) { 
		    	switch (requestCode) {
				case IMAGE_PICK:	
					this.imageFromGallery(resultCode, data);
					break;
				case IMAGE_CAPTURE:
					this.imageFromCamera(resultCode, data);
					break;
				default:
					break;
				}
	    	}
	    }
	 
	
	    
	    /**
	     * Image result from camera
	     * @param resultCode
	     * @param data
	     */
	    private void imageFromCamera(int resultCode, Intent data) {
	    	Uri selectedImageUri = data.getData();
	    	Log.d("selectedImageUri",""+selectedImageUri);
	    	 Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
	    	 load.setImageBitmap(thumbnail);
	            //3
	            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
	            //4
	            SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                //Log.d("imagenum displaying....!",""+imageNum);
               imageNum =  settings.getInt("imageNum", 0);
                
               saveImage = "image_" + String.valueOf(imageNum) + ".jpg";
        		Log.d("imagenum displaying....!",""+imageNum);
        	   imageNum=++imageNum;
        	   Editor editor1 = settings.edit();
               editor1.putInt("imageNum",imageNum);
               editor1.commit();
              Log.d("imagenum displaying....!",""+imageNum);
	            File file = new File(Environment.getExternalStorageDirectory()+File.separator +saveImage);
	            Log.d("cameraimage",""+file);
	            saveImage="/mnt/sdcard/"+saveImage;
	            ImageList.add(saveImage);
	            try {
	                file.createNewFile();
	                FileOutputStream fo = new FileOutputStream(file);
	                //5
	                fo.write(bytes.toByteArray());
	                fo.close();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	    }
	    

			            	
	    	 
	          /* ByteArrayOutputStream stream = new ByteArrayOutputStream();
				 photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
				         byteArray = stream.toByteArray();*/
	  
	    
	    /**
	     * Image result from gallery
	     * @param resultCode
	     * @param data
	     */
	    private void imageFromGallery(int resultCode, Intent data) {
	    	
	            	try{
	            	Runtime.getRuntime().gc();
	            	Uri selectedImageUri = data.getData();
	            
	                selectedImagePath = getPath(selectedImageUri);
	                System.out.println("Image Path : " + selectedImagePath);
	                Bitmap yourSelectedImage = decodeFile(selectedImagePath);
	                load.setImageBitmap(yourSelectedImage);
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
	              
	        	 
	        		
	        		File file = new File("/mnt/sdcard/MenuImages/"+fileName);
	        		
	        		

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
	        	        	saveImage=path;
	        	        	Log.d("saveImage",""+saveImage);
	        	        	ImageList.add(saveImage);
	        	        	Log.d("ImageList",""+ImageList);
	        	            Log.i("ExternalStorage", "Scanned " + path + ":");
	        	            Log.i("ExternalStorage", "-> uri=" + uri);
	        	        }
	        	    });
	            	}
	            	catch(IOException e) {
	            		
	            	}
	    	 }
	    	
	    	
	    
	    public String getPath(Uri uri) {
	        String[] projection = { MediaStore.Images.Media.DATA };
	        Cursor cursor = managedQuery(uri, projection, null, null, null);
	        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	        cursor.moveToFirst();
	       
	        return cursor.getString(column_index);
	    }
	    
	class ImagePickListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			intent.setType("image/*");
			
            final File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MenuImages");
           
		    
				 imagesFolder.mkdirs(); //directory is created;

		    
			
            

			startActivityForResult(Intent.createChooser(intent, "Escolha uma Foto"), IMAGE_PICK);
			
		}
    }
    
    /**
     * Click listener for taking new picture
     * @author tscolari
     *
     */
    class TakePictureListener implements OnClickListener {
		public void onClick(View v) {
			
			/*Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, IMAGE_CAPTURE);*/
			Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			
			final File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MenuImages");

			
		   
				 imagesFolder.mkdirs(); //directory is created;

		  
			
			startActivityForResult(intent, IMAGE_CAPTURE);
		
		}
    }
	
	
}



