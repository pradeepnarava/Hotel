package com.brill.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class ShowLogos extends Activity { 
	 Dialog d;
	 public static String logopath;
	 public static List<String> LogoPath = new ArrayList<String>();
	 public int SELECT_PICTURE;
	 public static final String PREFS_NAME = "MyApp";
	 DataLogo dl;
	    private String selectedImagePath,Logo;
	 private List<String> imageUrls = new ArrayList<String>();
private Uri[] mUrls; 
String[] mFiles=null; 
int imageNum = 0;
ImageView load,t3;
public void onCreate(Bundle icicle) { 
super.onCreate(icicle); 
setContentView(R.layout.logoview);
dl=new DataLogo(this);
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

load=(ImageView)findViewById(R.id.view_image);
 //Resources res = getResources();


if(Logo!=null){
	    /*Log.d("AddLogo.LogoPath.get(0)",""+AddLogo.LogoPath.get(0));
		 String pathName = AddLogo.LogoPath.get(0);
		 Log.d("pathName",""+pathName);*/
	Bitmap bitmap = BitmapFactory.decodeFile(Logo);

	// BitmapDrawable bd = new BitmapDrawable(res, bitmap);

	load.setImageBitmap(bitmap);
		  }

load.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		
		
		 Context x=v.getContext();
		 Log.d("x",""+x);
 		d=new Dialog(x);
 		d.setContentView(R.layout.edit_logo);
 		d.setTitle("Select another Logo");
 		d.show();
 		  t3=(ImageView)d.findViewById(R.id.edit_image);
 		 Bitmap bitmap = BitmapFactory.decodeFile(Logo);

 		// BitmapDrawable bd = new BitmapDrawable(res, bitmap);

 		 t3.setImageBitmap(bitmap);
 		Button ok=(Button)d.findViewById(R.id.b1);
 		Button close=(Button)d.findViewById(R.id.b2);
 		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			
				Intent intent = new Intent();
            	
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
            
                Log.d("Gallery displaying....!",""+intent);
               
               final File imagesFolder = new File(Environment.getExternalStorageDirectory(), "Logo");
			      imagesFolder.mkdirs(); // <----
			      
					
				
			       
				String fileName = "image_" + String.valueOf(imageNum) + ".jpg";
				
			
				startActivityForResult(intent,0);
			
			//d.cancel();
			}
		});
 	
 		close.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				
				d.cancel();
			}
			});
		
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
          t3.setImageBitmap(yourSelectedImage);
         load.setImageBitmap(yourSelectedImage);
            Drawable d =new BitmapDrawable(yourSelectedImage);
            File path = Environment.getExternalStoragePublicDirectory(
    		        Environment.DIRECTORY_PICTURES);
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            //Log.d("imagenum displaying....!",""+imageNum);
           imageNum =  settings.getInt("imageNum", 0);
            
    		String fileName = "Editimage_" + String.valueOf(imageNum) + ".jpg";
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
    	        	Log.d("editlogo",""+logopath);
    	        	dl.open();
    	        	dl.updateTitle(1, logopath);
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

@Override
public void onBackPressed() {
	Log.d("back","back");
       // Do as you please
	 Intent in=new Intent(getApplicationContext(),Menu.class);    
	     startActivity(in); 
	     finish();
}
} 
